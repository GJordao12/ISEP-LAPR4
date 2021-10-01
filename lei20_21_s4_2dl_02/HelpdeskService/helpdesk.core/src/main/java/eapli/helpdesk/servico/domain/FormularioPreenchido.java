package eapli.helpdesk.servico.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import eapli.framework.domain.model.AggregateRoot;
import eapli.helpdesk.ticket.exceptions.FormularioPreenchidoException;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class FormularioPreenchido implements Serializable, AggregateRoot<Long> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;

    @OneToOne
    private Formulario formulario;

    @JsonProperty
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true , fetch = FetchType.EAGER)
    @JoinColumn(name = "atributo")
    private List<AtributoPreenchido> list = new ArrayList<>();


    public FormularioPreenchido(Formulario formulario, List<AtributoPreenchido> atributos){
        this.formulario=formulario;
        this.list = atributos;
    }

    protected FormularioPreenchido() {

    }

    public FormularioPreenchido(final FormularioPreenchido form) throws FormularioPreenchidoException {
            this.codigo = form.codigo;
            this.list = form.list;
    }

    public List<AtributoPreenchido> atributoPreenchidoList(){
        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FormularioPreenchido)) return false;
        FormularioPreenchido that = (FormularioPreenchido) o;
        return codigo.equals(that.codigo) && formulario.equals(that.formulario) && list.equals(that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, formulario, list);
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public String toString() {
        return "FormularioPreenchido{" +
                "codigo=" + codigo +
                ", formulario=" + formulario +
                ", list=" + list +
                '}';
    }

    @Override
    public Long identity() {
        return null;
    }
}
