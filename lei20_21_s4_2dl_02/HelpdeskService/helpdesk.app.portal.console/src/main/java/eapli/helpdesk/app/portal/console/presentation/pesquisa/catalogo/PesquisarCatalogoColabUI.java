package eapli.helpdesk.app.portal.console.presentation.pesquisa.catalogo;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.helpdesk.colaborador.domain.MecanographicNumber;
import eapli.helpdesk.colaborador.exception.MecanographicNumberException;
import eapli.helpdesk.pesquisa.catalogo.PesquisarCatalogoColabController;
import eapli.helpdesk.catalogo.domain.Catalogo;

public class PesquisarCatalogoColabUI extends AbstractUI {

    private final PesquisarCatalogoColabController theController = new PesquisarCatalogoColabController();


    @Override
    protected boolean doShow() {
        String colab;

            colab = Console.readLine("Colaborador Responsável(Número mecanográfico):");
            System.out.println("Resultados obtidos que possuem " + colab);
        try {
            MecanographicNumber number = new MecanographicNumber(colab);
            for (Catalogo cat:theController.findCatalogoByColab(number)) {
                System.out.println(cat.toString());
            };
        } catch (MecanographicNumberException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public String headline () {
        return "Pesquisar Catalogo";
    }
}
