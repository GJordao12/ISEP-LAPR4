package eapli.helpdesk.servico.application;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.helpdesk.atividade.AtividadeAutomatica;
import eapli.helpdesk.atividade.AtividadeManual;
import eapli.helpdesk.atividade.Script;
import eapli.helpdesk.servico.domain.*;
import eapli.helpdesk.servico.repositories.*;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumerico;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumericoException;
import eapli.helpdesk.tipoEquipa.domain.Descricao;
import eapli.helpdesk.tipoEquipa.domain.DescricaoException;
import eapli.helpdesk.catalogo.domain.Catalogo;
import eapli.helpdesk.catalogo.domain.DescricaoBreve;
import eapli.helpdesk.catalogo.domain.Titulo;
import eapli.helpdesk.catalogo.exceptions.DescricaoBreveException;
import eapli.helpdesk.catalogo.exceptions.TituloException;
import eapli.helpdesk.catalogo.repositories.CatalogoRepository;
import eapli.helpdesk.criticidade.domain.Criticidade;
import eapli.helpdesk.criticidade.repositories.CriticidadeRepository;
import eapli.helpdesk.infraestrutura.PersistenceContext;
import eapli.helpdesk.user.domain.HelpdeskRoles;
import org.apache.commons.io.IOUtils;

import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ChangeServicoController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final TransactionalContext ctx = PersistenceContext.repositories().newTransactionalContext();
    private final ServicoRepository repositoryServico = PersistenceContext.repositories().servico(ctx);
    private final ServicoRepository servicoRepositoryWithoutTransaction =PersistenceContext.repositories().servico();
    private final FormularioRepository repositoryFormulario = PersistenceContext.repositories().formulario(ctx);
    private final CatalogoRepository repositoryCatalogo = PersistenceContext.repositories().catalogo();
    private final CriticidadeRepository repositoryCriticidade= PersistenceContext.repositories().criticidades();
    private final WorkflowRepository repositoryWorkflow=PersistenceContext.repositories().workflow(ctx);
    private final List<AtributoFormulario>atributosFormularioSolicitacao=new ArrayList<>();
    private final List<AtributoFormulario>atributosFormularioAtividadeManualExecucao=new ArrayList<>();
    private final List<AtributoFormulario>atributosFormularioAtividadeManualAprovacao=new ArrayList<>();
    private Formulario formularioDeSolicitacao;
    private AtividadeManual atividadeManualAprovacao=null;
    private Formulario formularioAtividadeManualAprovacao;
    private AtividadeManual atividadeManualExecucao=null;
    private Formulario formularioAtividadeManualExecucao;
    private AtividadeAutomatica atividadeAutomatica=null;
    private Workflow workflow;



    public void registerWorkflowTransaction(Servico servico){
        ctx.beginTransaction();
        changeWorkflow(servico);
        ctx.commit();
    }

    @Transactional
    public void changeWorkflowTransaction(Servico servico){
        ctx.beginTransaction();
        removeWorkflow(servico);
        changeWorkflow(servico);
        ctx.commit();
    }

    public void registerFormularioTransaction(Servico servico){

        ctx.beginTransaction();

            this.repositoryFormulario.save(formularioDeSolicitacao);

        changeFormulario(servico);
        ctx.commit();
    }


    public Iterable<Servico> findAllServicos(){
        return this.repositoryServico.findAll();
    }

    public void changeTitulo(final Servico servico, final String newTitulo) throws TituloException {
        authz.ensureAuthenticatedUserHasAnyOf(HelpdeskRoles.POWER_USER,HelpdeskRoles.SERVICE_MANAGER);
        Titulo newTitulo1=new Titulo(newTitulo);
        servico.changeTituloTo(newTitulo1);
        this.servicoRepositoryWithoutTransaction.save(servico);
    }

    public void changeDescricaoBreve(final Servico servico, final String newdescricaoBreve) throws DescricaoBreveException {
        authz.ensureAuthenticatedUserHasAnyOf(HelpdeskRoles.POWER_USER,HelpdeskRoles.SERVICE_MANAGER);
        DescricaoBreve newdescricaoBreve1=new DescricaoBreve(newdescricaoBreve);
        servico.changeDescricaoBreveTo(newdescricaoBreve1);
        this.servicoRepositoryWithoutTransaction.save(servico);
    }

    public void changeDescricaoCompleta(final Servico servico, final String newdescricaoCompleta) throws  DescricaoCompletaServicoException {
        authz.ensureAuthenticatedUserHasAnyOf(HelpdeskRoles.POWER_USER,HelpdeskRoles.SERVICE_MANAGER);
        DescricaoCompletaServico newdescricaoCompleta1=new DescricaoCompletaServico(newdescricaoCompleta);
        servico.changeDescricaoCompletaTo(newdescricaoCompleta1);
        this.servicoRepositoryWithoutTransaction.save(servico);
    }

    public void changeFeedback(final Servico servico, final String feedback){
        authz.ensureAuthenticatedUserHasAnyOf(HelpdeskRoles.POWER_USER,HelpdeskRoles.SERVICE_MANAGER);
        servico.changeFeedbackTo(feedback);
        this.servicoRepositoryWithoutTransaction.save(servico);
    }
    public void changeTempoLimiteFeedback(final Servico servico, final String tempoLimiteFeedback){
        authz.ensureAuthenticatedUserHasAnyOf(HelpdeskRoles.POWER_USER,HelpdeskRoles.SERVICE_MANAGER);
        servico.changeTempoLimiteFeedback(Integer.parseInt(tempoLimiteFeedback));
        this.servicoRepositoryWithoutTransaction.save(servico);
    }

    public Servico changeIcone(final Servico servico,final String icone) throws IOException {
        authz.ensureAuthenticatedUserHasAnyOf(HelpdeskRoles.POWER_USER,HelpdeskRoles.SERVICE_MANAGER);
        final InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(icone);
        if (inputStream == null) {
            throw new FileNotFoundException("Imagem " + icone + " não encontrada!");
        }
        servico.changeIcone(IOUtils.toByteArray(inputStream));
        return this.servicoRepositoryWithoutTransaction.save(servico);
    }

    public void changeKeywords(final Servico servico, final Set<String> keywords){
        authz.ensureAuthenticatedUserHasAnyOf(HelpdeskRoles.POWER_USER,HelpdeskRoles.SERVICE_MANAGER);
        servico.changeKeywords(keywords);
        this.servicoRepositoryWithoutTransaction.save(servico);
    }

    public void changeFormulario(final Servico servico){
        authz.ensureAuthenticatedUserHasAnyOf(HelpdeskRoles.POWER_USER,HelpdeskRoles.SERVICE_MANAGER);
        servico.changeFormularioSolicitacaoTo(formularioDeSolicitacao);
        this.repositoryServico.save(servico);
    }

    public void changeCriticidade(final Servico servico, final Criticidade criticidade){
        authz.ensureAuthenticatedUserHasAnyOf(HelpdeskRoles.POWER_USER,HelpdeskRoles.SERVICE_MANAGER);
        servico.changeCriticidadeTo(criticidade);
        this.servicoRepositoryWithoutTransaction.save(servico);
    }

    public void changeWorkflow(final Servico servico){
        authz.ensureAuthenticatedUserHasAnyOf(HelpdeskRoles.POWER_USER,HelpdeskRoles.SERVICE_MANAGER);
        servico.changeWorkflowTo(this.workflow);
        this.repositoryServico.save(servico);
    }

    public void registerAtributoFormularioSolicitacao(String nomeAtributo, String descricao, String expressaoRegular, String tipoDados, String etiqueta) throws DescricaoException, TipoDadosException {
        authz.ensureAuthenticatedUserHasAnyOf(HelpdeskRoles.POWER_USER,HelpdeskRoles.SERVICE_MANAGER);
        Nome nomeAtributo1=new Nome(nomeAtributo);
        Descricao descricao1=new Descricao(descricao);
        TipoDados tipoDados1=new TipoDados(tipoDados);
        Etiqueta etiqueta1 = new Etiqueta(etiqueta);
        AtributoFormulario atributo =new AtributoFormulario(nomeAtributo1,descricao1,expressaoRegular,tipoDados1, etiqueta1);
        this.atributosFormularioSolicitacao.add(atributo);
    }

    public void registerAtributoFormularioAtividadeManualExecucao(String nomeAtributo, String descricao, String expressaoRegular, String tipoDados, String etiqueta) throws DescricaoException, TipoDadosException {
        authz.ensureAuthenticatedUserHasAnyOf(HelpdeskRoles.POWER_USER,HelpdeskRoles.SERVICE_MANAGER);
        Nome nomeAtributo1=new Nome(nomeAtributo);
        Descricao descricao1=new Descricao(descricao);
        TipoDados tipoDados1=new TipoDados(tipoDados);
        Etiqueta etiqueta1 = new Etiqueta(etiqueta);
        AtributoFormulario atributo =new AtributoFormulario(nomeAtributo1,descricao1,expressaoRegular,tipoDados1, etiqueta1);
        this.atributosFormularioAtividadeManualExecucao.add( atributo);
    }

    public void registerAtributoFormularioAtividadeManualAprovacao(String nomeAtributo, String descricao, String expressaoRegular, String tipoDados, String etiqueta) throws DescricaoException, TipoDadosException {
        authz.ensureAuthenticatedUserHasAnyOf(HelpdeskRoles.POWER_USER,HelpdeskRoles.SERVICE_MANAGER);
        Nome nomeAtributo1=new Nome(nomeAtributo);
        Descricao descricao1=new Descricao(descricao);
        TipoDados tipoDados1=new TipoDados(tipoDados);
        Etiqueta etiqueta1 = new Etiqueta(etiqueta);
        AtributoFormulario atributo =new AtributoFormulario(nomeAtributo1,descricao1,expressaoRegular,tipoDados1, etiqueta1);
        this.atributosFormularioAtividadeManualAprovacao.add( atributo);
    }

    public void registerFormularioSolicitacao(String codAlfaNum, String nome, String etiqueta, Script script) throws CodigoAlfaNumericoException {
        authz.ensureAuthenticatedUserHasAnyOf(HelpdeskRoles.POWER_USER,HelpdeskRoles.SERVICE_MANAGER);
        CodigoAlfaNumerico cod=new CodigoAlfaNumerico(codAlfaNum);
        Nome nome1=new Nome(nome);
        Etiqueta etiqueta1=new Etiqueta(etiqueta);
        this.formularioDeSolicitacao=new Formulario(cod,nome1,etiqueta1,script,atributosFormularioSolicitacao);
    }

    public void registerFormularioAtividadeManualExecucao(String codAlfaNum, String nome, String etiqueta, Script script) throws CodigoAlfaNumericoException {
        authz.ensureAuthenticatedUserHasAnyOf(HelpdeskRoles.POWER_USER,HelpdeskRoles.SERVICE_MANAGER);
        CodigoAlfaNumerico cod=new CodigoAlfaNumerico(codAlfaNum);
        Nome nome1=new Nome(nome);
        Etiqueta etiqueta1=new Etiqueta(etiqueta);
        this.formularioAtividadeManualExecucao=new Formulario(cod,nome1,etiqueta1,script,atributosFormularioAtividadeManualExecucao);
    }

    public void registerFormularioAtividadeManualAprovacao(String codAlfaNum, String nome, String etiqueta, Script script) throws CodigoAlfaNumericoException {
        authz.ensureAuthenticatedUserHasAnyOf(HelpdeskRoles.POWER_USER,HelpdeskRoles.SERVICE_MANAGER);
        CodigoAlfaNumerico cod=new CodigoAlfaNumerico(codAlfaNum);
        Nome nome1=new Nome(nome);
        Etiqueta etiqueta1=new Etiqueta(etiqueta);
        this.formularioAtividadeManualAprovacao=new Formulario(cod,nome1,etiqueta1,script,atributosFormularioAtividadeManualAprovacao);
    }

    public void registerAtividadeManualExecucao() throws CodigoAlfaNumericoException {
        this.atividadeManualExecucao= new AtividadeManual( this.formularioAtividadeManualExecucao);
    }

    public void registerAtividadeManualAprovacao() throws CodigoAlfaNumericoException {
        this.atividadeManualAprovacao= new AtividadeManual( this.formularioAtividadeManualAprovacao);
    }

    public void changeListaServicoCatalogo(final Catalogo catalogo, final List<Servico> servicos) {
        authz.ensureAuthenticatedUserHasAnyOf(HelpdeskRoles.POWER_USER,HelpdeskRoles.SERVICE_MANAGER);
        catalogo.changeListaDeServicosTo(servicos);
        this.repositoryCatalogo.save(catalogo);
    }

    public void registerWorkflow( String diaMudanca) throws CodigoAlfaNumericoException, ParseException {
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(formatoData.parse(diaMudanca));
        this.workflow= new Workflow(c,this.atividadeManualAprovacao, this.atividadeManualExecucao, this.atividadeAutomatica);
    }

    public void changeEstadoServico(final String estado, final Servico servico){
        authz.ensureAuthenticatedUserHasAnyOf(HelpdeskRoles.POWER_USER,HelpdeskRoles.SERVICE_MANAGER);
        servico.changeEstadoServicoTo(estado);
        this.servicoRepositoryWithoutTransaction.save(servico);
    }

    public Iterable<Criticidade> allCriticidades() { return repositoryCriticidade.findAll(); }

    public Iterable<Catalogo> findAllCatalogos(){
        return this.repositoryCatalogo.findAll();
    }

    public void removeWorkflow(Servico servico){
        repositoryWorkflow.remove(servico.workflow());
    }
    public void registerAtividadeAutomatica(Script script) throws CodigoAlfaNumericoException {
        this.atividadeAutomatica= new AtividadeAutomatica(script);
    }
}
