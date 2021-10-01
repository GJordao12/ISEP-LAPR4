package eapli.helpdesk.infraestrutura.smoketests.backoffice;

import eapli.framework.actions.Action;
import eapli.framework.validations.Invariants;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.equipa.domain.Acronym;
import eapli.helpdesk.equipa.domain.Designation;
import eapli.helpdesk.equipa.domain.Team;
import eapli.helpdesk.equipa.exception.AcronymException;
import eapli.helpdesk.equipa.exception.DesignationException;
import eapli.helpdesk.equipa.repositories.TeamRepository;
import eapli.helpdesk.tipoEquipa.domain.*;
import eapli.helpdesk.infraestrutura.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class EquipaManagementSmokerTest implements Action {

    private static final Logger LOGGER = LogManager.getLogger(EquipaManagementSmokerTest.class);

    private final TeamRepository repo = PersistenceContext.repositories().teams();

    @Override
    public boolean execute() {

        try {
            definirEquipas();
        } catch (DescricaoException | CorException | CodigoAlfaNumericoException | AcronymException | DesignationException tempoException) {
            tempoException.printStackTrace();
        }

        // nothing else to do
        return true;
    }

    private void definirEquipas() throws CodigoAlfaNumericoException, AcronymException, DesignationException, CorException, DescricaoException {
        //save
        List<Collaborator> list = new ArrayList<>();
        repo.save(new Team(new CodigoAlfaNumerico("AA44"), new Acronym("Acronimo45"), new Designation("Designacao"), new TipoEquipa(new Cor("#FFFFFF"), new CodigoAlfaNumerico("BB55"), new Descricao("Descricao")), list, list));
        repo.save(new Team(new CodigoAlfaNumerico("AA356"), new Acronym("Acronimo67"), new Designation("Designacao1"), new TipoEquipa(new Cor("#AAAAAA"), new CodigoAlfaNumerico("BB77"), new Descricao("Descricao1")), list, list));
        LOGGER.info("»»» definidas equipas");

        //findAll
        final Iterable<Team> l = repo.findAll();
        Invariants.nonNull(l);
        Invariants.nonNull(l.iterator());
        Invariants.ensure(l.iterator().hasNext());
        LOGGER.info("»»» find all teams");

        // count
        final long n = repo.count();
        LOGGER.info("»»» # teams = {}", n);

        // ofIdentity
        final Team c1 = repo.ofIdentity(new CodigoAlfaNumerico("AA44")).orElseThrow(IllegalStateException::new);
        final Team c2 = repo.ofIdentity(new CodigoAlfaNumerico("AA356")).orElseThrow(IllegalStateException::new);
        LOGGER.info("»»» found teams of identity");


        // containsOfIdentity
        final boolean hasId = repo.containsOfIdentity(c1.identity());
        Invariants.ensure(hasId);
        LOGGER.info("»»» contains criticidade of identity");

        // contains
        final boolean has = repo.contains(c1);
        Invariants.ensure(has);
        LOGGER.info("»»» contains criticidade");

        // delete
        repo.delete(c1);
        LOGGER.info("»»» delete criticidade");

        // deleteOfIdentity
        repo.deleteOfIdentity(c2.identity());
        LOGGER.info("»»» delete criticidade of identity");

        // size
        final long n2 = repo.size();
        Invariants.ensure(n2 == n - 2);
        LOGGER.info("»»» # criticidades = {}", n2);
    }
}
