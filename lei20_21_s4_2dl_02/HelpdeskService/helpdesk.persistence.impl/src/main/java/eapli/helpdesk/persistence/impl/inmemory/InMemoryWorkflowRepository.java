package eapli.helpdesk.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import eapli.helpdesk.servico.domain.Workflow;
import eapli.helpdesk.servico.repositories.WorkflowRepository;


public class InMemoryWorkflowRepository extends InMemoryDomainRepository<Workflow, Long>
        implements WorkflowRepository {

    static {
        InMemoryInitializer.init();
    }
}
