package eapli.helpdesk.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import eapli.helpdesk.Application;
import eapli.helpdesk.servico.domain.Workflow;
import eapli.helpdesk.servico.repositories.WorkflowRepository;

public class JpaWorkflowRepository extends JpaAutoTxRepository<Workflow, Long, Long> implements WorkflowRepository {

    public JpaWorkflowRepository(final TransactionalContext autoTx) {
        super(autoTx, "codigoAlfanumerico");
    }

    public JpaWorkflowRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "codigoAlfanumerico");
    }
}
