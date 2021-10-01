package eapli.helpdesk.app.servicosRH.console.presentation.tipoEquipa;

import eapli.framework.visitor.Visitor;
import eapli.helpdesk.tipoEquipa.domain.TipoEquipa;

public class TeamTypePrinter implements Visitor<TipoEquipa> {
    @Override
    public void visit(final TipoEquipa visitee) {
        System.out.printf("%s", visitee.toString());
    }
}
