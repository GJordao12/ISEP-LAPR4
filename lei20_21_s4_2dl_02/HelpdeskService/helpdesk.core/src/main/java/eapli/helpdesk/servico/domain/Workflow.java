package eapli.helpdesk.servico.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.helpdesk.atividade.AtividadeAutomatica;
import eapli.helpdesk.atividade.AtividadeManual;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Calendar;


@XmlRootElement
@Entity
public class Workflow implements AggregateRoot<Long>, Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private long id;

    @XmlElement
    @JsonProperty
    private Calendar diaMudanca;


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn (name = "atividadeManualAprovacao")
    private AtividadeManual atividadeAprovacao;


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn (name = "atividadeManualExecucao")
    private AtividadeManual atividadeManual;


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn (name = "atividadeAutomatica")
    private AtividadeAutomatica atividadeAutomatica;

    protected Workflow() {
        // for ORM
    }


    public Workflow(Calendar diaMudanca,AtividadeManual atividadeAprovacao,AtividadeManual atividadeManual,AtividadeAutomatica atividadeAutomatica) {
        this.diaMudanca=diaMudanca;
        this.atividadeAprovacao=atividadeAprovacao;
        this.atividadeManual=atividadeManual;
        this.atividadeAutomatica=atividadeAutomatica;
    }

    public AtividadeManual atividadeManualAprovacao(){return this.atividadeAprovacao;}
    public AtividadeAutomatica atividadeAutomatica(){return this.atividadeAutomatica;}
    public AtividadeManual atividadeManualExecucao(){return this.atividadeManual;}


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

    public Long codigo() {
        return identity();
    }

    @Override
    public Long identity() {
        return this.id;
    }
}
