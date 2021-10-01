package eapli.helpdesk.app.portal.console.presentation.pesquisa.servico;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.helpdesk.servico.domain.Servico;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumerico;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumericoException;
import eapli.helpdesk.pesquisa.servico.PesquisarServicoCodigoController;


public class PesquisarServicoCodigoUI extends AbstractUI {

    private final PesquisarServicoCodigoController theController = new PesquisarServicoCodigoController();

    @Override
    protected boolean doShow() {
        String codigo;

        codigo = Console.readLine("Código Alfanumérico:");
        System.out.println("Resultados obtidos que possuem o código alfanumérico : " + codigo);
        try {
            CodigoAlfaNumerico cod = new CodigoAlfaNumerico(codigo);
            for (Servico servico : theController.findServicoByCodigo(cod)) {
                if (servico.codigoAlfaNumerico().toString().equals(codigo) && servico.estado().equalsIgnoreCase("Incompleto")) {
                    System.out.println("O servico " + servico + " estará brevemente disponivel!");
                }else {
                    System.out.println(servico.toString());
                }
            }
        } catch (CodigoAlfaNumericoException e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public String headline() {
        return "Pesquisar Serviço";
    }
}
