package eapli.helpdesk.criticidade.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.helpdesk.criticidade.exception.ValorException;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Valor implements ValueObject, Serializable, Comparable<Valor> {

    private static final long serialVersionUID = 1L;
    private Integer valor;

    public Valor(final Integer valor) throws ValorException {
        if (valor < 0) {
            throw new ValorException();
        }
        this.valor = valor;
    }

    protected Valor() {
        // for ORM
    }

    public static Valor valueOf(final Integer valor) throws ValorException {
        return new Valor(valor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Valor)) return false;
        Valor valor1 = (Valor) o;
        return valor.equals(valor1.valor);
    }

    @Override
    public int hashCode() {
        return this.valor.hashCode();
    }

    @Override
    public String toString() {
        return "" + this.valor;
    }

    @Override
    public int compareTo(Valor o) {
        return this.valor.compareTo(o.valor);
    }
}
