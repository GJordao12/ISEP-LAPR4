package eapli.helpdesk.pesquisa.catalogo;

import eapli.helpdesk.catalogo.domain.Catalogo;
import eapli.helpdesk.catalogo.domain.Id;
import eapli.helpdesk.catalogo.repositories.CatalogoRepository;
import eapli.helpdesk.infraestrutura.PersistenceContext;

public class PesquisarCatalogoIdController {
    private final CatalogoRepository catalogoRepository = PersistenceContext.repositories().catalogo();
    public Iterable<Catalogo> findCatalogoById(Id id) {

        return this.catalogoRepository.findCatalogoById(id);
    }
}
