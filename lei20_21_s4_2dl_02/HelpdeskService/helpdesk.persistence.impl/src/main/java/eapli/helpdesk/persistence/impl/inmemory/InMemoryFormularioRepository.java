package eapli.helpdesk.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import eapli.helpdesk.servico.domain.Formulario;
import eapli.helpdesk.servico.repositories.FormularioRepository;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumerico;

public class InMemoryFormularioRepository extends InMemoryDomainRepository<Formulario, CodigoAlfaNumerico>
        implements FormularioRepository {

    static {
        InMemoryInitializer.init();
    }
    @Override
    public Formulario findFormularioByCod(CodigoAlfaNumerico cod) {
        return null;
    }
}
