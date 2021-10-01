/*
 * Copyright (c) 2013-2020 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.helpdesk.infraestrutura.bootstrapers;

import eapli.helpdesk.colaborador.domain.*;
import eapli.helpdesk.colaborador.exception.LongNameException;
import eapli.helpdesk.colaborador.exception.MecanographicNumberException;
import eapli.helpdesk.colaborador.exception.PhoneNumberException;
import eapli.helpdesk.colaborador.exception.ShortNameException;
import eapli.helpdesk.colaborador.repositories.CollaboratorRepository;
import eapli.helpdesk.equipa.domain.Designation;
import eapli.helpdesk.equipa.exception.DesignationException;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumerico;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumericoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eapli.helpdesk.infraestrutura.PersistenceContext;
import eapli.helpdesk.user.domain.HelpdeskRoles;
import eapli.helpdesk.user.domain.UserBuilderHelper;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthenticationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.strings.util.Strings;
import eapli.framework.validations.Invariants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * eCafeteria Bootstrapping data app
 *
 * @author Paulo Gandra de Sousa
 */
@SuppressWarnings("squid:S106")
public class HelpdeskBootstrapper implements Action {
    private static final Logger LOGGER = LogManager.getLogger(HelpdeskBootstrapper.class);

    private static final String POWERUSER_A1 = "poweruserA1";
    private static final String POWERUSER = "poweruser";
    private static final String HR_MANAGER_A1 = "hrmanagerA1";
    private static final String HR_MANAGER = "hrmanager";
    private static final String SERVICE_MANAGER_A1 = "servicemanagerA1";
    private static final String SERVICE_MANAGER = "servicemanager";

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final AuthenticationService authenticationService = AuthzRegistry
            .authenticationService();
    private final UserRepository userRepository = PersistenceContext.repositories().users();
    private static final CollaboratorRepository collaboratorRepository = PersistenceContext.repositories().collaborators();

    @Override
    public boolean execute() {
        // declare bootstrap actions
        final Action[] actions = {new TipoEquipaBootstrapper(),new CriticidadeBootstrapper(), new CollaboratorBootstrapper(), new TeamBootstrapper(),new CatalogoBootstrapper(),new ServicoBootstrapper(),new TicketBootstrapper()};

        Action action = new RoleBootstrapper();
        action.execute();
        registerPowerUser(userRepository);
        registerHRManager(userRepository);
        registerServiceManager(userRepository);
        authenticateForBootstrapping();

        // execute all bootstrapping
        boolean ret = true;
        for (final Action boot : actions) {
            System.out.println("Bootstrapping " + nameOfEntity(boot) + "...");
            ret &= boot.execute();
        }
        return ret;
    }

    /**
     * Register a power user directly in the persistence layer as we need to
     * circumvent authorizations in the Application Layer.
     */
    public static boolean registerPowerUser(UserRepository userRepository) {
        final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
        userBuilder.withUsername(POWERUSER).withPassword(POWERUSER_A1).withName("joe", "power")
                .withEmail("joe@email.org").withRoles(HelpdeskRoles.POWER_USER);
        final SystemUser newUser = userBuilder.build();

        try {
            final SystemUser poweruser = userRepository.save(newUser);
            assert poweruser != null;
            return true;
        } catch (ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)",
                    newUser.username());
            LOGGER.trace("Assuming existing record", e);
            return false;
        }
    }

    public static boolean registerHRManager(UserRepository userRepository) {
        final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
        userBuilder.withUsername(HR_MANAGER).withPassword(HR_MANAGER_A1).withName("Joao", "Goncalves")
                .withEmail("joaoHR@email.org").withRoles(HelpdeskRoles.HR_MANAGER);
        final SystemUser newUser = userBuilder.build();

        try {
            final SystemUser poweruser = userRepository.save(newUser);
            assert poweruser != null;
            CodigoAlfaNumerico cod = new CodigoAlfaNumerico("AAA111");
            Designation desig = new Designation("HR_MANAGER");
            RoleCollaborator role = new RoleCollaborator(cod, desig);
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
            Calendar c = Calendar.getInstance();
            c.setTime(formatoData.parse("13/12/2000"));
            Collaborator colab = new Collaborator(poweruser, new MecanographicNumber("11809"), new ShortName("Joao Goncalves"), new LongName("Joao Miguel Goncalves"), c, new Address("Porto", "Porto"), new PhoneNumber("962588299"), role, null);
            collaboratorRepository.save(colab);
            return true;
        } catch (ConcurrencyException | IntegrityViolationException | PhoneNumberException | ShortNameException | LongNameException | MecanographicNumberException | CodigoAlfaNumericoException | DesignationException | ParseException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)",
                    newUser.username());
            LOGGER.trace("Assuming existing record", e);
            return false;
        }
    }

    public static boolean registerServiceManager(UserRepository userRepository) {
        final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
        userBuilder.withUsername(SERVICE_MANAGER).withPassword(SERVICE_MANAGER_A1).withName("Tiago", "Pinto")
                .withEmail("TiagoService@email.org").withRoles(HelpdeskRoles.SERVICE_MANAGER);
        final SystemUser newUser = userBuilder.build();

        try {
            final SystemUser poweruser = userRepository.save(newUser);
            assert poweruser != null;
            CodigoAlfaNumerico cod1 = new CodigoAlfaNumerico("AAA123");
            Designation desig1 = new Designation("SERVICE_MANAGER");
            RoleCollaborator role = new RoleCollaborator(cod1, desig1);
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
            Calendar c = Calendar.getInstance();
            c.setTime(formatoData.parse("12/12/1999"));
            Collaborator colab = new Collaborator(poweruser, new MecanographicNumber("11810"), new ShortName("Tiago Pinto"), new LongName("Tiago Maia Pinto"), c, new Address("Porto", "Porto"), new PhoneNumber("962588298"), role, null);
            collaboratorRepository.save(colab);
            return true;
        } catch (ConcurrencyException | IntegrityViolationException | PhoneNumberException | ParseException | ShortNameException | LongNameException | MecanographicNumberException | CodigoAlfaNumericoException | DesignationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)",
                    newUser.username());
            LOGGER.trace("Assuming existing record", e);
            return false;
        }
    }

    /**
     * authenticate a super user to be able to register new users
     *
     */
    protected void authenticateForBootstrapping() {
        authenticationService.authenticate(POWERUSER, POWERUSER_A1);
        Invariants.ensure(authz.hasSession());
    }

    private String nameOfEntity(final Action boot) {
        final String name = boot.getClass().getSimpleName();
        return Strings.left(name, name.length() - "Bootstrapper".length());
    }
}