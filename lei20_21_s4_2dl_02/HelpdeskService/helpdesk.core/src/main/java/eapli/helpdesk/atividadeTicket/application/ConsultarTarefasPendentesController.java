package eapli.helpdesk.atividadeTicket.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.colaborador.repositories.CollaboratorRepository;
import eapli.helpdesk.atividadeTicket.dto.AtividadeTicketDTO;
import eapli.helpdesk.atividadeTicket.service.ListAtividadeTicketService;
import eapli.helpdesk.infraestrutura.PersistenceContext;

import java.io.IOException;
import java.util.*;

public class ConsultarTarefasPendentesController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CollaboratorRepository collaboratorRepository = PersistenceContext.repositories().collaborators();
    private final String emailColab = authz.session().get().authenticatedUser().email().toString();
    private final Collaborator collab = collaboratorRepository.findByEmail(emailColab);
    private final ListAtividadeTicketService listService = new ListAtividadeTicketService();

    public List<AtividadeTicketDTO> allPendingTasks() {
        try {
            return this.listService.createClientAndRequestTasks(collab, 4);
        } catch (IllegalArgumentException | IOException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    public List<AtividadeTicketDTO> orderByPriority(List<AtividadeTicketDTO> tasks) {
        return this.listService.orderByPriority(tasks);
    }

    public List<AtividadeTicketDTO> orderByCriticality(List<AtividadeTicketDTO> tasks) {
        return this.listService.orderByCriticality(tasks);
    }

    public List<AtividadeTicketDTO> orderByDeadline(List<AtividadeTicketDTO> tasks) {
        return this.listService.orderByDeadline(tasks);
    }

    public Iterable<AtividadeTicketDTO> filterByPriority(List<AtividadeTicketDTO> tasks, int aux) {
        return this.listService.filterByPriority(tasks, aux);
    }

    public Iterable<AtividadeTicketDTO> filterByCriticality(List<AtividadeTicketDTO> tasks, int aux) {
        return this.listService.filterByCriticality(tasks, aux);
    }

    public Iterable<AtividadeTicketDTO> filterByDeadline(List<AtividadeTicketDTO> tasks, Calendar cal) {
        return this.listService.filterByDeadline(tasks, cal);
    }
}
