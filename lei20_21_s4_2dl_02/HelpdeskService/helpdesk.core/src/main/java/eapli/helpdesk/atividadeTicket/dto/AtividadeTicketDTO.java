package eapli.helpdesk.atividadeTicket.dto;

import eapli.helpdesk.colaborador.dto.CollaboratorDTO;
import eapli.helpdesk.ticket.dto.TicketDTO;

import java.io.Serializable;

public class AtividadeTicketDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    public long id;
    public TicketDTO ticketDTO;
    public CollaboratorDTO collaboratorDTO;
    public String tipoAtividade;

    public AtividadeTicketDTO(long id, TicketDTO ticketDTO, CollaboratorDTO collaboratorDTO, String tipoAtividade) {
        this.id = id;
        this.ticketDTO = ticketDTO;
        this.collaboratorDTO = collaboratorDTO;
        this.tipoAtividade = tipoAtividade;
    }
    @Override
    public String toString() {
        return String.format("ID Atividade Ticket: %d - Tipo de Atividade: %s - ID Ticket: %d - Data Limite Ticket: %s - ID Serviço: %s - " +
                        "Descrição Serviço: %s - Criticidade: %s - Prioridade: %d", this.id, this.tipoAtividade, this.ticketDTO.id, this.ticketDTO.dataLimite.getTime(),
                this.ticketDTO.serviceDTO.code, this.ticketDTO.serviceDTO.briefDescription, this.ticketDTO.serviceDTO.critDTO.valor.toString(),
                this.ticketDTO.prioridade);
    }
}
