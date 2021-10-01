package eapli.helpdesk.catalogo.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import eapli.helpdesk.catalogo.exceptions.TituloException;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Titulo implements ValueObject, Serializable, Comparable<Titulo> {
    private static final long serialVersionUID = 1L;

    private String titulo;

    public Titulo(final String titulo) throws TituloException {
        if(titulo.length() > 50 || StringPredicates.isNullOrEmpty(titulo)) {
            throw new TituloException("Titulo inválido");
        }
        this.titulo = titulo;
    }

    protected Titulo() {
        // for ORM
    }

    public static Titulo valueOf(final String titulo) throws TituloException {
        return new Titulo(titulo);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Titulo)) {
            return false;
        }

        final Titulo that = (Titulo) o;
        return this.titulo.equals(that.titulo);
    }

    @Override
    public int hashCode() {
        return this.titulo.hashCode();
    }

    @Override
    public String toString() {
        return this.titulo;
    }

    @Override
    public int compareTo(final Titulo arg0) {
        return titulo.compareTo(arg0.titulo);
    }
}