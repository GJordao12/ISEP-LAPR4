package eapli.helpdesk.persistence.impl.jpa;


import eapli.helpdesk.servico.domain.Formulario;
import eapli.helpdesk.servico.repositories.FormularioRepository;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumerico;

import javax.persistence.TypedQuery;

public class JpaFormularioRepository extends HelpdeskJpaRepositoryBase<Formulario, CodigoAlfaNumerico, CodigoAlfaNumerico> implements FormularioRepository {

    public JpaFormularioRepository() {
        super("codigoAlfaNumerico");
    }

    @Override
    public Formulario findFormularioByCod(CodigoAlfaNumerico cod) {
        final TypedQuery<Formulario> query = entityManager().createQuery(
                "SELECT form FROM Formulario form  WHERE form.codAlfaNum = :codigo",
                Formulario.class);
        query.setParameter("codigo", cod);

        return query.getSingleResult();
    }
}
