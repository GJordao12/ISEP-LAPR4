package eapli.helpdesk.equipa.domain;

import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.equipa.exception.AcronymException;
import eapli.helpdesk.equipa.exception.DesignationException;
import eapli.helpdesk.tipoEquipa.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {

    private Team team;
    private CodigoAlfaNumerico codigoAlfaNumerico;
    private Designation designation;
    private Acronym acronym;
    private Cor cor;
    private CodigoAlfaNumerico codAN;
    private Descricao descricao;
    private TipoEquipa tipoEquipa;
    private List<Collaborator> listColab;
    private final List<Collaborator> list1 = new ArrayList<>();

    public TeamTest() throws CodigoAlfaNumericoException, DesignationException, AcronymException, CorException, DescricaoException {
        this.codigoAlfaNumerico = new CodigoAlfaNumerico("55AA");
        this.designation = new Designation("desig");
        this.acronym = new Acronym("Acronimo89");
        this.cor = new Cor("#FFFFFF");
        this.codAN = new CodigoAlfaNumerico("55AA36");
        this.descricao = new Descricao("descricao");
        this.tipoEquipa = new TipoEquipa(cor, codAN, descricao);
        this.listColab = new ArrayList<>();

        this.team = new Team(codigoAlfaNumerico, acronym, designation, tipoEquipa, listColab, listColab);
    }

    @Test
    void ensureValidationCodAlfa() {
        boolean flag = false;
        try {
            new CodigoAlfaNumerico("");
            flag = true;
        } catch (CodigoAlfaNumericoException e) {
            assertFalse(flag);
        }
        boolean flag1 = false;
        try {
            new CodigoAlfaNumerico("4444");
            flag1 = true;
        } catch (CodigoAlfaNumericoException e) {
            assertFalse(flag1);
        }
        boolean flag2 = false;
        try {
            new CodigoAlfaNumerico("AAAA");
            flag2 = true;
        } catch (CodigoAlfaNumericoException e) {
            assertFalse(flag2);
        }
    }

    @Test
    void ensureValidationDesignation() {
        boolean flag = false;
        try {
            new Designation("");
            flag = true;
        } catch (DesignationException e) {
            assertFalse(flag);
        }
        boolean flag1 = false;
        try {
            new CodigoAlfaNumerico("designacao");
            flag1 = true;
            assertTrue(flag1);
        } catch (CodigoAlfaNumericoException e) {
            assertFalse(flag1);
        }
    }

    @Test
    void ensureValidationAcronym() {
        boolean flag = false;
        try {
            new Acronym("");
            flag = true;
        } catch (AcronymException e) {
            assertFalse(flag);
        }
        boolean flag1 = false;
        try {
            new Acronym("4444");
            flag1 = true;
        } catch (AcronymException e) {
            assertFalse(flag1);
        }
        boolean flag2 = false;
        try {
            new Acronym("AAAA");
            flag2 = true;
        } catch (AcronymException e) {
            assertFalse(flag2);
        }
        boolean flag3 = false;
        try {
            new Acronym("AAAA44447642");
            flag3 = true;
        } catch (AcronymException e) {
            assertFalse(flag3);
        }
    }

    @Test
    void testToString() {
        String s = "Código Alfanumérico: " + this.codigoAlfaNumerico + " - Designação: " + this.designation +
                " - Acrónimo: " + this.acronym + " - Tipo de Equipa } " + this.tipoEquipa.toString();
        Assertions.assertEquals(s, this.team.toString());
    }

    @Test
    void codigoAlfaNumerico() {
        Assertions.assertEquals("55AA", this.team.codigoAlfaNumerico().toString());
    }

    @Test
    void listMembers() {
        Assertions.assertEquals(this.list1, this.listColab);
    }

    @Test
    void identity() {
        Assertions.assertEquals("55AA", this.team.identity().toString());
    }

    @Test
    void teamType() throws CorException, CodigoAlfaNumericoException, DescricaoException {
        Cor cor1 = new Cor("#FFFFFF");
        CodigoAlfaNumerico cod1 = new CodigoAlfaNumerico("55AA36");
        Descricao descricao1 = new Descricao("descricao");
        TipoEquipa te = new TipoEquipa(cor1, cod1, descricao1);
        Assertions.assertEquals(te.toString(), this.tipoEquipa.toString());
    }
}