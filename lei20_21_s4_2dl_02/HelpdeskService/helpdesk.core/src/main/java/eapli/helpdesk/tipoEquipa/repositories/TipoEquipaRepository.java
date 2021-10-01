package eapli.helpdesk.tipoEquipa.repositories;

import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumerico;
import eapli.helpdesk.tipoEquipa.domain.TipoEquipa;
import eapli.framework.domain.repositories.DomainRepository;

public interface TipoEquipaRepository
     extends DomainRepository<CodigoAlfaNumerico, TipoEquipa>
    {
    }
