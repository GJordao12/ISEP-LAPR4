package eapli.helpdesk.servico.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.representations.dto.DTOable;
import eapli.helpdesk.atividade.Script;
import eapli.helpdesk.servico.dto.FormularioDTO;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumerico;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Formulario implements AggregateRoot<CodigoAlfaNumerico>, DTOable<FormularioDTO>, Serializable {
    private static final long serialVersionUID = 1L;
    // business ID
    @XmlElement
    @JsonProperty
    @EmbeddedId
    private CodigoAlfaNumerico codAlfaNum;

    @XmlElement
    @JsonProperty
    private Nome nome;

    @XmlElement
    @JsonProperty
    private Etiqueta etiqueta;

    @XmlElement
    @JsonProperty
    private Script script;

    @JsonProperty
    @OneToMany (cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "IdFormulario")
    private List<AtributoFormulario> atributos = new ArrayList<>();



    protected Formulario() {
        // for ORM
    }


    public Formulario(final CodigoAlfaNumerico codAlfaNum, final Nome nome,final Etiqueta etiqueta,final Script script,
                      final List<AtributoFormulario> atributos) {
        this.codAlfaNum = codAlfaNum;
        this.etiqueta=etiqueta;
        this.atributos=atributos;
        this.script=script;
        this.nome=nome;

    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    public List<AtributoFormulario> atributos() {
        return atributos;
    }

    public Script scriptFormulario() {
        return script;
    }

    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    public CodigoAlfaNumerico codigoAlfaNumerico() {
        return identity();
    }

    @Override
    public CodigoAlfaNumerico identity() {
        return this.codAlfaNum;
    }

    public FormularioDTO toDTO() {
        return new FormularioDTO(nome.toString(), codAlfaNum.toString(), atributos);
    }
}
