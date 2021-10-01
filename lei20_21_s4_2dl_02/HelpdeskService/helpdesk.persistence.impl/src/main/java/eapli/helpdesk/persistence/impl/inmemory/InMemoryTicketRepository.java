package eapli.helpdesk.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.ticket.domain.Ticket;
import eapli.helpdesk.ticket.repositories.TicketRepository;

import java.util.List;

public class InMemoryTicketRepository extends InMemoryDomainRepository<Ticket, Long>
        implements TicketRepository {

        static {
            InMemoryInitializer.init();
        }

        @Override
        public List<Ticket> onGoingTicketsByCollab(Collaborator collab) {
            return null;
        }

        @Override
        public List<Ticket> completedTicketsByCollab(Collaborator collab) {
            return null;
        }
}
