package eapli.helpdesk.app.portal.console.presentation.pesquisa.catalogo;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.helpdesk.pesquisa.catalogo.PesquisarCatalogoCritController;
import eapli.helpdesk.catalogo.domain.Catalogo;
import eapli.helpdesk.criticidade.domain.Valor;
import eapli.helpdesk.criticidade.exception.ValorException;

public class PesquisarCatalogoCritUI extends AbstractUI {

    private final PesquisarCatalogoCritController theController = new PesquisarCatalogoCritController();

    @Override
    protected boolean doShow() {
        int crit;

            crit = Console.readInteger("Nivel de Criticidade:");
            System.out.println("Resultados obtidos que possuem: " + crit);
        try {
            Valor val = new Valor(crit);
            for (Catalogo cat:theController.findCatalogoByCrit(val)) {
                System.out.println(cat.toString());
            }
        } catch (ValorException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String headline () {
        return "Pesquisar Catalogo";
    }
}
