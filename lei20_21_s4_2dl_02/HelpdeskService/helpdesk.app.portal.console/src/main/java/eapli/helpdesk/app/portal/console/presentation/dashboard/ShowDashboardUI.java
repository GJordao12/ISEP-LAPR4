package eapli.helpdesk.app.portal.console.presentation.dashboard;

import eapli.framework.presentation.console.AbstractUI;
import eapli.helpdesk.dashboard.application.ShowDashboardController;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ShowDashboardUI extends AbstractUI {

    private final ShowDashboardController theController = new ShowDashboardController();

    @Override
    protected boolean doShow() {

        theController.metodo();
        theController.showDashboard();

        URI uri;
        try {
            uri = new URI("https://localhost:55090/");
                    Desktop.getDesktop().browse(uri);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public String headline() {
        return "Dashboard";
    }

}
