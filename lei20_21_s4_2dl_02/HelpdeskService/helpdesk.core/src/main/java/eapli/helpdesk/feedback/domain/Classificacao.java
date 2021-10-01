package eapli.helpdesk.feedback.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.helpdesk.feedback.exception.ClassificacaoException;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Classificacao implements ValueObject, Serializable, Comparable<Classificacao> {

    private static final long serialVersionUID = 1L;

    private Integer classificacao;

    public Classificacao(final Integer classificacao) throws ClassificacaoException {
        if (classificacao < 0 || classificacao > 5) {
            throw new ClassificacaoException();
        }
        this.classificacao = classificacao;
    }

    protected Classificacao() {
        // for ORM
    }

    public static Classificacao valueOf(final Integer classificacao) throws ClassificacaoException {
        return new Classificacao(classificacao);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Classificacao)) return false;
        Classificacao classificacao1 = (Classificacao) o;
        return classificacao.equals(classificacao1.classificacao);
    }

    @Override
    public int hashCode() {
        return this.classificacao.hashCode();
    }

    @Override
    public String toString() {
        return "" + this.classificacao;
    }

    @Override
    public int compareTo(Classificacao o) {
        return this.classificacao.compareTo(o.classificacao);
    }

}
