package eapli.helpdesk.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import eapli.helpdesk.criticidade.domain.Criticidade;
import eapli.helpdesk.criticidade.domain.Valor;
import eapli.helpdesk.criticidade.repositories.CriticidadeRepository;

public class InMemoryCriticidadeRepository  extends InMemoryDomainRepository<Criticidade, Valor>
        implements CriticidadeRepository {

    static {
        InMemoryInitializer.init();
    }
}
