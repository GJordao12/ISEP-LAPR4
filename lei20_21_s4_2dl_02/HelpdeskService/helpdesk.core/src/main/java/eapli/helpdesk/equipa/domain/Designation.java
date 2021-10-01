package eapli.helpdesk.equipa.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import eapli.helpdesk.equipa.exception.DesignationException;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Designation implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;

    private String designation;

    public Designation(final String designation) throws DesignationException {
        if(designation.length()>50 || StringPredicates.isNullOrEmpty(designation)) {
            throw new DesignationException("Designação inválida. Por favor insira uma Designação válida.");
        }
        this.designation = designation;
    }

    protected Designation() {
        // for ORM
    }

    public static Designation valueOf(final String designation) throws DesignationException {
        return new Designation(designation);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Designation)) {
            return false;
        }

        final Designation that = (Designation) o;
        return this.designation.equals(that.designation);
    }

    @Override
    public int hashCode() {
        return this.designation.hashCode();
    }

    @Override
    public String toString() {
        return this.designation;
    }
}
