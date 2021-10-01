package eapli.helpdesk.app.servicosRH.console.presentation.equipa;

import eapli.framework.visitor.Visitor;
import eapli.helpdesk.equipa.domain.Team;

public class TeamPrinter implements Visitor<Team> {

    @Override
    public void visit(final Team visitee) {
        System.out.printf("%s", visitee.toString());
    }
}
