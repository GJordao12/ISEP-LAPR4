package eapli.helpdesk.app.portal.console.presentation.pesquisa.servico;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.helpdesk.pesquisa.servico.PesquisarServicoFeedbackController;
import eapli.helpdesk.servico.domain.Servico;
import eapli.helpdesk.criticidade.domain.Tempo;
import eapli.helpdesk.criticidade.exception.TempoException;


public class PesquisarServicoTempoFeedbackUI extends AbstractUI {

    private final PesquisarServicoFeedbackController theController = new PesquisarServicoFeedbackController();

    @Override
    protected boolean doShow() {
        int tempo;

            tempo = Console.readInteger("Tempo Limite de Feedback:");
            System.out.println("Resultados obtidos que possuem o tempo: " + tempo);
        try {
            Tempo t = new Tempo(tempo);
            for (Servico servico : theController.findServicoByFeedback(t)) {
                if (servico.tempoLimiteFeedback() == tempo && servico.estado().equalsIgnoreCase("Incompleto")) {
                    System.out.println("O servico " + servico + " estará brevemente disponivel!");
                }else{
                    System.out.println(servico.toString());
                }
            }
        } catch (TempoException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String headline () {
        return "Pesquisar Serviço";
    }
}
