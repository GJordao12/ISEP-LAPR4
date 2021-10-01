package eapli.helpdesk.atividade;

import eapli.framework.domain.model.DomainEntities;
import eapli.helpdesk.servico.domain.Formulario;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@XmlRootElement
@Entity
public class AtividadeManual extends Atividade implements Serializable {
    private static final long serialVersionUID = 1L;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "FormularioAtividadeManual")
    private Formulario formularioAtividadeManual;

    protected AtividadeManual() {
        // for ORM
    }

    public AtividadeManual(Formulario formularioAtividadeManual) {
        this.formularioAtividadeManual=formularioAtividadeManual;
    }

    public Formulario formulario(){return this.formularioAtividadeManual;}

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

}
