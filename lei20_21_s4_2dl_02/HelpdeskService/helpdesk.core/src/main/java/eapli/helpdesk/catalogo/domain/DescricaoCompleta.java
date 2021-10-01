package eapli.helpdesk.catalogo.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import eapli.helpdesk.catalogo.exceptions.DescricaoCompletaException;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class DescricaoCompleta implements ValueObject, Serializable, Comparable<DescricaoCompleta> {
    private static final long serialVersionUID = 1L;

    private String descCompleta;

    public DescricaoCompleta(final String descCompleta) throws DescricaoCompletaException {
        if(descCompleta.length()>100 || StringPredicates.isNullOrEmpty(descCompleta)) {
            throw new DescricaoCompletaException("Descricao completa inválida");
        }
        this.descCompleta = descCompleta;
    }

    protected DescricaoCompleta() {
        // for ORM
    }

    public static DescricaoCompleta valueOf(final String descCompleta) throws DescricaoCompletaException {
        return new DescricaoCompleta(descCompleta);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DescricaoCompleta)) {
            return false;
        }

        final DescricaoCompleta that = (DescricaoCompleta) o;
        return this.descCompleta.equals(that.descCompleta);
    }

    @Override
    public int hashCode() {
        return this.descCompleta.hashCode();
    }

    @Override
    public String toString() {
        return this.descCompleta;
    }

    @Override
    public int compareTo(final DescricaoCompleta arg0) {
        return descCompleta.compareTo(arg0.descCompleta);
    }
}