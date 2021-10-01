
package eapli.helpdesk.colaborador.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import eapli.helpdesk.colaborador.exception.PhoneNumberException;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

@Embeddable
public class PhoneNumber implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;
    @XmlElement
    @JsonProperty
    private String phoneNumber;

    public PhoneNumber(final String phoneNumber) throws PhoneNumberException {
        if (StringPredicates.isNullOrEmpty(phoneNumber) || phoneNumber.length()<9 || phoneNumber.length()>13
        || (!phoneNumber.matches("9([0-9]{8})") && !phoneNumber.matches("\\+3519([0-9]{8})"))) {
            throw new PhoneNumberException("Número de Contacto Inválido. Por favor insira um Número de Contacto válido.");
        }
        // TODO validate invariants, i.e., mecanographic number regular
        // expression
        this.phoneNumber = phoneNumber;
    }

    protected PhoneNumber() {
        // for ORM
    }

    public static PhoneNumber valueOf(final String phoneNumber) throws PhoneNumberException {
        return new PhoneNumber(phoneNumber);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PhoneNumber)) {
            return false;
        }

        final PhoneNumber that = (PhoneNumber) o;
        return this.phoneNumber.equals(that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return this.phoneNumber.hashCode();
    }

    @Override
    public String toString() {
        return this.phoneNumber;
    }
}

