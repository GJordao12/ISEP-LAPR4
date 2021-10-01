package eapli.helpdesk.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import eapli.helpdesk.Application;
import eapli.helpdesk.servico.domain.Servico;
import eapli.helpdesk.servico.repositories.ServicoRepository;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumerico;
import eapli.helpdesk.catalogo.domain.Titulo;
import eapli.helpdesk.criticidade.domain.Tempo;

import javax.persistence.TypedQuery;

public class JpaServicoRepository
    extends JpaAutoTxRepository<Servico, CodigoAlfaNumerico, CodigoAlfaNumerico> implements ServicoRepository {

    public JpaServicoRepository(final TransactionalContext autoTx) {
        super(autoTx, "codAlfaNum");
    }

    public JpaServicoRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "codAlfaNum");
    }

    @Override
    public Iterable<Servico> findServicoByCodigo(CodigoAlfaNumerico cod) {
        final TypedQuery<Servico> query = createQuery(
                "SELECT t FROM Servico t WHERE t.codalfanum = :codigo",
                Servico.class);
        query.setParameter("codigo", cod);

        return query.getResultList();
    }

    @Override
    public Iterable<Servico> findServicoByFeedback(Tempo feed) {
        final TypedQuery<Servico> query = createQuery(
                "SELECT t FROM Servico t WHERE t.tempolimitefeedback = :feedback",
                Servico.class);
        query.setParameter("feedback", feed);

        return query.getResultList();
    }

    @Override
    public Iterable<Servico> findServicoByTitulo(Titulo titulo) {
        final TypedQuery<Servico> query = createQuery(
                "SELECT t FROM Servico t WHERE t.titulo = :tit",
                Servico.class);
        query.setParameter("tit", titulo);

        return query.getResultList();
    }
}
