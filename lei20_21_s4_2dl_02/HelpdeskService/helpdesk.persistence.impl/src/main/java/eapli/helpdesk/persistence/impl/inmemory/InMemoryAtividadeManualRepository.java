package eapli.helpdesk.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import eapli.helpdesk.atividade.AtividadeManual;
import eapli.helpdesk.servico.repositories.AtividadeManualRepository;

public class InMemoryAtividadeManualRepository extends InMemoryDomainRepository<AtividadeManual, Long>
        implements AtividadeManualRepository {

    static {
        InMemoryInitializer.init();
    }
}
