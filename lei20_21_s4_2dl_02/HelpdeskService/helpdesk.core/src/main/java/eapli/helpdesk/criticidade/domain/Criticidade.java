package eapli.helpdesk.criticidade.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.representations.dto.DTOable;
import eapli.framework.strings.util.StringPredicates;
import eapli.framework.validations.Preconditions;
import eapli.helpdesk.tipoEquipa.domain.Cor;
import eapli.helpdesk.tipoEquipa.domain.Descricao;
import eapli.helpdesk.criticidade.dto.CriticidadeDTO;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@Entity
public class Criticidade implements AggregateRoot<Valor>, Serializable, DTOable<CriticidadeDTO> {

    private static final long serialVersionUID = 1L;

    @XmlElement
    @JsonProperty
    @EmbeddedId
    private Valor valor;

    @XmlElement
    @JsonProperty
    @Column(nullable = false)
    private Descricao designacao;

    @XmlElement
    @JsonProperty
    @Column(nullable = false)
    private Cor cor;

    @XmlElement
    @JsonProperty
    @Column(nullable = false)
    private String etiqueta;

    @XmlElement
    @JsonProperty
    @Column(nullable = false)
    @AttributeOverride(name = "tempo", column = @Column(name = "tempoMedAprovacao"))
    private Tempo tempoMedAprovacao;

    @XmlElement
    @JsonProperty
    @Column(nullable = false)
    @AttributeOverride(name = "tempo", column = @Column(name = "tempoMaxAprovacao"))
    private Tempo tempoMaxAprovacao;

    @XmlElement
    @JsonProperty
    @Column(nullable = false)
    @AttributeOverride(name = "tempo", column = @Column(name = "tempoMedResolucao"))
    private Tempo tempoMedResolucao;

    @XmlElement
    @JsonProperty
    @Column(nullable = false)
    @AttributeOverride(name = "tempo", column = @Column(name = "tempoMaxResolucao"))
    private Tempo tempoMaxResolucao;

    protected Criticidade() {
        // for ORM
    }

    public Criticidade(final Valor valor, final Descricao designacao, final Cor cor, final String etiqueta, final Tempo tempoMedAprovacao, final Tempo tempoMaxAprovacao, final Tempo tempoMedResolucao, final Tempo tempoMaxResolucao) {
        this.valor = valor;
        this.designacao = designacao;
        this.cor = cor;
        this.tempoMedAprovacao = tempoMedAprovacao;
        this.tempoMaxAprovacao = tempoMaxAprovacao;
        this.tempoMedResolucao = tempoMedResolucao;
        this.tempoMaxResolucao = tempoMaxResolucao;

        Preconditions.ensure(etiquetaMeetsMinimumRequirements(etiqueta));

        this.etiqueta = etiqueta;
    }

    private boolean etiquetaMeetsMinimumRequirements(final String etiqueta) {
        return !StringPredicates.isNullOrEmpty(etiqueta);
    }

    @Override
    public boolean sameAs(Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public Valor identity() {
        return this.valor;
    }

    public String toString(){
        return ("Designação: "+this.designacao+" Valor: "+this.valor);
    }

    public CriticidadeDTO toDTO() {
        return new CriticidadeDTO(this.valor);
    }

    public Tempo tempoMedioAprovacao() {
        return tempoMedAprovacao;
    }

    public Tempo tempoMedioResolucao() {
        return tempoMedResolucao;
    }
}
