package eapli.helpdesk.catalogo.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.helpdesk.colaborador.domain.MecanographicNumber;
import eapli.helpdesk.catalogo.domain.Catalogo;
import eapli.helpdesk.catalogo.domain.Id;
import eapli.helpdesk.catalogo.domain.Titulo;
import eapli.helpdesk.criticidade.domain.Valor;

public interface CatalogoRepository extends DomainRepository<Id, Catalogo>
{
    Iterable<Catalogo> findCatalogoByColab(MecanographicNumber number);
    Iterable<Catalogo> findCatalogoByCrit(Valor crit);
    Iterable<Catalogo> findCatalogoById(Id id);
    Iterable<Catalogo> findCatalogoByTitulo(Titulo titulo);
}
