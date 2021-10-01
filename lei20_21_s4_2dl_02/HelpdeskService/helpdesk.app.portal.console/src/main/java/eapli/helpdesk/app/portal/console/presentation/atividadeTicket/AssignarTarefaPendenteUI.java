package eapli.helpdesk.app.portal.console.presentation.atividadeTicket;

import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.helpdesk.atividadeTicket.application.AssignarTarefaPendenteController;
import eapli.helpdesk.atividadeTicket.dto.AtividadeTicketDTO;

import java.util.List;

public class AssignarTarefaPendenteUI extends AbstractUI {

    private final AssignarTarefaPendenteController controller = new AssignarTarefaPendenteController();

    @Override
    protected boolean doShow() {

        try {
            List<AtividadeTicketDTO> tarefas = this.controller.allPendingAccessTasks();

            if (tarefas.isEmpty()) {
                System.out.println("[INFO] Não há tarefas disponíveis para serem reivindicadas!");
                return false;
            }

            final SelectWidget<AtividadeTicketDTO> selectorTask = new SelectWidget<>("Tarefas Pendentes Não Reivindicadas:", this.controller.orderByCriticality(tarefas), new AtividadeTicketDTOPrinter());
            selectorTask.show();

            if (selectorTask.selectedOption() == 0) {
                System.out.println("[INFO] Nenhuma Tarefa Reivindicada!");
                return false;
            }

            final AtividadeTicketDTO taskDTO = selectorTask.selectedElement();
            this.controller.assignTask(taskDTO);

            System.out.println("[SUCCESS] Tarefa Reivindicada!");
        } catch (IllegalArgumentException ie) {
            System.out.println(ie.getMessage());
        }
        return false;
    }

    @Override
    public String headline() {
        return "Reivindicar Tarefas Pendentes";
    }
}
