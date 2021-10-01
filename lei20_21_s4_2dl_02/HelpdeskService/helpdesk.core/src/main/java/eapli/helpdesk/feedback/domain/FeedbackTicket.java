package eapli.helpdesk.feedback.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.helpdesk.ticket.domain.Ticket;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlElement;

@Entity
public class FeedbackTicket implements AggregateRoot<Long> {

    @Id
    @GeneratedValue
    long id;

    @XmlElement
    @JsonProperty
    private Classificacao classificacao;

    @OneToOne
    private Ticket ticket;

    public FeedbackTicket(final Classificacao classificacao, final Ticket ticket) {
        this.classificacao = classificacao;
        this.ticket = ticket;
    }

    protected FeedbackTicket() {
        // for ORM only
    }

    public Classificacao classificacao() {
        return this.classificacao;
    }

    public Ticket ticket() {
        return this.ticket;
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Long identity() {
        return this.id;
    }
}
