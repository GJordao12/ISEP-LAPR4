package eapli.helpdesk.servico.domain;


import eapli.helpdesk.catalogo.domain.DescricaoBreve;
import eapli.helpdesk.catalogo.domain.Titulo;
import eapli.helpdesk.catalogo.exceptions.DescricaoBreveException;
import eapli.helpdesk.catalogo.exceptions.TituloException;
import eapli.helpdesk.criticidade.domain.Criticidade;
import eapli.helpdesk.criticidade.domain.Tempo;
import eapli.helpdesk.criticidade.domain.Valor;
import eapli.helpdesk.criticidade.exception.TempoException;
import eapli.helpdesk.criticidade.exception.ValorException;
import eapli.helpdesk.tipoEquipa.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.beans.DesignMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class ServicoTest {

    private CodigoAlfaNumerico codAlfaNum;

    private DescricaoBreve descricaoBreve;

    private DescricaoCompletaServico descricaoCompletaServico;

    private String estadoServico;

    private String feedback;

    private Integer tempoLimiteFeedback;

    private Titulo titulo;

    private byte[] icone;

    private Set<String> keywords = new HashSet<>();

    private Criticidade criticidade;

    private Formulario formularioDeSolicitacao;

    private Servico servico;

    private List<AtributoFormulario> atributos =new ArrayList<>();

    private AtributoFormulario atributo1;

    private Workflow workflow;

    public ServicoTest() throws  DescricaoCompletaServicoException, DescricaoBreveException, TituloException, CorException, CodigoAlfaNumericoException, TipoDadosException, DescricaoException, ValorException, TempoException {
        this.codAlfaNum = new CodigoAlfaNumerico("2we");
        this.descricaoCompletaServico=new DescricaoCompletaServico("Servico completa");
        this.estadoServico="Incompleto";
        this.descricaoBreve=new DescricaoBreve("Servico breve");
        this.feedback="S";
        this.tempoLimiteFeedback=3;
        this.keywords.add("servico");
        this.titulo=new Titulo("Servico de resolucao");
        this.atributo1=new AtributoFormulario(new Nome("at1"),new Descricao("eee"),"[0-9]*",new TipoDados("integer"),new Etiqueta("eerr44"));
        this.atributos.add(atributo1);
        this.formularioDeSolicitacao=new Formulario(new CodigoAlfaNumerico("ee33"),new Nome("Formulario1"),new Etiqueta("ww33"),null,atributos);
        this.criticidade=new Criticidade(new Valor(3),new Descricao("crit1"),new Cor("#FFFFFF"),"WE2",new Tempo(2),new Tempo(3),new Tempo(4),new Tempo(5));
       this.workflow=new Workflow(null,null,null,null);
        this.servico= new Servico(codAlfaNum,descricaoBreve, descricaoCompletaServico,
                estadoServico, feedback, tempoLimiteFeedback, titulo, icone,
         keywords, formularioDeSolicitacao, criticidade,workflow);
    }

    @Test
    void codigoAlfaNumerico() {
        Assertions.assertEquals("2we", this.servico.codigoAlfaNumerico().toString());
    }

    @Test
    void descricaoBreve() {
        Assertions.assertEquals("Servico breve", this.servico.descricaoBreve().toString());
    }

    @Test
    void descricaoCompleta() {
        Assertions.assertEquals("Servico completa", this.servico.descricaoCompleta().toString());
    }

    @Test
    void titulo() {
        Assertions.assertEquals("Servico de resolucao", this.servico.titulo().toString());
    }

    @Test
    void estado() {
        Assertions.assertEquals("Incompleto", this.servico.estado());
    }

    @Test
    void feedback() {
        Assertions.assertEquals("S", this.servico.feedback());
    }

    @Test
    void tempoLimiteFeedback() {
        Assertions.assertEquals(3, this.servico.tempoLimiteFeedback());
    }

    @Test
    void keywords() {
        Set<String> keywords2 = new HashSet<>();
        keywords2.add("servico");
        Assertions.assertEquals(keywords2.toString(), this.servico.keywords().toString());
    }

    @Test
    void criticidade() {
        String s=("Designação: crit1 Valor: 3");
        Assertions.assertEquals(s,this.criticidade.toString());
    }

    @Test
    void changeKeywords() {
        Set<String> keywords2 = new HashSet<>();
        keywords2.add("servico5");
        this.servico.changeKeywords(keywords2);
        Assertions.assertEquals(keywords2,this.servico.keywords());
    }

    @Test
    void changeDescricaoCompletaTo() throws DescricaoCompletaServicoException {
        DescricaoCompletaServico desc=new DescricaoCompletaServico("2222");
        this.servico.changeDescricaoCompletaTo(desc);
        Assertions.assertEquals(desc,this.servico.descricaoCompleta());
    }

    @Test
    void changeDescricaoBreveTo() throws DescricaoBreveException {
        DescricaoBreve desc=new DescricaoBreve("2222");
        this.servico.changeDescricaoBreveTo(desc);
        Assertions.assertEquals(desc,this.servico.descricaoBreve());
    }

    @Test
    void changeTituloTo() throws TituloException {
        Titulo desc=new Titulo("2222");
        this.servico.changeTituloTo(desc);
        Assertions.assertEquals(desc,this.servico.titulo());
    }

    @Test
    void changeFeedbackTo() {
        String feed="n";
        this.servico.changeFeedbackTo(feed);
        Assertions.assertEquals(feed,this.servico.feedback());
    }

    @Test
    void changeTempoLimiteFeedback() {
        int n=6;
        this.servico.changeTempoLimiteFeedback(n);
        Assertions.assertEquals(n,this.servico.tempoLimiteFeedback());
    }

    @Test
    void identity() {
        Assertions.assertEquals("2we", this.servico.identity().toString());
    }

}
