package eapli.helpdesk.atividadeTicket.dto;

import eapli.framework.representations.dto.DTOParser;
import eapli.helpdesk.atividadeTicket.domain.AtividadeTicket;
import eapli.helpdesk.atividadeTicket.repositories.AtividadeTicketRepository;
import eapli.helpdesk.infraestrutura.PersistenceContext;

import java.util.Optional;

public class AtividadeTicketDTOParser implements DTOParser<AtividadeTicketDTO, AtividadeTicket> {

    private final AtividadeTicketRepository atividadeTicketRepository = PersistenceContext.repositories().atividadesTicket();

    public AtividadeTicketDTOParser() {
        // Empty
    }

    @Override
    public AtividadeTicket valueOf(final AtividadeTicketDTO dto) {
        Optional<AtividadeTicket> atividadeTicket = atividadeTicketRepository.ofIdentity(dto.id);

        if (!atividadeTicket.isPresent())
            throw new IllegalArgumentException(String.format("Nenhuma Atividade Ticket encontrada com o código: %s", dto.id));

        return atividadeTicket.get();
    }
}
