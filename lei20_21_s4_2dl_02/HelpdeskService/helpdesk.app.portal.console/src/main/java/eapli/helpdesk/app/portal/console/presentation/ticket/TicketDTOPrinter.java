package eapli.helpdesk.app.portal.console.presentation.ticket;

import eapli.framework.visitor.Visitor;
import eapli.helpdesk.ticket.dto.TicketDTO;

public class TicketDTOPrinter implements Visitor<TicketDTO> {

    @Override
    public void visit(TicketDTO visitee) {
        System.out.printf("%s", visitee.toString());
    }
}
