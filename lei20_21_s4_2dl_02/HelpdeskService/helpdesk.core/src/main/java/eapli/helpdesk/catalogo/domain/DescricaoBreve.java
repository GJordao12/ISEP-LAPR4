package eapli.helpdesk.catalogo.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import eapli.helpdesk.catalogo.exceptions.DescricaoBreveException;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class DescricaoBreve implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;
    private String descBreve;

    public DescricaoBreve(final String descBreve) throws DescricaoBreveException {
        if(descBreve.length()>40 || StringPredicates.isNullOrEmpty(descBreve)) {
            throw new DescricaoBreveException("Descricao breve inválida");
        }
        this.descBreve = descBreve;
    }

    protected DescricaoBreve() {
        // for ORM
    }

    public static DescricaoBreve valueOf(final String descBreve) throws DescricaoBreveException {
        return new DescricaoBreve(descBreve);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DescricaoBreve)) {
            return false;
        }

        final DescricaoBreve that = (DescricaoBreve) o;
        return this.descBreve.equals(that.descBreve);
    }

    @Override
    public int hashCode() {
        return this.descBreve.hashCode();
    }

    @Override
    public String toString() {
        return this.descBreve;
    }

}