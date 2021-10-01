package eapli.helpdesk.catalogo.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.helpdesk.catalogo.exceptions.IdException;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Id implements ValueObject, Serializable, Comparable<Id> {
    private static final long serialVersionUID = 1L;

    private int id;

    public Id(final int id) throws IdException {
        if(id <= 0) {
            throw new IdException("Id inválido");
        }
        this.id = id;
    }

    protected Id() {
        // for ORM
    }

    public static Id valueOf(final int id) throws IdException {
        return new Id(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Id)) return false;
        Id id1 = (Id) o;
        return id == id1.id;
    }

    @Override
    public String toString() {
        return "" + id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Id o) {
        return 0;
    }
}