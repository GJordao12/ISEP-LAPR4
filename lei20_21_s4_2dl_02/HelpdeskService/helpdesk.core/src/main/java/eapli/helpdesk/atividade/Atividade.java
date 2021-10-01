package eapli.helpdesk.atividade;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.*;

@MappedSuperclass
public class Atividade implements AggregateRoot<Long> {

    @Id
    @GeneratedValue
    private Long cod;

    protected Atividade() {
        // for ORM
    }
    public Long codigo() {
        return identity();
    }

    @Override
    public Long identity() {
        return this.cod;
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

}
