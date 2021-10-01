package eapli.helpdesk.app.servicosRH.console.presentation.equipa;

import eapli.framework.actions.Action;

public class AddTeamAction implements Action {
    public boolean execute() {
        return new AddTeamUI().show();
    }
}
