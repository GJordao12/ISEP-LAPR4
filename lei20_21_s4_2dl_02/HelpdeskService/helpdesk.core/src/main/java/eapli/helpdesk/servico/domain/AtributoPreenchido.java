package eapli.helpdesk.servico.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
public class AtributoPreenchido implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private AtributoFormulario atributoFormulario;

    @XmlElement
    @JsonProperty
    private String nome;

    @XmlElement
    @JsonProperty
    private int valor;

    @XmlElement
    @JsonProperty
    private Boolean bool;

    @XmlElement
    @JsonProperty
    private Date data;


    public AtributoPreenchido(AtributoFormulario atFormulario, String atributo) {
        this.atributoFormulario = atFormulario;
        this.nome = atributo;
    }

    public AtributoPreenchido(AtributoFormulario atFormulario, int atributo) {
        this.atributoFormulario = atFormulario;
        this.valor = atributo;
    }

    public AtributoPreenchido(AtributoFormulario atFormulario, boolean atributo) {
        this.atributoFormulario = atFormulario;
        this.bool = atributo;
    }

    public AtributoPreenchido(AtributoFormulario atFormulario, Date data) {
        this.atributoFormulario = atFormulario;
        this.data = data;
    }

    public AtributoPreenchido() {

    }

    public Integer valor(){
        return valor;
    }

    public String nome(){
        return nome;
    }

    public AtributoFormulario atributoFormulario(){
        return atributoFormulario;
    }

    @Override
    public String toString() {
        return "AtributoPreenchido{" +
                "atributoFormulario=" + atributoFormulario +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", bool=" + bool +
                ", data=" + data +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AtributoPreenchido)) return false;
        AtributoPreenchido that = (AtributoPreenchido) o;
        return valor == that.valor && atributoFormulario.equals(that.atributoFormulario) && nome.equals(that.nome) && bool.equals(that.bool) && data.equals(that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(atributoFormulario, nome, valor, bool, data);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

