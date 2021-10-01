package eapli.helpdesk.app.servicosRH.console.presentation.criticidade;

import eapli.framework.visitor.Visitor;
import eapli.helpdesk.criticidade.domain.Criticidade;

public class CriticidadePrinter implements Visitor<Criticidade> {

    @Override
    public void visit(final Criticidade visitee) {
        System.out.printf("%s",visitee.toString());
    }
}
