package eapli.helpdesk.pesquisa.catalogo;


import eapli.helpdesk.colaborador.domain.MecanographicNumber;
import eapli.helpdesk.catalogo.domain.Catalogo;
import eapli.helpdesk.catalogo.repositories.CatalogoRepository;
import eapli.helpdesk.infraestrutura.PersistenceContext;


public class PesquisarCatalogoColabController {
    private final CatalogoRepository catalogoRepository = PersistenceContext.repositories().catalogo();
    public Iterable<Catalogo> findCatalogoByColab(MecanographicNumber number) {

        return this.catalogoRepository.findCatalogoByColab(number);
    }
}
