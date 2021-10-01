package eapli.helpdesk.app.portal.console.presentation.pesquisa.catalogo;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.helpdesk.catalogo.domain.Catalogo;
import eapli.helpdesk.catalogo.domain.Titulo;
import eapli.helpdesk.catalogo.exceptions.TituloException;
import eapli.helpdesk.pesquisa.catalogo.PesquisarCatalogoTituloController;

public class PesquisarCatalogoTituloUI extends AbstractUI {

    private final PesquisarCatalogoTituloController theController = new PesquisarCatalogoTituloController();

    @Override
    protected boolean doShow() {
        String titulo;

        titulo = Console.readLine("Titulo:");
        System.out.println("Resultados obtidos que possuem: " + titulo);
        try {
            Titulo titulo1 = new Titulo(titulo);
            for (Catalogo cat: theController.findCatalogoByTitulo(titulo1)) {
                System.out.println(cat.toString());
            }
        } catch (TituloException tituloException) {
            tituloException.printStackTrace();
        }
        return false;
    }

    @Override
    public String headline () {
        return "Pesquisar Catalogo";
    }
}
