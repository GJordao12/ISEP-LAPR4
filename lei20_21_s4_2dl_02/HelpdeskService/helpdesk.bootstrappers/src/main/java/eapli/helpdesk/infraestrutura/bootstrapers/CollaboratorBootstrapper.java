package eapli.helpdesk.infraestrutura.bootstrapers;

import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.helpdesk.colaborador.application.AddCollaboratorController;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.colaborador.exception.LongNameException;
import eapli.helpdesk.colaborador.exception.MecanographicNumberException;
import eapli.helpdesk.colaborador.exception.PhoneNumberException;
import eapli.helpdesk.colaborador.exception.ShortNameException;
import eapli.helpdesk.equipa.domain.Team;
import eapli.helpdesk.equipa.exception.DesignationException;
import eapli.helpdesk.user.domain.HelpdeskRoles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CollaboratorBootstrapper implements Action {
    private static final Logger LOGGER = LogManager.getLogger(CollaboratorBootstrapper.class);

    @Override
    public boolean execute() {
        Set<Role> roleTypesCollaborator = new HashSet<>();
        Set<Role> roleTypesHRManager = new HashSet<>();
        Set<Role> roleTypesServiceManager = new HashSet<>();

        roleTypesCollaborator.add(HelpdeskRoles.COLLABORATOR);
        roleTypesHRManager.add(HelpdeskRoles.HR_MANAGER);
        roleTypesServiceManager.add(HelpdeskRoles.SERVICE_MANAGER);

        try {
            registerCollaborator("userName", "pwd123A1", "1190523@isep.ipp.pt", "11904", "Diogo Domingues", "Diogo Miguel Monte Domingues", "962588237", "13/12/2001", "Porto", "Póvoa de Varzim", roleTypesHRManager, null, new ArrayList<>());
            registerCollaborator("userName1", "pwd123A2", "1190447@isep.ipp.pt", "11905", "Bruno Silva", "Bruno Gabriel Flores Silva", "962588238", "01/04/2001", "Porto", "Póvoa de Varzim", roleTypesHRManager, null, new ArrayList<>());
            registerCollaborator("userName2", "pwd123A3", "1190633@isep.ipp.pt", "11906", "Goncalo Jordao", "Goncalo Ribeiro Teles Jordao", "973482132", "07/08/2001", "Porto", "Custóias", roleTypesHRManager, null, new ArrayList<>());
            registerCollaborator("userName3", "pwd123A4", "1190995@isep.ipp.pt", "11907", "Ricardo Mesquita", "Ricardo Jorge Monteiro Mesquita", "962585328", "05/08/2001", "Porto", "Matosinhos Praia", roleTypesHRManager, null, new ArrayList<>());
        } catch (ParseException | PhoneNumberException | ShortNameException | IOException | LongNameException | MecanographicNumberException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void registerCollaborator(String username, String password, String email, String mechanographicNumber, String shortName,
                                      String longName, String phoneNumber, String birthDate, String distrito,
                                      String concelho, Set<Role> roles, Collaborator responsible, ArrayList<Team> teams) throws ParseException, PhoneNumberException, ShortNameException, IOException, LongNameException, MecanographicNumberException {
        final AddCollaboratorController controller = new AddCollaboratorController();
        try {
            String [] s = shortName.split(" ");
            controller.registerCollaborator(username, password, email, mechanographicNumber, shortName, longName, phoneNumber, birthDate, distrito, concelho, roles, responsible, teams);
        } catch (final IntegrityViolationException | ConcurrencyException | DesignationException ex) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", mechanographicNumber);
            LOGGER.trace("Assuming existing record", ex);
        }
    }
}
