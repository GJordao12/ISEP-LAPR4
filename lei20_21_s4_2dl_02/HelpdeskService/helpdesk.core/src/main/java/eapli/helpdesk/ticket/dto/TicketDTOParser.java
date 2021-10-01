package eapli.helpdesk.ticket.dto;

import eapli.framework.representations.dto.DTOParser;
import eapli.helpdesk.ticket.domain.Ticket;
import eapli.helpdesk.ticket.repositories.TicketRepository;
import eapli.helpdesk.infraestrutura.PersistenceContext;

import java.util.Optional;

public class TicketDTOParser implements DTOParser<TicketDTO, Ticket> {

    private final TicketRepository ticketRepository = PersistenceContext.repositories().tickets();

    public TicketDTOParser() {
        // Empty
    }

    @Override
    public Ticket valueOf(final TicketDTO dto) {
        Optional<Ticket> atividadeTicket = ticketRepository.ofIdentity(dto.id);

        if (!atividadeTicket.isPresent())
            throw new IllegalArgumentException(String.format("Nenhuma Atividade Ticket encontrada com o código: %s", dto.id));

        return atividadeTicket.get();
    }
}
