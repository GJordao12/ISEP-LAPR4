package eapli.helpdesk.persistence.impl.jpa;

import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.ticket.domain.Ticket;
import eapli.helpdesk.ticket.repositories.TicketRepository;

import javax.persistence.TypedQuery;
import java.util.List;


public class JpaTicketRepository extends HelpdeskJpaRepositoryBase<Ticket, Long, Long> implements TicketRepository {

    public JpaTicketRepository() {
        super("codigo");
    }


    @Override
    public List<Ticket> onGoingTicketsByCollab(Collaborator collab) {
        final TypedQuery<Ticket> query = entityManager().createQuery(
                "SELECT t FROM Ticket t WHERE t.colab = :collab AND " +
                        " UPPER(t.estado) not like 'RESOLVIDO' " , Ticket.class);
        query.setParameter("collab", collab);

        return query.getResultList();
    }

    @Override
    public List<Ticket> completedTicketsByCollab(Collaborator collab) {
        final TypedQuery<Ticket> query = entityManager().createQuery(
                "SELECT t FROM Ticket t WHERE t.colab = :collab AND " +
                        " UPPER(t.estado) like 'RESOLVIDO' " , Ticket.class);
        query.setParameter("collab", collab);

        return query.getResultList();
    }


}
