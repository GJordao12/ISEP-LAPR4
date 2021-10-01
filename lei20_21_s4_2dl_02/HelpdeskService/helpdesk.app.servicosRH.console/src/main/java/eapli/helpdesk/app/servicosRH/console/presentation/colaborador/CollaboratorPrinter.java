package eapli.helpdesk.app.servicosRH.console.presentation.colaborador;

import eapli.framework.visitor.Visitor;
import eapli.helpdesk.colaborador.domain.Collaborator;

public class CollaboratorPrinter implements Visitor<Collaborator> {

    @Override
    public void visit(final Collaborator visitee) {
        System.out.printf("%s",visitee.toString());
    }
}
