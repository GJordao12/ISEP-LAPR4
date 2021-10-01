package eapli.helpdesk.persistence.impl.jpa;

import eapli.helpdesk.colaborador.domain.RoleCollaborator;
import eapli.helpdesk.equipa.domain.Designation;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumerico;
import eapli.helpdesk.funcao.RoleRepository;

import javax.persistence.Query;

public class JpaRolesRepository extends HelpdeskJpaRepositoryBase<RoleCollaborator, CodigoAlfaNumerico,CodigoAlfaNumerico> implements RoleRepository {
    public JpaRolesRepository() {
        super("codAlfaNumerico");
    }

    @Override
    public RoleCollaborator findRoleByDesignation(Designation r) {

                Query query = entityManager().createQuery(
                "SELECT t FROM RoleCollaborator t  WHERE t.designation = :role",
                RoleCollaborator.class);
        query.setParameter("role", r);

        return (RoleCollaborator) query.getSingleResult();
    }
}
