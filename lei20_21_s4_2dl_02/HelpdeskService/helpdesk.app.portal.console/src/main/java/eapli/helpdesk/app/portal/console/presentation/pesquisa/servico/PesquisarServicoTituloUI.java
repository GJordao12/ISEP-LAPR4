package eapli.helpdesk.app.portal.console.presentation.pesquisa.servico;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.helpdesk.servico.domain.Servico;
import eapli.helpdesk.pesquisa.servico.PesquisarServicoTituloController;
import eapli.helpdesk.catalogo.domain.Titulo;
import eapli.helpdesk.catalogo.exceptions.TituloException;


public class PesquisarServicoTituloUI extends AbstractUI {

    private final PesquisarServicoTituloController theController = new PesquisarServicoTituloController();

    @Override
    protected boolean doShow() {
        String titulo;
            titulo = Console.readLine("Titulo:");
            System.out.println("Resultados obtidos que possuem o titulo: " + titulo);
            try {
                Titulo titulo1 = new Titulo(titulo);
                for (Servico servico : theController.findServicoByTitulo(titulo1)) {
                    if (servico.titulo().toString().equals(titulo) && servico.estado().equalsIgnoreCase("Incompleto")) {
                        System.out.println("O servico " + servico + " estará brevemente disponivel!");
                    }else{
                        System.out.println(servico.toString());
                    }
                }
            } catch (TituloException e) {
                e.printStackTrace();
            }

        return false;
    }

    @Override
    public String headline () {
        return "Pesquisar Serviço";
    }
}
