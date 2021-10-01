package eapli.helpdesk.pesquisa.catalogo;

import eapli.helpdesk.catalogo.domain.Catalogo;
import eapli.helpdesk.catalogo.domain.Titulo;
import eapli.helpdesk.catalogo.repositories.CatalogoRepository;
import eapli.helpdesk.infraestrutura.PersistenceContext;

public class PesquisarCatalogoTituloController {
    private final CatalogoRepository catalogoRepository = PersistenceContext.repositories().catalogo();

    public Iterable<Catalogo> findCatalogoByTitulo(Titulo titulo) {

        return this.catalogoRepository.findCatalogoByTitulo(titulo);
    }
}
