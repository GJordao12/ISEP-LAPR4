package eapli.helpdesk.app.portal.console.presentation.ticket;

import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.helpdesk.ticket.application.ConsultarEstadoPedidosController;
import eapli.helpdesk.ticket.dto.TicketDTO;

import java.util.ArrayList;
import java.util.List;

public class ConsultarEstadoPedidosUI extends AbstractUI {

    private final ConsultarEstadoPedidosController theController = new ConsultarEstadoPedidosController();

    @Override
    protected boolean doShow() {
        int option;
        do {
            List<String> list = new ArrayList<>();
            list.add("Consultar Pedidos em Curso (ainda não concluídos)");
            list.add("Consultar Histórico de Pedidos (já concluídos)");
            final SelectWidget<String> selector = new SelectWidget<>("Operações Possíveis:", list);
            selector.show();
            option = selector.selectedOption();

            if (option == 1) {
                List<TicketDTO> requests = this.theController.showOnGoingTickets();
                if (requests == null || requests.isEmpty()) {
                    System.out.println("Ainda não existe registo de pedidos realizados por si no sistema...\n");
                } else {
                    int i = 1;
                    System.out.println("Pedidos em Curso e respetivos detalhes:");
                    for (TicketDTO t : requests) {
                        System.out.printf("%d -> %s\n", i, t);
                        i++;
                    }
                }
            }

            if (option == 2) {
                List<TicketDTO> requests = this.theController.showCompletedTickets();
                if (requests == null || requests.isEmpty()) {
                    System.out.println("Ainda não existe registo de pedidos realizados por si no sistema...\n");
                } else {
                    int i = 1;
                    System.out.println("Done requests of this Collaborator:");
                    for (TicketDTO t : requests) {
                        System.out.printf("%d -> %s\n", i, t);
                        i++;
                    }
                }
            }
        } while (option != 0);

        return false;
    }

    @Override
    public String headline() {
        return "Consultar Estado dos Pedidos";
    }
}
