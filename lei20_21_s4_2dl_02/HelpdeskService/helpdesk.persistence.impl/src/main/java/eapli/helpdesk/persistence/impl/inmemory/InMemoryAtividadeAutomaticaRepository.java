package eapli.helpdesk.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import eapli.helpdesk.atividade.AtividadeAutomatica;
import eapli.helpdesk.servico.repositories.AtividadeAutomaticaRepository;

public class InMemoryAtividadeAutomaticaRepository extends InMemoryDomainRepository<AtividadeAutomatica, Long>
        implements AtividadeAutomaticaRepository {

    static {
        InMemoryInitializer.init();
    }
}
