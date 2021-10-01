package eapli.helpdesk.atividadeTicket.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.colaborador.repositories.CollaboratorRepository;
import eapli.helpdesk.atividadeTicket.dto.AtividadeTicketDTO;
import eapli.helpdesk.atividadeTicket.service.ListAtividadeTicketService;
import eapli.helpdesk.infraestrutura.PersistenceContext;
import eapli.helpdesk.user.domain.HelpdeskRoles;

import java.io.IOException;
import java.util.List;

public class AssignarTarefaPendenteController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CollaboratorRepository repoColla = PersistenceContext.repositories().collaborators();
    private final String emailColab = authz.session().get().authenticatedUser().email().toString();
    private final Collaborator collab = repoColla.findByEmail(emailColab);
    private final ListAtividadeTicketService listAtividadeTicketService = new ListAtividadeTicketService();

    public List<AtividadeTicketDTO> allPendingAccessTasks() {
        try {
            return this.listAtividadeTicketService.createClientAndRequestTasks(collab, 3);
        } catch (IllegalArgumentException | IOException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    public void assignTask(AtividadeTicketDTO atividadeTicketDTO) {
        authz.ensureAuthenticatedUserHasAnyOf(HelpdeskRoles.HR_MANAGER, HelpdeskRoles.SERVICE_MANAGER, HelpdeskRoles.COLLABORATOR);
        try {
            this.listAtividadeTicketService.createClientAndRequestAssignTask(collab, 5, atividadeTicketDTO);
        } catch (IllegalArgumentException | IOException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    public List<AtividadeTicketDTO> orderByCriticality(List<AtividadeTicketDTO> tasks) {
        return this.listAtividadeTicketService.orderByCriticality(tasks);
    }
}
