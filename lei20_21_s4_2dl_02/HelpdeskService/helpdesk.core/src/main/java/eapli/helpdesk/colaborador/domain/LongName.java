
package eapli.helpdesk.colaborador.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import eapli.helpdesk.colaborador.exception.LongNameException;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

@Embeddable
public class LongName implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;
    @XmlElement
    @JsonProperty
    private String longName;

    public LongName(final String longName) throws LongNameException {
        if (StringPredicates.isNullOrEmpty(longName) || longName.length()>80 || !longName.contains(" ")) {
            throw new LongNameException("Nome Completo Inválido. Por favor insira um Nome Completo válido.");
        }
        // TODO validate invariants, i.e., mecanographic number regular
        // expression
        this.longName = longName;
    }

    protected LongName() {
        // for ORM
    }

    public static LongName valueOf(final String longName) throws LongNameException {
        return new LongName(longName);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LongName)) {
            return false;
        }

        final LongName that = (LongName) o;
        return this.longName.equals(that.longName);
    }

    @Override
    public int hashCode() {
        return this.longName.hashCode();
    }

    @Override
    public String toString() {
        return this.longName;
    }
}

