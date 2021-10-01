package eapli.helpdesk.app.servicosRH.console.presentation.servico;

import eapli.framework.actions.Action;

public class ChangeServicoAction implements Action {
    @Override
    public boolean execute() {
        return new ChangeServicoUI().show();
    }
}
