package eapli.helpdesk.atividade;

import com.fasterxml.jackson.annotation.JsonProperty;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;


@XmlRootElement
@Entity
public class AtividadeAutomatica extends Atividade implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement
    @JsonProperty
    @Column(name = "ScriptAtvAutomatica")
    private Script script;

    protected AtividadeAutomatica() {
        // for ORM
    }

    public AtividadeAutomatica(Script script) {
        this.script = script;
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    public Script script() {
        return this.script;
    }
}
