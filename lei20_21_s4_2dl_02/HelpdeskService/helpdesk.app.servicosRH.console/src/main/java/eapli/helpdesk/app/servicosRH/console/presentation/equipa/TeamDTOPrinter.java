package eapli.helpdesk.app.servicosRH.console.presentation.equipa;

import eapli.framework.visitor.Visitor;
import eapli.helpdesk.equipa.dto.TeamDTO;

public class TeamDTOPrinter implements Visitor<TeamDTO> {

    @Override
    public void visit(TeamDTO visitee) {
        System.out.printf("%s", visitee.toString());
    }
}
