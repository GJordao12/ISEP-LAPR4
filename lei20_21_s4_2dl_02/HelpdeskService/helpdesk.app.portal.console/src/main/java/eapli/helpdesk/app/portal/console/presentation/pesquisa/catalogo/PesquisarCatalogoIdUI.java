package eapli.helpdesk.app.portal.console.presentation.pesquisa.catalogo;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.helpdesk.pesquisa.catalogo.PesquisarCatalogoIdController;
import eapli.helpdesk.catalogo.domain.Catalogo;
import eapli.helpdesk.catalogo.domain.Id;
import eapli.helpdesk.catalogo.exceptions.IdException;


public class PesquisarCatalogoIdUI extends AbstractUI {

    private final PesquisarCatalogoIdController theController = new PesquisarCatalogoIdController();

    @Override
    protected boolean doShow() {
        int id;


            id = Console.readInteger("Id:");
            System.out.println("Resultados obtidos que possuem: " + id);
        try {
            Id id1 = new Id(id);
            for (Catalogo cat: theController.findCatalogoById(id1)) {
                System.out.println(cat.toString());
            }
        } catch (IdException idException) {
            idException.printStackTrace();
        }
            return false;
    }

    @Override
    public String headline() {
        return "Pesquisar Catalogo";
    }
}
