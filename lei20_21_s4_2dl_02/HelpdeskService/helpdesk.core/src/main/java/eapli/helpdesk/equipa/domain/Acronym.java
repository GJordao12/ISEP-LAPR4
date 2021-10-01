package eapli.helpdesk.equipa.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import eapli.helpdesk.equipa.exception.AcronymException;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Acronym implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;

    private String acronym;

    public Acronym(final String acronym) throws AcronymException {
        if(!acronym.matches("^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z0-9]+$")|| StringPredicates.isNullOrEmpty(acronym)
                || acronym.length()>10) {
            throw new AcronymException("Acrónimo Inválido. Por favor insira um Acrónimo válido.");
        }
        this.acronym = acronym;
    }

    protected Acronym() {
        // for ORM
    }

    public static Acronym valueOf(final String acronym) throws AcronymException {
        return new Acronym(acronym);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Acronym)) {
            return false;
        }

        final Acronym that = (Acronym) o;
        return this.acronym.equals(that.acronym);
    }

    @Override
    public int hashCode() {
        return this.acronym.hashCode();
    }

    @Override
    public String toString() {
        return this.acronym;
    }
}
