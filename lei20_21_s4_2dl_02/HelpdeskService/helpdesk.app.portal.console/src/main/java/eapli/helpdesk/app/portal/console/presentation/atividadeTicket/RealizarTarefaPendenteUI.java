package eapli.helpdesk.app.portal.console.presentation.atividadeTicket;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.helpdesk.antlr.formValidation.FormValidation;
import eapli.helpdesk.atividadeTicket.application.RealizarTarefaPendenteController;
import eapli.helpdesk.atividadeTicket.domain.AtividadeTicket;
import eapli.helpdesk.atividadeTicket.dto.AtividadeTicketDTO;
import eapli.helpdesk.servico.domain.AtributoFormulario;
import eapli.helpdesk.servico.domain.Formulario;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class RealizarTarefaPendenteUI extends AbstractUI {

    private final RealizarTarefaPendenteController controller = new RealizarTarefaPendenteController();

    @Override
    protected boolean doShow() {
        int option;
        do {
            System.out.println("Listagem de Tarefas Pendentes Possíveis de serem realizadas:\n");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<AtividadeTicketDTO> tasks = this.controller.showTasksPossibleToBeDone();
            if(tasks.isEmpty()) {
                System.out.println("\nNão existem tarefas pendentes para serem realizadas por si de momento...\n");
                return false;
            }
            final SelectWidget<AtividadeTicketDTO> selector = new SelectWidget<>("Selecione a tarefa que pretende realizar:", tasks);
            selector.show();
            option = selector.selectedOption();

            if(option != 0) {
                AtividadeTicketDTO selectedDTO = selector.selectedElement();
                AtividadeTicket selected = this.controller.showTicketInformation(selectedDTO);
                Calendar c = Calendar.getInstance();

                if (selectedDTO.tipoAtividade.equalsIgnoreCase("APROVACAO")) {
                    List<AtributoFormulario> atributos = selected.ticket().servico().workflow().atividadeManualAprovacao().formulario().atributos();
                    atributos.sort(Comparator.comparing(AtributoFormulario::codigoAtributo));
                    List<String> respostas = new ArrayList<>();
                    boolean flag = false;
                    try {
                        flag = respostasFormulario(atributos, respostas, selected.ticket().servico().workflow().atividadeManualAprovacao().formulario(), true);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        controller.processamentoTarefaAprovacao(selected, respostas, flag, c);
                    } catch (ParseException | IOException e) {
                        e.printStackTrace();
                    }
                }
                if (selectedDTO.tipoAtividade.equalsIgnoreCase("MANUAL")) {
                    List<AtributoFormulario> atributos = selected.ticket().servico().workflow().atividadeManualExecucao().formulario().atributos();
                    atributos.sort(Comparator.comparing(AtributoFormulario::codigoAtributo));
                    List<String> respostas = new ArrayList<>();
                    try {
                        respostasFormulario(atributos, respostas, selected.ticket().servico().workflow().atividadeManualExecucao().formulario(), false);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        controller.processamentoTarefaExecucao(selected, respostas, c);
                    } catch (ParseException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } while (option != 0);

        return false;
    }

    private boolean respostasFormulario(List<AtributoFormulario> atributos, List<String> respostas, Formulario formulario, boolean b) throws IOException {
        System.out.println("Pode proceder ao preenchimento dos atributos do formulário associado:\n");
        do {
            respostas.clear();
            for(AtributoFormulario atr : atributos) {
                String resposta = null;
                boolean flag = true;
                while (flag) {
                    resposta = Console.readLine("Nome do atributo: " + atr.toString() + "\nTipo de Dados: " + atr.tipoDados() + "\n");
                    if (resposta.matches(atr.expressaoRegularAtributo())) {
                        flag = false;
                    }
                }
                respostas.add(resposta);
            }
        } while(!FormValidation.verificarDados(formulario.scriptFormulario(), respostas, atributos));

        int opt;
        if(b) {
            do {
                System.out.println("Aprova esta tarefa?\n1 - Sim\n2 - Não\n");
                opt = Console.readInteger("Selecione uma opção\n");
            } while(opt<1 && opt>2);
            return (opt == 1);
        }
        return false;
    }

    @Override
    public String headline() {
        return "Realizar Tarefa Pendente";
    }
}
