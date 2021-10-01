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
package eapli.helpdesk.tipoEquipa.domain;
import java.io.Serializable;
import javax.persistence.Embeddable;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

@Embeddable
public class Descricao implements ValueObject, Serializable, Comparable<Descricao> {

    private static final long serialVersionUID = 1L;

    private String descricao;

    public Descricao(final String descricao) throws DescricaoException {
        if(descricao.length()>50 || StringPredicates.isNullOrEmpty(descricao)) {
            throw new DescricaoException("Descricao inválida");
        }
        this.descricao = descricao;
    }

    protected Descricao() {
        // for ORM
    }

    public static Descricao valueOf(final String descricao) throws DescricaoException {
        return new Descricao(descricao);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Descricao)) {
            return false;
        }

        final Descricao that = (Descricao) o;
        return this.descricao.equals(that.descricao);
    }

    @Override
    public int hashCode() {
        return this.descricao.hashCode();
    }

    @Override
    public String toString() {
        return this.descricao;
    }

    @Override
    public int compareTo(final Descricao arg0) {
        return descricao.compareTo(arg0.descricao);
    }

    public String descricao(){
        return this.descricao;
    }
    public void changeDescricaoTo(String descricao){
        this.descricao=descricao;
    }

}