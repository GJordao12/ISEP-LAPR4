package eapli.helpdesk.criticidade.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.helpdesk.criticidade.exception.TempoException;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Tempo implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;
    private Integer tempo;

    public Tempo(final Integer tempo) throws TempoException {
        if (tempo <= 0) {
            throw new TempoException();
        }
        this.tempo = tempo;
    }

    protected Tempo() {
        // for ORM
    }

    public static Tempo valueOf(final Integer tempo) throws TempoException {
        return new Tempo(tempo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tempo)) return false;
        Tempo tempo1 = (Tempo) o;
        return Objects.equals(tempo, tempo1.tempo);
    }

    @Override
    public int hashCode() {
        return this.tempo.hashCode();
    }

    public Integer tempo() {
        return tempo;
    }

    @Override
    public String toString() {
        return "" + this.tempo;
    }
}
