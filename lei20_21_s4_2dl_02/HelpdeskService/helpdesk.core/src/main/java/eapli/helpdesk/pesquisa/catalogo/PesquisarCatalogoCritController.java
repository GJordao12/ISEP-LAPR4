package eapli.helpdesk.pesquisa.catalogo;

import eapli.helpdesk.catalogo.domain.Catalogo;
import eapli.helpdesk.catalogo.repositories.CatalogoRepository;
import eapli.helpdesk.criticidade.domain.Valor;
import eapli.helpdesk.infraestrutura.PersistenceContext;

public class PesquisarCatalogoCritController {
    private final CatalogoRepository catalogoRepository = PersistenceContext.repositories().catalogo();
    public Iterable<Catalogo> findCatalogoByCrit(Valor crit) {

        return this.catalogoRepository.findCatalogoByCrit(crit);
    }
}
