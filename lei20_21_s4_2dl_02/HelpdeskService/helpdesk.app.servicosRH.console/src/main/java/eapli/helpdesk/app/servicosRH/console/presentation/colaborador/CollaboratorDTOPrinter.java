package eapli.helpdesk.app.servicosRH.console.presentation.colaborador;

import eapli.framework.visitor.Visitor;
import eapli.helpdesk.colaborador.dto.CollaboratorDTO;

public class CollaboratorDTOPrinter implements Visitor<CollaboratorDTO> {

    @Override
    public void visit(CollaboratorDTO visitee) {
        System.out.printf("%s", visitee.toString());

    }
}
