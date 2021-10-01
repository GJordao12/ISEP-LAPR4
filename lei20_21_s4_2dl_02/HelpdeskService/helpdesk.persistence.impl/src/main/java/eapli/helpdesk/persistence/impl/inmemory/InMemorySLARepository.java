package eapli.helpdesk.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import eapli.helpdesk.sla.domain.SLA;
import eapli.helpdesk.sla.repositories.SLARepository;

public class InMemorySLARepository extends InMemoryDomainRepository<SLA, Long>
        implements SLARepository {

    static {
        InMemoryInitializer.init();
    }
}
