package eapli.helpdesk.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import eapli.helpdesk.colaborador.domain.RoleCollaborator;
import eapli.helpdesk.equipa.domain.Designation;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumerico;
import eapli.helpdesk.funcao.RoleRepository;

public class InMemoryRolesRepository extends InMemoryDomainRepository<RoleCollaborator, CodigoAlfaNumerico>
            implements RoleRepository {
    @Override
    public RoleCollaborator findRoleByDesignation(Designation r) {
        return null;
    }
}
