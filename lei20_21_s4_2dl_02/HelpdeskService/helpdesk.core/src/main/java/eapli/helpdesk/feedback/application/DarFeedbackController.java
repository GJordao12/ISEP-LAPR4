package eapli.helpdesk.feedback.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.colaborador.repositories.CollaboratorRepository;
import eapli.helpdesk.infraestrutura.PersistenceContext;
import eapli.helpdesk.ticket.dto.TicketDTO;
import eapli.helpdesk.ticket.service.ListTicketsService;
import eapli.helpdesk.user.domain.HelpdeskRoles;

import java.io.IOException;
import java.util.List;

public class DarFeedbackController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CollaboratorRepository repoColla = PersistenceContext.repositories().collaborators();
    private final String emailColab = authz.session().get().authenticatedUser().email().toString();
    private final Collaborator collab = repoColla.findByEmail(emailColab);
    private final ListTicketsService listTicketService = new ListTicketsService();

    public List<TicketDTO> allFinishTicketsWithFeedbackAvailable() {
        try {
            return this.listTicketService.createClientAndRequestTickets(collab, 8);
        } catch (IllegalArgumentException | IOException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    public void saveFeedback(TicketDTO ticketDTO, int classification) {
        authz.ensureAuthenticatedUserHasAnyOf(HelpdeskRoles.HR_MANAGER, HelpdeskRoles.SERVICE_MANAGER, HelpdeskRoles.COLLABORATOR);
        try {
            this.listTicketService.createClientAndRequestFeedbackTicket(ticketDTO, classification, 9);
        } catch (IllegalArgumentException | IOException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }
}
