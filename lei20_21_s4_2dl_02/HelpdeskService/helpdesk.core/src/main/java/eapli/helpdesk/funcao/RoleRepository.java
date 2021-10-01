package eapli.helpdesk.funcao;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.helpdesk.colaborador.domain.RoleCollaborator;
import eapli.helpdesk.equipa.domain.Designation;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumerico;


public interface RoleRepository extends DomainRepository<CodigoAlfaNumerico, RoleCollaborator> {
    RoleCollaborator findRoleByDesignation(Designation r);
}
