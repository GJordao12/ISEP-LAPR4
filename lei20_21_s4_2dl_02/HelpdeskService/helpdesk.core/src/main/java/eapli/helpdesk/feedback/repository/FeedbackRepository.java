package eapli.helpdesk.feedback.repository;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.feedback.domain.FeedbackTicket;
import eapli.helpdesk.feedback.exception.ClassificacaoException;
import eapli.helpdesk.ticket.domain.Ticket;

import java.util.List;

public interface FeedbackRepository extends DomainRepository<Long, FeedbackTicket> {
    void saveFeedback(Ticket ticket, int classification) throws ClassificacaoException;

    List<Ticket> allFinishTicketsWithFeedbackAvailable(Collaborator collab);
}
