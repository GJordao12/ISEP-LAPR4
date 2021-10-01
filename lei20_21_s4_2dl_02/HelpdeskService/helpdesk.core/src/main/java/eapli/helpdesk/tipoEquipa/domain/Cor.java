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

@Embeddable
public class Cor implements ValueObject, Serializable, Comparable<Cor> {
    private static final long serialVersionUID = 1L;

    private String cor;

    public Cor(final String cor) throws CorException {
        if(!cor.matches("#([A-F]|[0-9]){6}")) {
            throw new CorException("Cor inválida");
        }
        this.cor = cor;
    }

    protected Cor() {
        // for ORM
    }

    public static Cor valueOf(final String cor) throws CorException {
        return new Cor(cor);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cor)) {
            return false;
        }

        final Cor that = (Cor) o;
        return this.cor.equals(that.cor);
    }

    @Override
    public int hashCode() {
        return this.cor.hashCode();
    }

    @Override
    public String toString() {
        return this.cor;
    }

    @Override
    public int compareTo(final Cor arg0) {
        return cor.compareTo(arg0.cor);
    }

    public String cor (){
        return this.cor;
    }

    public void changeCorTo(String cor){
        this.cor=cor;
    }
}

