package eapli.helpdesk.colaborador.domain;

import eapli.helpdesk.colaborador.exception.LongNameException;
import eapli.helpdesk.colaborador.exception.MecanographicNumberException;
import eapli.helpdesk.colaborador.exception.PhoneNumberException;
import eapli.helpdesk.colaborador.exception.ShortNameException;
import eapli.helpdesk.equipa.domain.Designation;
import eapli.helpdesk.equipa.exception.DesignationException;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumerico;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumericoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class CollaboratorTest {

    private MecanographicNumber mecanographicNumber;

    private ShortName shortName;

    private LongName longName;

    private Calendar birthDate;

    private PhoneNumber phoneNumber;

    private RoleCollaborator roleCollaborator;

    private Collaborator collaborator;

    public CollaboratorTest() throws MecanographicNumberException, ShortNameException, LongNameException, PhoneNumberException, CodigoAlfaNumericoException, DesignationException {
        this.mecanographicNumber = new MecanographicNumber("11905");
        this.shortName = new ShortName("Tiago Jorge");
        this.longName = new LongName("Tiago Jorge Sampaio");
        this.phoneNumber = new PhoneNumber("966999967");
        CodigoAlfaNumerico cod = new CodigoAlfaNumerico("666AA");
        Designation desig = new Designation("Designacao");
        this.roleCollaborator = new RoleCollaborator(cod, desig);
    }

    @Test
    void ensureValidationMecanNumber() {
        boolean flag = false;
        try {
            new MecanographicNumber("");
            flag = true;
        } catch (MecanographicNumberException e) {
            assertFalse(flag);
        }
        boolean flag1 = false;
        try {
            new MecanographicNumber("99999999999999");
            flag1 = true;
        } catch (MecanographicNumberException e) {
            assertFalse(flag1);
        }
        boolean flag2 = false;
        try {
            new MecanographicNumber("967653545");
            flag2 = true;
        } catch (MecanographicNumberException e) {
            assertFalse(flag2);
        }
        boolean flag3 = false;
        try {
            new MecanographicNumber("+351967653545");
            flag3 = true;
        } catch (MecanographicNumberException e) {
            assertFalse(flag3);
        }
    }

    @Test
    void ensureValidationShortName() {
        boolean flag = false;
        try {
            new ShortName("");
            flag = true;
        } catch (ShortNameException e) {
            assertFalse(flag);
        }
        boolean flag1 = false;
        try {
            new ShortName("Joaquim");
            flag1 = true;
        } catch (ShortNameException e) {
            assertFalse(flag1);
        }
        boolean flag2 = false;
        try {
            new ShortName("Joaquim Santos");
            flag2 = true;
        } catch (ShortNameException e) {
            assertFalse(flag2);
        }
    }

    @Test
    void ensureValidationLongName() {
        boolean flag = false;
        try {
            new LongName("");
            flag = true;
        } catch (LongNameException e) {
            assertFalse(flag);
        }
        boolean flag1 = false;
        try {
            new LongName("Joaquim");
            flag1 = true;
        } catch (LongNameException e) {
            assertFalse(flag1);
        }
        boolean flag2 = false;
        try {
            new LongName("Joaquim Santos Sá");
            flag2 = true;
        } catch (LongNameException e) {
            assertFalse(flag2);
        }
    }

    @Test
    void ensureValidationRoleNull() {
        boolean flag = false;
        try {
            new RoleCollaborator(null, null);
            flag = true;
        } catch (IllegalArgumentException iae) {
            assertFalse(flag);
        }
    }

    @Test
    void mecanographicNumber() {
        Assertions.assertEquals("Número Mecanográfico: 11905", this.mecanographicNumber.toString());
    }


}