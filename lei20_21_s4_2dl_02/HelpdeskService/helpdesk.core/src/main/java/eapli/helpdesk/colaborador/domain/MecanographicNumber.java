package eapli.helpdesk.colaborador.domain;/*
 * Copyright (c) 2013-2020 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
/*
package eapli.ecafeteria.CollaboratorManagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;

import javax.persistence.Embeddable;


/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import eapli.helpdesk.colaborador.exception.MecanographicNumberException;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MecanographicNumber implements ValueObject, Comparable<MecanographicNumber>, Serializable {
    private static final long serialVersionUID = 1L;
    private String mecanographicNumber;

    public MecanographicNumber(final String mecanographicNumber) throws MecanographicNumberException {
        if (StringPredicates.isNullOrEmpty(mecanographicNumber) || mecanographicNumber.length()>6 ||
        mecanographicNumber.length()==0 || !mecanographicNumber.matches("[0-9]{1,6}")) {
            throw new MecanographicNumberException("Número Mecanográfico Inválido. Por favor insira um Número Mecanográfico válido.");
        }
        // TODO validate invariants, i.e., mecanographic number regular
        // expression
        this.mecanographicNumber = mecanographicNumber;
    }

    protected MecanographicNumber() {
        // for ORM
    }

    public static MecanographicNumber valueOf(final String mecanographicNumber) throws MecanographicNumberException {
        return new MecanographicNumber(mecanographicNumber);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MecanographicNumber)) {
            return false;
        }

        final MecanographicNumber that = (MecanographicNumber) o;
        return this.mecanographicNumber.equals(that.mecanographicNumber);
    }

    @Override
    public int hashCode() {
        return this.mecanographicNumber.hashCode();
    }

    @Override
    public String toString() {
        return "Número Mecanográfico: " + this.mecanographicNumber;
    }

    @Override
    public int compareTo(final MecanographicNumber arg0) {
        return mecanographicNumber.compareTo(arg0.mecanographicNumber);
    }
}
