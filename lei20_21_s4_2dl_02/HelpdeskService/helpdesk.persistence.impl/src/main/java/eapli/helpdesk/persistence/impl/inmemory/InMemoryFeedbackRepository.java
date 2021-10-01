package eapli.helpdesk.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.feedback.domain.FeedbackTicket;
import eapli.helpdesk.feedback.repository.FeedbackRepository;
import eapli.helpdesk.ticket.domain.Ticket;

import java.util.List;

public class InMemoryFeedbackRepository extends InMemoryDomainRepository<FeedbackTicket, Long>
        implements FeedbackRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public void saveFeedback(Ticket ticket, int classification) {
        //do nothing
    }

    @Override
    public List<Ticket> allFinishTicketsWithFeedbackAvailable(Collaborator collab) {
        return null;
    }
}
