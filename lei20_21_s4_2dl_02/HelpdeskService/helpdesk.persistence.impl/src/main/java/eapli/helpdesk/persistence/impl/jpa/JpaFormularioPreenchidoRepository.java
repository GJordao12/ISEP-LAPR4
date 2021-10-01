package eapli.helpdesk.persistence.impl.jpa;

import eapli.helpdesk.servico.domain.FormularioPreenchido;
import eapli.helpdesk.ticket.repositories.FormularioPreenchidoRepository;

public class JpaFormularioPreenchidoRepository extends HelpdeskJpaRepositoryBase<FormularioPreenchido, Long, Long> implements FormularioPreenchidoRepository{

    public JpaFormularioPreenchidoRepository() {
        super("id");
    }
}
