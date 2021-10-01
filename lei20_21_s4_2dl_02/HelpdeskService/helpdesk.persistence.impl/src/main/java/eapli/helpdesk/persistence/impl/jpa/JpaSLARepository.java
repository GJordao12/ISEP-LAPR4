package eapli.helpdesk.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import eapli.helpdesk.Application;
import eapli.helpdesk.sla.domain.SLA;
import eapli.helpdesk.sla.repositories.SLARepository;

public class JpaSLARepository extends JpaAutoTxRepository<SLA, Long,Long> implements SLARepository {

    public JpaSLARepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    public JpaSLARepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "id");
    }
}
