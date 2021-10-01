package eapli.helpdesk.infraestrutura.bootstrapers;

import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.colaborador.domain.MecanographicNumber;
import eapli.helpdesk.colaborador.exception.MecanographicNumberException;
import eapli.helpdesk.colaborador.repositories.CollaboratorRepository;
import eapli.helpdesk.equipa.application.AddTeamController;
import eapli.helpdesk.equipa.exception.AcronymException;
import eapli.helpdesk.equipa.exception.DesignationException;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumerico;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumericoException;
import eapli.helpdesk.tipoEquipa.domain.TipoEquipa;
import eapli.helpdesk.tipoEquipa.repositories.TipoEquipaRepository;
import eapli.helpdesk.infraestrutura.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeamBootstrapper implements Action {
    private static final Logger LOGGER = LogManager.getLogger(TeamBootstrapper.class);
    private final TipoEquipaRepository TeamTypeRepo = PersistenceContext.repositories().tipoEquipa();
    private final CollaboratorRepository CollaboratorRepo = PersistenceContext.repositories().collaborators();
    @Override
    public boolean execute() {

        Optional<TipoEquipa> type1 = null;
        try {
            type1 = TeamTypeRepo.ofIdentity(new CodigoAlfaNumerico("tpRH1"));
        } catch (CodigoAlfaNumericoException e) {
            e.printStackTrace();
        }

        Optional<Collaborator> colab1 = null;
        try {
            colab1 = CollaboratorRepo.ofIdentity(new MecanographicNumber("11904"));
        } catch ( MecanographicNumberException e) {
            e.printStackTrace();
        }


        Optional<Collaborator> colab2 = null;
        try {
           colab2 = CollaboratorRepo.ofIdentity(new MecanographicNumber("11906"));
        } catch (MecanographicNumberException e) {
            e.printStackTrace();
        }

        final List<Collaborator> list1 = new ArrayList<>();
        list1.add(colab1.stream().iterator().next());
        final List<Collaborator> list2= new ArrayList<>();
        list2.add(colab2.stream().iterator().next());


        Optional<Collaborator> colab3 = null;
        try {
            colab3 = CollaboratorRepo.ofIdentity(new MecanographicNumber("11809"));
        } catch (MecanographicNumberException e) {
            e.printStackTrace();
        }

        final List<Collaborator> list3 = new ArrayList<>();
        list3.add(colab3.stream().iterator().next());

        Optional<TipoEquipa>  tipoEquipa=null;
        try {
            tipoEquipa=TeamTypeRepo.ofIdentity(new CodigoAlfaNumerico("tpDV1"));
        } catch (CodigoAlfaNumericoException e) {
            e.printStackTrace();
        }


        registerTeam("equipaRH1", "RHTEAM1", "Equipa de recursos humanos principal", type1.stream().iterator().next(), list3, new ArrayList<>());
        registerTeam("equipaDV1", "DVTEAM1", "Equipa de departamento de vendas principal", tipoEquipa.stream().iterator().next(), list1, list2);
        return false;
    }

    private void registerTeam(final String uniqueCode, final String acronym, final String designation, final TipoEquipa teamType, final List<Collaborator> collaboratorsResponsible, final List<Collaborator> collaboratoresMembers) {
        final AddTeamController controller = new AddTeamController();
        try {
            controller.addTeam(uniqueCode, acronym, designation, teamType, collaboratorsResponsible, collaboratoresMembers);
        } catch (final IntegrityViolationException | ConcurrencyException ex) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", uniqueCode);
            LOGGER.trace("Assuming existing record", ex);
        } catch (DesignationException | AcronymException | CodigoAlfaNumericoException e) {
            e.printStackTrace();
        }
    }
}
