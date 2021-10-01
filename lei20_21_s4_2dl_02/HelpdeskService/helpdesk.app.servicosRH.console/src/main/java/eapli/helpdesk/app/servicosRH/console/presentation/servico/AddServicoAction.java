package eapli.helpdesk.app.servicosRH.console.presentation.servico;

import eapli.framework.actions.Action;


public class AddServicoAction implements Action {

    @Override
    public boolean execute() {
        return new AddServicoUI().show();
    }
}
