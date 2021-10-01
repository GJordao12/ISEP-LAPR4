package eapli.helpdesk.servico.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import eapli.helpdesk.tipoEquipa.domain.Descricao;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.GeneratedValue;
import java.io.Serializable;

@XmlRootElement
@Entity
public class AtributoFormulario  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;

    @XmlElement
    @JsonProperty
    @Column(name = "nomeAtributo")
    private Nome nome;

    @XmlElement
    @JsonProperty
    private Descricao descricao;

    @XmlElement
    @JsonProperty
    private Etiqueta etiqueta;

    @XmlElement
    @JsonProperty
    private String expressaoRegular;

    @XmlElement
    @JsonProperty
    private TipoDados tipoDados;


    protected AtributoFormulario() {
        // for ORM
    }


    public AtributoFormulario(Nome nome, Descricao descricao, String expressaoRegular, TipoDados tipoDados, Etiqueta etiqueta) {
        this.descricao = descricao;
        this.tipoDados = tipoDados;
        this.expressaoRegular = expressaoRegular;
        this.nome = nome;
        this.etiqueta = etiqueta;
    }

    public Long codigoAtributo() {
        return codigo;
    }

    public Nome nome(){return nome;}

    public TipoDados tipoDados() {
        return tipoDados;
    }

    public String expressaoRegularAtributo() {
        return expressaoRegular;
    }

    public Nome nomeAtributo() {
        return nome;
    }

    @Override
    public String toString() {
        return this.nome.toString();
    }

}
