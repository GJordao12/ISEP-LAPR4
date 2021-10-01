package eapli.helpdesk.app.servicosRH.console.presentation.servico;

import eapli.framework.visitor.Visitor;
import eapli.helpdesk.servico.domain.Servico;

public class ServicoPrinter implements Visitor<Servico> {

    @Override
    public void visit(final Servico visiter) {
        System.out.printf("%s", visiter.toString());
    }
}

