package eapli.helpdesk.servico.domain;
/*
 * Copyright (c) 2013-2021 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
import java.io.Serializable;
import javax.persistence.Embeddable;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

@Embeddable
public class TipoDados implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;
    private String tipoDados;

    public TipoDados(final String tipoDados) throws TipoDadosException {
        if (tipoDados.length() > 50 || StringPredicates.isNullOrEmpty(tipoDados)) {
            throw new TipoDadosException("TipoDados inválido");
        }
        this.tipoDados = tipoDados;
    }

    protected TipoDados() {
        // for ORM
    }

    public static TipoDados valueOf(final String tipoDados) throws TipoDadosException {
        return new TipoDados(tipoDados);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TipoDados)) {
            return false;
        }

        final TipoDados that = (TipoDados) o;
        return this.tipoDados.equals(that.tipoDados);
    }

    @Override
    public int hashCode() {
        return this.tipoDados.hashCode();
    }

    @Override
    public String toString() {
        return this.tipoDados;
    }

}