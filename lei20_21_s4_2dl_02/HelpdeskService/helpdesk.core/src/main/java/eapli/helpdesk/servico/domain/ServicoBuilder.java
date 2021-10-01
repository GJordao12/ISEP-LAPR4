package eapli.helpdesk.servico.domain;

import eapli.framework.domain.model.DomainFactory;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumerico;
import eapli.helpdesk.catalogo.domain.DescricaoBreve;
import eapli.helpdesk.catalogo.domain.Titulo;
import eapli.helpdesk.criticidade.domain.Criticidade;

import java.util.HashSet;
import java.util.Set;

public class ServicoBuilder implements DomainFactory<Servico> {

    private CodigoAlfaNumerico codAlfaNum;

    private DescricaoBreve descricaoBreve;

    private DescricaoCompletaServico descricaoCompletaServico;

    private String estadoServico;

    private String feedback;

    private Integer tempoLimiteFeedback;

    private Titulo titulo;

    private byte[] icone;

    private Set<String> keywords = new HashSet<>();

    private Formulario formularioDeSolicitacao;

    private Criticidade criticidade;

    private Workflow workflow;


    public ServicoBuilder() {
    }

    public ServicoBuilder withData(final CodigoAlfaNumerico codAlfaNum, final Titulo titulo) {
        this.codAlfaNum = codAlfaNum;
        this.titulo=titulo;
        return this;
    }

    public ServicoBuilder withDescricaoBreve(final DescricaoBreve descricaoBreve) {
        this.descricaoBreve = descricaoBreve;
        return this;
    }

    public ServicoBuilder withDescricaoCompleta(final DescricaoCompletaServico descricaoCompletaServico) {
        this.descricaoCompletaServico=descricaoCompletaServico;
        return this;
    }

    public ServicoBuilder withFeedback(final String feedback) {
        this.feedback=feedback;
        return this;
    }

    public ServicoBuilder withTempoLimiteFeedback(final Integer tempoLimiteFeedback){
        this.tempoLimiteFeedback=tempoLimiteFeedback;
        return this;
    }

    public ServicoBuilder withIcone(final byte[] icone){
        this.icone=icone;
        return this;
    }

    public ServicoBuilder withKeywords(final Set<String> keywords){
        this.keywords=keywords;
        return this;
    }

    public ServicoBuilder withFormularioDeSolicitacao(final Formulario formularioDeSolicitacao){
        this.formularioDeSolicitacao=formularioDeSolicitacao;
        return this;
    }

    public ServicoBuilder withCriticidade(final Criticidade criticidade){
        this.criticidade=criticidade;
        return this;
    }

    public ServicoBuilder withWorkflow(final Workflow workflow){
        this.workflow=workflow;
        return this;
    }
    public ServicoBuilder withEstadoServico(final String estadoServico){
        this.estadoServico=estadoServico;
        return this;
    }

    @Override
    public Servico build() {
        return new Servico(this.codAlfaNum,this.descricaoBreve,this.descricaoCompletaServico,this.estadoServico,this.feedback,
                this.tempoLimiteFeedback,this.titulo,this.icone,this.keywords,this.formularioDeSolicitacao,this.criticidade,this.workflow);
    }
}
