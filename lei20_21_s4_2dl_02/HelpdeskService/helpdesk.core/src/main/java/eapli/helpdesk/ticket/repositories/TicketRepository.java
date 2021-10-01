package eapli.helpdesk.ticket.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.ticket.domain.Ticket;

import java.util.List;

public interface TicketRepository extends DomainRepository<Long, Ticket> {

    List<Ticket> onGoingTicketsByCollab(Collaborator collab);

    List<Ticket> completedTicketsByCollab(Collaborator collab);

}
