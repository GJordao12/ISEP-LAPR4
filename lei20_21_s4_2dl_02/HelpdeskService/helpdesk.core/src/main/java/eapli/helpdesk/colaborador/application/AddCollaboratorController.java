package eapli.helpdesk.colaborador.application;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.time.util.Calendars;
import eapli.helpdesk.colaborador.domain.*;
import eapli.helpdesk.colaborador.exception.LongNameException;
import eapli.helpdesk.colaborador.exception.MecanographicNumberException;
import eapli.helpdesk.colaborador.exception.PhoneNumberException;
import eapli.helpdesk.colaborador.exception.ShortNameException;
import eapli.helpdesk.colaborador.repositories.CollaboratorRepository;
import eapli.helpdesk.equipa.domain.Designation;
import eapli.helpdesk.equipa.domain.Team;
import eapli.helpdesk.equipa.exception.DesignationException;
import eapli.helpdesk.equipa.repositories.TeamRepository;
import eapli.helpdesk.infraestrutura.PersistenceContext;
import eapli.helpdesk.funcao.RoleRepository;
import eapli.helpdesk.user.domain.HelpdeskRoles;
import eapli.framework.infrastructure.authz.domain.model.Role;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;

public class AddCollaboratorController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final TransactionalContext ctx = PersistenceContext.repositories().newTransactionalContext();
    private final UserManagementService usrSvc = AuthzRegistry.userService();
    private final UserRepository userRepository = PersistenceContext.repositories().users(ctx);
    private final CollaboratorRepository collaboratorRepository = PersistenceContext.repositories().collaborators(ctx);
    private final TeamRepository teamRepository = PersistenceContext.repositories().teams();
    private final RoleRepository roleRepository = PersistenceContext.repositories().roles();

    public void registerCollaborator(String username, String password, String email, String mechanographicNumber, String shortName,
                                     String longName, String phoneNumber, String birthDate, String distrito,
                                     String concelho, Set<Role> roles, Collaborator responsible, ArrayList<Team> teams) throws ParseException, ShortNameException, MecanographicNumberException, LongNameException, PhoneNumberException, IOException, DesignationException {
        String [] nomes = shortName.split(" ");
        authz.ensureAuthenticatedUserHasAnyOf(HelpdeskRoles.HR_MANAGER, HelpdeskRoles.POWER_USER);

        ctx.beginTransaction();

        SystemUser user = addUser(username, password, nomes[0], nomes[1], email, roles);

        String r = roles.iterator().next().toString();
        Designation desig = new Designation(r);
        RoleCollaborator roleCollaborator = roleRepository.findRoleByDesignation(desig);

        registerNewCollaborator(user, mechanographicNumber, shortName, longName, phoneNumber, birthDate, distrito,
                concelho, roleCollaborator, responsible, teams);



        EmailHandler emailHandler = new EmailHandler();
        emailHandler.sendEmail(email, "Registo de Colaborador", "Parabéns, foi registado com sucesso na nossa empresa de Helpdesk!" +
                        " A sua password de acesso é: " + password);


        ctx.commit();
    }

    public SystemUser addUser(final String username, final String password, final String firstName,
                              final String lastName,
                              final String email, final Set<eapli.framework.infrastructure.authz.domain.model.Role> roles, final Calendar createdOn) {

        authz.ensureAuthenticatedUserHasAnyOf(HelpdeskRoles.HR_MANAGER, HelpdeskRoles.POWER_USER);

        return usrSvc.registerNewUser(username, password, firstName, lastName, email, roles, createdOn);
    }

    public SystemUser addUser(final String username, final String password, final String firstName,
                              final String lastName,
                              final String email, final Set<Role> roles) {
        return addUser(username, password, firstName, lastName, email, roles, Calendars.now());
    }


    public void registerNewCollaborator(SystemUser user, final String mechanographicNumber, final String shortName, final String longName,
                                        final String phoneNumber, final String birthDate, final String distrito, final String concelho,
                                        final RoleCollaborator roleCollaborator, final Collaborator responsible, ArrayList<Team> teams) throws ParseException, ShortNameException, MecanographicNumberException, LongNameException, PhoneNumberException {
        authz.ensureAuthenticatedUserHasAnyOf(HelpdeskRoles.HR_MANAGER, HelpdeskRoles.POWER_USER);

        MecanographicNumber mn = registerMecanographicNumber(mechanographicNumber);
        ShortName sn = registerShortName(shortName);
        LongName ln = registerLongName(longName);
        PhoneNumber pn = registerPhoneNumber(phoneNumber);
        Address add = new Address(distrito, concelho);

        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(formatoData.parse(birthDate));

        Collaborator colab = new Collaborator(user, mn, sn, ln, c, add, pn, roleCollaborator, responsible);
        this.collaboratorRepository.save(colab);
        if (!teams.isEmpty() && teams.get(0) != null) {
            for (Team t : teams) {
                t.listMembers().add(colab);
                teamRepository.save(t);
            }
        }
    }

    public Role[] getRoleTypes() {
        return HelpdeskRoles.collaboratorValues();
    }

    public MecanographicNumber registerMecanographicNumber(String mechanographicNumber) throws MecanographicNumberException {
        return new MecanographicNumber(mechanographicNumber);
    }

    public ShortName registerShortName(String nomeCurto) throws ShortNameException {
        return new ShortName(nomeCurto);
    }

    public LongName registerLongName(String nomeCompleto) throws LongNameException {
        return new LongName(nomeCompleto);
    }

    public PhoneNumber registerPhoneNumber(String phoneNumber) throws PhoneNumberException {
        return new PhoneNumber(phoneNumber);
    }
}
