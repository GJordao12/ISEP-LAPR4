package eapli.helpdesk.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import eapli.helpdesk.servico.domain.FormularioPreenchido;
import eapli.helpdesk.ticket.repositories.FormularioPreenchidoRepository;

public class InMemoryFormularioPreenchidoRepository extends InMemoryDomainRepository<FormularioPreenchido, Long>
        implements FormularioPreenchidoRepository {

    static {
        InMemoryInitializer.init();
    }
}
