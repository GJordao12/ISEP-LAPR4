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
public class CodigoAlfaNumerico implements ValueObject, Serializable, Comparable<CodigoAlfaNumerico>{
    private static final long serialVersionUID = 1L;

    private String codAlfaNum;

  public CodigoAlfaNumerico(final String codAlfaNum) throws CodigoAlfaNumericoException {
      if(!codAlfaNum.matches("^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z0-9]+$")||StringPredicates.isNullOrEmpty(codAlfaNum)||codAlfaNum.length()>15) {
          throw new CodigoAlfaNumericoException("Codigo Alfa Numerico inválido");
      }
        this.codAlfaNum = codAlfaNum;
    }

    protected CodigoAlfaNumerico() {
        // for ORM
    }

    public static CodigoAlfaNumerico valueOf(final String codAlfaNum) throws CodigoAlfaNumericoException {
        return new CodigoAlfaNumerico(codAlfaNum);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CodigoAlfaNumerico)) {
            return false;
        }

        final CodigoAlfaNumerico that = (CodigoAlfaNumerico) o;
        return this.codAlfaNum.equals(that.codAlfaNum);
    }

    @Override
    public int hashCode() {
        return this.codAlfaNum.hashCode();
    }

    @Override
    public String toString() {
        return this.codAlfaNum;
    }

    @Override
    public int compareTo(final CodigoAlfaNumerico arg0) {
        return codAlfaNum.compareTo(arg0.codAlfaNum);
    }

    public String codAlfaNum(){
      return this.codAlfaNum;
    }

    public void changeCodAlfaNumTo(String codAlfaNum){
      this.codAlfaNum=codAlfaNum;
    }
}
