package eapli.helpdesk.ticket.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.helpdesk.atividadeTicket.dto.AtividadeTicketDTO;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.colaborador.repositories.CollaboratorRepository;
import eapli.helpdesk.infraestrutura.PersistenceContext;
import eapli.helpdesk.ticket.dto.TicketDTO;
import eapli.helpdesk.ticket.service.ListTicketsService;

import java.io.IOException;
import java.util.List;

public class ConsultarEstadoPedidosController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CollaboratorRepository collaboratorRepository = PersistenceContext.repositories().collaborators();
    private final String emailColab = authz.session().get().authenticatedUser().email().toString();
    private final Collaborator collab = collaboratorRepository.findByEmail(emailColab);
    private final ListTicketsService ticketsService = new ListTicketsService();

    public List<TicketDTO> showOnGoingTickets() {
        try {
            return orderByRequestDate(this.ticketsService.createClientAndRequestTickets(collab, 6));
        } catch (IllegalArgumentException | IOException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    public List<TicketDTO> showCompletedTickets() {
        try {
            return orderByRequestDate(this.ticketsService.createClientAndRequestTickets(collab, 7));
        } catch (IllegalArgumentException | IOException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    public List<TicketDTO> orderByRequestDate(List<TicketDTO> tickets) {
        return this.ticketsService.orderByRequestDate(tickets);
    }
}
