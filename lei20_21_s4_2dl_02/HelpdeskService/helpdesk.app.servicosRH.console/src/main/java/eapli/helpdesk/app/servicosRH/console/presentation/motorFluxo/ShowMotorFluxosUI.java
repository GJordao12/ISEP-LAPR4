package eapli.helpdesk.app.servicosRH.console.presentation.motorFluxo;

import eapli.framework.presentation.console.AbstractUI;
import eapli.helpdesk.motorFluxos.application.ShowMotorFluxosController;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ShowMotorFluxosUI extends AbstractUI {

    private final ShowMotorFluxosController theController = new ShowMotorFluxosController();

    @Override
    protected boolean doShow() {

        try {
            theController.metodo1();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            theController.metodo2();
        } catch (IOException e) {
            e.printStackTrace();
        }
        theController.showMotorFluxos();

        URI uri;
        try {
            uri = new URI("https://localhost:55034/");
            Desktop.getDesktop().browse(uri);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public String headline() {
        return "Motor de Fluxos";
    }

}

