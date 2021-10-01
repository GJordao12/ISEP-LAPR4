package eapli.helpdesk.atividade;

import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import java.io.File;
import java.io.Serializable;

@Embeddable
public class Script implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;

    private File script;

    public Script(final File script) {
        this.script = script;
    }

    protected Script() {
        // for ORM
    }

    public static Script valueOf(final File script) {
        return new Script(script);
    }

    @Override
    public String toString() {
        return this.script.toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Script)) {
            return false;
        }

        final Script that = (Script) o;
        return this.script.equals(that.script);
    }

    @Override
    public int hashCode() {
        return this.script.hashCode();
    }

    public File script() {
        return this.script;
    }
}
