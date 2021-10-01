package eapli.helpdesk.tipoEquipa.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class TipoEquipaTest {

    private TipoEquipa tipoEquipa;
    private Cor cor;
    private CodigoAlfaNumerico codigoAlfaNumerico;
    private Descricao descricao;

    public TipoEquipaTest() throws CodigoAlfaNumericoException, CorException, DescricaoException {
        this.codigoAlfaNumerico = new CodigoAlfaNumerico("55AA");
        this.cor = new Cor("#FFFFFF");
        this.descricao = new Descricao("descricao");

        this.tipoEquipa = new TipoEquipa(cor,codigoAlfaNumerico,descricao);
    }

    @Test
    void testToString() {
        String s = String.format("Código Alfanumérico: %s - Descrição: %s - Cor: %s", this.codigoAlfaNumerico, this.descricao, this.cor);
        Assertions.assertEquals(s, this.tipoEquipa.toString());
    }

    @Test
    void codigoAlfaNumerico() {
        Assertions.assertEquals("55AA", this.tipoEquipa.codigoAlfaNumerico().toString());
    }

    @Test
    void identity() {
        Assertions.assertEquals("55AA", this.tipoEquipa.identity().toString());
    }
}