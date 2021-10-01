package eapli.helpdesk.persistence.impl.jpa;


import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumerico;
import eapli.helpdesk.tipoEquipa.domain.TipoEquipa;
import eapli.helpdesk.tipoEquipa.repositories.TipoEquipaRepository;

class JpaTipoEquipaRepository extends HelpdeskJpaRepositoryBase<TipoEquipa,CodigoAlfaNumerico,CodigoAlfaNumerico> implements TipoEquipaRepository {

    public JpaTipoEquipaRepository() {
        super("codAlfaNum");
    }
}