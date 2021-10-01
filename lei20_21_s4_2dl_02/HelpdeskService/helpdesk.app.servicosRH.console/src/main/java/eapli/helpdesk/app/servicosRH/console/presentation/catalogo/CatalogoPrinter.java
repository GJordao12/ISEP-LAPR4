package eapli.helpdesk.app.servicosRH.console.presentation.catalogo;

import eapli.framework.visitor.Visitor;
import eapli.helpdesk.catalogo.domain.Catalogo;

public class CatalogoPrinter implements Visitor<Catalogo> {

    @Override
    public void visit(final Catalogo visitee) {
        System.out.printf("%s",visitee.toString());
    }
}
