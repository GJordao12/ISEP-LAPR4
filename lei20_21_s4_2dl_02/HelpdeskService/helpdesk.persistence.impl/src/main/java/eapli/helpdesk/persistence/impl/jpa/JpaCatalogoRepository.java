package eapli.helpdesk.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import eapli.helpdesk.Application;
import eapli.helpdesk.colaborador.domain.MecanographicNumber;
import eapli.helpdesk.catalogo.domain.Catalogo;
import eapli.helpdesk.catalogo.domain.Id;
import eapli.helpdesk.catalogo.domain.Titulo;
import eapli.helpdesk.catalogo.repositories.CatalogoRepository;
import eapli.helpdesk.criticidade.domain.Valor;

import javax.persistence.TypedQuery;

public class JpaCatalogoRepository extends JpaAutoTxRepository<Catalogo, Id, Id> implements CatalogoRepository {

    public JpaCatalogoRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    public JpaCatalogoRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "id");
    }

    @Override
    public Iterable<Catalogo> findCatalogoByColab(MecanographicNumber number) {
        final TypedQuery<Catalogo> query = createQuery(
                "SELECT t FROM Catalogo t WHERE t.colabRes.mecanographicNumber =:num",
                Catalogo.class);
        query.setParameter("num", number);

        return query.getResultList();
    }

    @Override
    public Iterable<Catalogo> findCatalogoByCrit(Valor criticidade) {
        final TypedQuery<Catalogo> query = createQuery(
                "SELECT t FROM Catalogo t WHERE t.criticidade.valor = :crit",
                Catalogo.class);
        query.setParameter("crit", criticidade);

        return query.getResultList();
    }

    @Override
    public Iterable<Catalogo> findCatalogoById(Id id) {
        final TypedQuery<Catalogo> query = createQuery(
                "SELECT t FROM Catalogo t WHERE t.id = :identificador",
                Catalogo.class);
        query.setParameter("identificador", id);

        return query.getResultList();
    }

    @Override
    public Iterable<Catalogo> findCatalogoByTitulo(Titulo titulo) {
        final TypedQuery<Catalogo> query = createQuery(
                "SELECT t FROM Catalogo t WHERE t.titulo = :tit1",
                Catalogo.class);
        query.setParameter("tit1", titulo);

        return query.getResultList();
    }


}