package eapli.helpdesk.persistence.impl.jpa;

import eapli.helpdesk.criticidade.domain.Criticidade;
import eapli.helpdesk.criticidade.domain.Valor;
import eapli.helpdesk.criticidade.repositories.CriticidadeRepository;

public class JpaCriticidadeRepository extends HelpdeskJpaRepositoryBase<Criticidade, Valor, Valor>
        implements CriticidadeRepository {

    public JpaCriticidadeRepository() {
        super("valor");
    }
}
