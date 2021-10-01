package eapli.helpdesk.colaborador.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

@Embeddable
public class Address implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;
    @XmlElement
    @JsonProperty
    private String distrito;

    @XmlElement
    @JsonProperty
    private String concelho;

    public Address(final String distrito, final String concelho) {
        Preconditions.noneNull(distrito, concelho);
        // TODO validate invariants, i.e., cor regular expression
        this.distrito = distrito;
        this.concelho = concelho;
    }

    protected Address() {
        // for ORM
    }

    public String distrito() {
        return this.distrito;
    }

    public String concelho() {
        return this.concelho;
    }

    public void newDistrito(String distrito){
        this.distrito=distrito;
    }
    public void newConcelho(String concelho){
        this.concelho=concelho;
    }
}

