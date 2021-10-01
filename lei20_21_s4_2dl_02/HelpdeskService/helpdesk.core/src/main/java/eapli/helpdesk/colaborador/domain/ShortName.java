
package eapli.helpdesk.colaborador.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import eapli.helpdesk.colaborador.exception.ShortNameException;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ShortName implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;
    private String shortName;

    public ShortName(final String shortName) throws ShortNameException {
        if (StringPredicates.isNullOrEmpty(shortName) || shortName.length()>30 || !shortName.contains(" ")) {
            throw new ShortNameException("Nome Curto Inválido. Por favor um Nome Curto válido.");
        }
        // TODO validate invariants, i.e., mecanographic number regular
        // expression
        this.shortName = shortName;
    }

    protected ShortName() {
        // for ORM
    }

    public static ShortName valueOf(final String shortName) throws ShortNameException {
        return new ShortName(shortName);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShortName)) {
            return false;
        }

        final ShortName that = (ShortName) o;
        return this.shortName.equals(that.shortName);
    }

    @Override
    public int hashCode() {
        return this.shortName.hashCode();
    }

    @Override
    public String toString() {
        return "Nome Curto: " + this.shortName;
    }
}

