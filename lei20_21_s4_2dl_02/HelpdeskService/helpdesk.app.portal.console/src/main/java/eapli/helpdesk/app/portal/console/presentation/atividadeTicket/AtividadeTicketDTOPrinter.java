package eapli.helpdesk.app.portal.console.presentation.atividadeTicket;

import eapli.framework.visitor.Visitor;
import eapli.helpdesk.atividadeTicket.dto.AtividadeTicketDTO;

public class AtividadeTicketDTOPrinter implements Visitor<AtividadeTicketDTO> {
    @Override
    public void visit(AtividadeTicketDTO visitee) {
        System.out.printf("%s", visitee.toString());
    }
}
