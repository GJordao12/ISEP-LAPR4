package eapli.helpdesk.infraestrutura.smoketests.backoffice;

import eapli.framework.actions.Action;
import eapli.framework.validations.Invariants;
import eapli.helpdesk.tipoEquipa.domain.Cor;
import eapli.helpdesk.tipoEquipa.domain.CorException;
import eapli.helpdesk.tipoEquipa.domain.Descricao;
import eapli.helpdesk.tipoEquipa.domain.DescricaoException;
import eapli.helpdesk.criticidade.domain.Criticidade;
import eapli.helpdesk.criticidade.domain.Tempo;
import eapli.helpdesk.criticidade.domain.Valor;
import eapli.helpdesk.criticidade.exception.TempoException;
import eapli.helpdesk.criticidade.exception.ValorException;
import eapli.helpdesk.criticidade.repositories.CriticidadeRepository;
import eapli.helpdesk.infraestrutura.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CriticidadeManagementSmokerTester implements Action {

    private static final Logger LOGGER = LogManager.getLogger(CriticidadeManagementSmokerTester.class);

    private final CriticidadeRepository repo = PersistenceContext.repositories().criticidades();

    @Override
    public boolean execute() {

        try {
            definirCriticidades();
        } catch (TempoException | ValorException | DescricaoException | CorException tempoException) {
            tempoException.printStackTrace();
        }

        // nothing else to do
        return true;
    }

    private void definirCriticidades() throws TempoException, ValorException, DescricaoException, CorException {
        //save
        repo.save(new Criticidade(new Valor(100), new Descricao("Alta"), new Cor("#FFFFFF"), "Alta", new Tempo(10), new Tempo(10), new Tempo(10), new Tempo(10)));
        repo.save(new Criticidade(new Valor(99), new Descricao("Baixa"), new Cor("#FFFFFF"), "Baixa", new Tempo(10), new Tempo(10), new Tempo(10), new Tempo(10)));
        LOGGER.info("»»» definidas criticidades");

        //findAll
        final Iterable<Criticidade> l = repo.findAll();
        Invariants.nonNull(l);
        Invariants.nonNull(l.iterator());
        Invariants.ensure(l.iterator().hasNext());
        LOGGER.info("»»» find all criticidades");

        // count
        final long n = repo.count();
        LOGGER.info("»»» # criticidades = {}", n);

        // ofIdentity
        final Criticidade c1 = repo.ofIdentity(new Valor(100)).orElseThrow(IllegalStateException::new);
        final Criticidade c2 = repo.ofIdentity(new Valor(99)).orElseThrow(IllegalStateException::new);
        LOGGER.info("»»» found criticidades of identity");


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
