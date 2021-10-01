package eapli.helpdesk.sla.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.representations.dto.DTOable;
import eapli.helpdesk.equipa.dto.TeamTypeDTO;
import eapli.helpdesk.servico.domain.Servico;
import eapli.helpdesk.ticket.domain.Ticket;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@Entity
public class SLA implements AggregateRoot<Long>, Serializable {

    private static final long serialVersionUID = 1L;

    // business ID
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @XmlElement
    @JsonProperty
    @OneToOne
    private Ticket ticket;

    @XmlElement
    @JsonProperty
    @OneToOne
    private Servico servico;

    @XmlElement
    @JsonProperty
    private String tipoAtividade;

    @XmlElement
    @JsonProperty
    private String comentario;

    @XmlElement
    @JsonProperty
    private Double tempoDecorrido;

    public SLA(Ticket ticket, Servico servico, String tipoAtividade, String comentario, Double tempoDecorrido) {
        this.ticket = ticket;
        this.servico = servico;
        this.tipoAtividade = tipoAtividade;
        this.comentario = comentario;
        this.tempoDecorrido = tempoDecorrido;
    }

    protected SLA() {
        // for ORM
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    public Long id() {
        return identity();
    }

    @Override
    public Long identity() {
        return this.id;
    }
}
