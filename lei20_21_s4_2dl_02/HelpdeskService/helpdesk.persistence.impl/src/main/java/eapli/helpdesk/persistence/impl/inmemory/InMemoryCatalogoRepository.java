
package eapli.helpdesk.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import eapli.helpdesk.colaborador.domain.MecanographicNumber;
import eapli.helpdesk.catalogo.domain.Catalogo;
import eapli.helpdesk.catalogo.domain.Id;
import eapli.helpdesk.catalogo.domain.Titulo;
import eapli.helpdesk.catalogo.repositories.CatalogoRepository;
import eapli.helpdesk.criticidade.domain.Valor;

public class InMemoryCatalogoRepository extends InMemoryDomainRepository<Catalogo, Id>
        implements CatalogoRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<Catalogo> findCatalogoByColab(MecanographicNumber number) {
        return null;
    }

    @Override
    public Iterable<Catalogo> findCatalogoByCrit(Valor crit) {
        return null;
    }

    @Override
    public Iterable<Catalogo> findCatalogoById(Id id) {
        return null;
    }

    @Override
    public Iterable<Catalogo> findCatalogoByTitulo(Titulo titulo) {
        return null;
    }
}
