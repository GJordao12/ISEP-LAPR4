package eapli.helpdesk.persistence.impl.jpa;

import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.feedback.domain.Classificacao;
import eapli.helpdesk.feedback.domain.FeedbackTicket;
import eapli.helpdesk.feedback.exception.ClassificacaoException;
import eapli.helpdesk.feedback.repository.FeedbackRepository;
import eapli.helpdesk.ticket.domain.Ticket;

import javax.persistence.TypedQuery;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class JpaFeedbackRepository extends HelpdeskJpaRepositoryBase<FeedbackTicket, Long, Long>
        implements FeedbackRepository {

    public JpaFeedbackRepository() {
        super("id");
    }

    @Override
    public void saveFeedback(Ticket ticket, int classification) {
        Classificacao classificacao = null;
        try {
            classificacao = new Classificacao(classification);
        } catch (ClassificacaoException e) {
            System.out.println("[ERROR] Classificação Inválida (0-5)");
        }
        FeedbackTicket ft = new FeedbackTicket(classificacao, ticket);
        this.save(ft);
    }

    @Override
    public List<Ticket> allFinishTicketsWithFeedbackAvailable(Collaborator collab) {
        final TypedQuery<Ticket> query = entityManager().createQuery(
                "SELECT t from Ticket t WHERE t NOT IN (SELECT f.ticket FROM FeedbackTicket f) AND UPPER(t.estado) LIKE 'RESOLVIDO'" +
                        "AND t.colab = :collab AND t.Servico.tempoLimiteFeedback >= datediff('day',(SELECT te.dataFim FROM AtividadeTicket te WHERE te.ticket = t AND" +
                        "(UPPER(te.tipoAtividade) like 'MANUAL' OR UPPER(te.tipoAtividade) like 'AUTOMATICA')),:calAtual)",
                Ticket.class);

        Date dataAtual = new Date();
        Calendar calAtual = Calendar.getInstance();
        calAtual.setTime(dataAtual);
        query.setParameter("calAtual", calAtual);
        query.setParameter("collab", collab);

        return query.getResultList();
    }
}
