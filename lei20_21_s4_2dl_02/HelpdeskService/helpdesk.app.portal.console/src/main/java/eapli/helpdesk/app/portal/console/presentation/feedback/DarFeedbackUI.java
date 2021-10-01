package eapli.helpdesk.app.portal.console.presentation.feedback;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.helpdesk.app.portal.console.presentation.ticket.TicketDTOPrinter;
import eapli.helpdesk.feedback.application.DarFeedbackController;
import eapli.helpdesk.ticket.dto.TicketDTO;

import java.util.List;

public class DarFeedbackUI extends AbstractUI {

    private final DarFeedbackController controller = new DarFeedbackController();

    @Override
    protected boolean doShow() {
        try {
            List<TicketDTO> tickets = this.controller.allFinishTicketsWithFeedbackAvailable();

            if (tickets.isEmpty()) {
                System.out.println("[INFO] Não há tickets disponíveis para dar Feedback!");
                return false;
            }

            final SelectWidget<TicketDTO> selectorTask = new SelectWidget<>("Tickets que pode dar Feedback:", tickets, new TicketDTOPrinter());
            selectorTask.show();

            if (selectorTask.selectedOption() == 0) {
                System.out.println("[INFO] Nenhum Feedback Realizado!");
                return false;
            }

            int classification;

            do {
                classification = Console.readInteger("Classificação que pretende atribuir à resolução do Ticket selecionado (0-5): ");
                if (classification < 0 || classification > 5) {
                    System.out.println("[ERROR] A Classificação deve ser maior ou igual a 0 e menor ou igual a 5!");
                }
            } while (classification < 0 || classification > 5);

            final TicketDTO taskDTO = selectorTask.selectedElement();
            this.controller.saveFeedback(taskDTO, classification);

            System.out.println("[SUCCESS] Obrigado pelo seu Feedback, é muito importante para nós!");
        } catch (IllegalArgumentException ie) {
            System.out.println(ie.getMessage());
        }
        return false;
    }

    @Override
    public String headline() {
        return "Dar Feedback";
    }
}
