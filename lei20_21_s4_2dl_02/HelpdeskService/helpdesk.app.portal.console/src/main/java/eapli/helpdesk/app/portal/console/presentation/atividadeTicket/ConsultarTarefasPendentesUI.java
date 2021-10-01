package eapli.helpdesk.app.portal.console.presentation.atividadeTicket;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.helpdesk.atividadeTicket.application.ConsultarTarefasPendentesController;
import eapli.helpdesk.atividadeTicket.dto.AtividadeTicketDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ConsultarTarefasPendentesUI extends AbstractUI {

    private final ConsultarTarefasPendentesController theController = new ConsultarTarefasPendentesController();

    @Override
    protected boolean doShow() {
        try {
            final List<AtividadeTicketDTO> tasks = this.theController.allPendingTasks();
            if (tasks.isEmpty()) {
                System.out.println("Colaborador sem tarefas pendentes de momento assignadas...\n");
                return false;
            }

            System.out.println("Tarefas Pendentes:\n");
            int d = 1;
            for (AtividadeTicketDTO t : tasks) {
                System.out.printf("%d -> %s\n", d, t);
                d++;
            }

            int option;
            do {
                List<String> list = new ArrayList<>();
                list.add("Ordenar");
                list.add("Filtrar");
                final SelectWidget<String> selector = new SelectWidget<>("Operações Possíveis:", list);
                selector.show();
                option = selector.selectedOption();

                if (selector.selectedOption() == 1) {
                    List<String> list1 = new ArrayList<>();
                    list1.add("Prioridade");
                    list1.add("Criticidade");
                    list1.add("Deadline");
                    final SelectWidget<String> selector1 = new SelectWidget<>("Ordenações Possíveis:", list1);
                    selector1.show();

                    if (selector1.selectedOption() == 1) {
                        List<AtividadeTicketDTO> tasksWithPriority = theController.orderByPriority(tasks);

                        int j = 1;
                        System.out.println("Tarefas Pendentes Assignadas Ordenadas por Prioridade:");
                        for (AtividadeTicketDTO t : tasksWithPriority) {
                            System.out.printf("%d -> %s\n", j, t);
                            j++;
                        }
                    }

                    if (selector1.selectedOption() == 2) {
                        List<AtividadeTicketDTO> tasksWithCriticality = theController.orderByCriticality(tasks);

                        int k = 1;
                        System.out.println("Tarefas Pendentes Assignadas Ordenadas por Criticidade:");
                        for (AtividadeTicketDTO t : tasksWithCriticality) {
                            System.out.printf("%d -> %s\n", k, t);
                            k++;
                        }
                    }

                    if (selector1.selectedOption() == 3) {
                        List<AtividadeTicketDTO> tasksWithDeadline = theController.orderByDeadline(tasks);

                        int l = 1;
                        System.out.println("Tarefas Pendentes Assignadas Ordenadas por Deadline:");
                        for (AtividadeTicketDTO t : tasksWithDeadline) {
                            System.out.printf("%d -> %s\n", l, t);
                            l++;
                        }
                    }
                }

                if (selector.selectedOption() == 2) {
                    List<String> list2 = new ArrayList<>();
                    list2.add("Prioridade");
                    list2.add("Criticidade");
                    list2.add("Deadline");
                    final SelectWidget<String> selector2 = new SelectWidget<>("Tipos de Filtragem Possíveis:", list2);
                    selector2.show();

                    if (selector2.selectedOption() == 1) {
                        List<String> list3 = new ArrayList<>();
                        list3.add("Reduzida");
                        list3.add("Moderada");
                        list3.add("Urgente");
                        final SelectWidget<String> selector3 = new SelectWidget<>("Filtrar por:", list3);
                        selector3.show();
                        Iterable<AtividadeTicketDTO> tasksWithPriority = theController.filterByPriority(tasks, selector3.selectedOption());

                        int j = 1;
                        System.out.println("Tarefas filtradas pela prioridade especificada:");
                        for (AtividadeTicketDTO t : tasksWithPriority) {
                            System.out.printf("%d -> %s\n", j, t);
                            j++;
                        }
                    }

                    if (selector2.selectedOption() == 2) {
                        int optionCriticicality = Console.readInteger("\nInsira a criticidade pela qual pretende filtrar");
                        Iterable<AtividadeTicketDTO> tasksWithCriticality = theController.filterByCriticality(tasks, optionCriticicality);

                        if (!tasks.isEmpty()) {
                            int k = 1;
                            System.out.println("Tarefas filtradas por criticidade:");
                            for (AtividadeTicketDTO t : tasksWithCriticality) {
                                System.out.printf("%d -> %s\n", k, t);
                                k++;
                            }
                        } else {
                            System.out.println("Não existem tarefas associadas ao nível de criticidade especificado...");
                        }
                    }

                    boolean flag;
                    if (selector2.selectedOption() == 3) {
                        flag = true;
                        String optionDeadline = "";
                        Calendar c = Calendar.getInstance();
                        while (flag) {
                            try {
                                optionDeadline = Console.readLine("\nInsira a deadline pela qual pretende filtrar");
                                if (optionDeadline.matches("(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((?:19|20)[0-9][0-9])")) {
                                    SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
                                    c.setTime(formatoData.parse(optionDeadline));
                                    c.set(Calendar.HOUR_OF_DAY, 0);
                                    c.set(Calendar.MINUTE, 0);
                                    c.set(Calendar.SECOND, 0);
                                    c.set(Calendar.MILLISECOND, 0);
                                    flag = false;
                                }
                            } catch (ParseException e) {
                                System.out.println("Deadline Inválida...");
                            }
                        }
                        Iterable<AtividadeTicketDTO> tasksWithDeadline = theController.filterByDeadline(tasks, c);

                        int l = 1;
                        System.out.println("Tarefas Filtradas por Deadline:");
                        for (AtividadeTicketDTO t : tasksWithDeadline) {
                            System.out.printf("%d -> %s\n", l, t);
                            l++;
                        }
                    }
                }
            } while (option != 0);
        } catch (IllegalArgumentException ie) {
            System.out.println(ie.getMessage());
        }
        return false;
    }

    @Override
    public String headline() {
        return "Consultar Tarefas Pendentes Assignadas";
    }
}
