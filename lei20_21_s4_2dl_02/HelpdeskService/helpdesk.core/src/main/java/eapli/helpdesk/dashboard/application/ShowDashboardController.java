package eapli.helpdesk.dashboard.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.helpdesk.AppSettings;
import eapli.helpdesk.Application;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.colaborador.repositories.CollaboratorRepository;
import eapli.helpdesk.dashboard.domain.HttpServerAjaxVoting;
import eapli.helpdesk.atividadeTicket.dto.AtividadeTicketDTO;
import eapli.helpdesk.atividadeTicket.service.ListAtividadeTicketService;
import eapli.helpdesk.infraestrutura.PersistenceContext;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ShowDashboardController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CollaboratorRepository collaboratorRepository = PersistenceContext.repositories().collaborators();
    private final String emailColab = authz.session().get().authenticatedUser().email().toString();
    private final Collaborator collab = collaboratorRepository.findByEmail(emailColab);
    private final ListAtividadeTicketService listService = new ListAtividadeTicketService();
    private List<AtividadeTicketDTO> atvList;

    public void showDashboard()  {
        HttpServerAjaxVoting server = new HttpServerAjaxVoting(atvList);
        server.changeController(this);
        server.start();
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

    public List<AtividadeTicketDTO> metodo() {
        try {
            this.atvList = this.listService.createClientAndRequestTasks(collab, 4);
        } catch (IllegalArgumentException | IOException ie) {
            this.atvList = new ArrayList<>();
        }
        return this.atvList;
    }
}
