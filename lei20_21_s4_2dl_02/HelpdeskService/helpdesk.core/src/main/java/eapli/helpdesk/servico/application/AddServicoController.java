package eapli.helpdesk.servico.application;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.helpdesk.atividade.AtividadeAutomatica;
import eapli.helpdesk.atividade.AtividadeManual;
import eapli.helpdesk.atividade.Script;
import eapli.helpdesk.servico.domain.*;
import eapli.helpdesk.servico.repositories.*;
import eapli.helpdesk.tipoEquipa.domain.*;
import eapli.helpdesk.catalogo.domain.*;
import eapli.helpdesk.catalogo.exceptions.DescricaoBreveException;
import eapli.helpdesk.catalogo.exceptions.TituloException;
import eapli.helpdesk.catalogo.repositories.CatalogoRepository;
import eapli.helpdesk.criticidade.domain.Criticidade;
import eapli.helpdesk.criticidade.repositories.CriticidadeRepository;
import eapli.helpdesk.infraestrutura.PersistenceContext;
import eapli.helpdesk.user.domain.HelpdeskRoles;
import org.apache.commons.io.IOUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AddServicoController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final TransactionalContext ctx = PersistenceContext.repositories().newTransactionalContext();
    private final ServicoRepository repositoryServico = PersistenceContext.repositories().servico(ctx);
    private final CatalogoRepository repositoryCatalogo = PersistenceContext.repositories().catalogo(ctx);
    private final CriticidadeRepository repositoryCriticidade= PersistenceContext.repositories().criticidades();
    private final FormularioRepository repositoryFormulario = PersistenceContext.repositories().formulario();
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

    public void registerServicoTransaction(String codAlfaNum, String descricaoBreve, String descricaoCompletaServico,String feedback, String tempoLimiteFeedback, String titulo, String icone,
                                           Set<String> keywords,Criticidade criticidade,Catalogo catalogo,List<Servico>servicosNoCatalogo,String estadoServico) throws DescricaoCompletaServicoException, DescricaoBreveException, CodigoAlfaNumericoException, IOException, TituloException {
        ctx.beginTransaction();

        Servico servico=registerServico( codAlfaNum,  descricaoBreve,  descricaoCompletaServico, feedback,  tempoLimiteFeedback,  titulo,  icone,
                 keywords, criticidade,estadoServico);

        if (catalogo!=null){
            servicosNoCatalogo.add(servico);
            changeListaServicoCatalogo(catalogo,servicosNoCatalogo);
        }
        ctx.commit();
    }


    public Servico registerServico(String codAlfaNum, String descricaoBreve, String descricaoCompletaServico,String feedback, String tempoLimiteFeedback, String titulo, String icone,
                                      Set<String> keywords,Criticidade criticidade,String estadoServico) throws CodigoAlfaNumericoException, DescricaoBreveException, TituloException, DescricaoCompletaServicoException, IOException {
        authz.ensureAuthenticatedUserHasAnyOf(HelpdeskRoles.POWER_USER,HelpdeskRoles.SERVICE_MANAGER);
        CodigoAlfaNumerico cod=new CodigoAlfaNumerico(codAlfaNum);
        Titulo titulo1=registerTitulo(titulo);

        ServicoBuilder servicoBuilder= new ServicoBuilder();
        servicoBuilder.withData(cod,titulo1);

        if (descricaoBreve!=null){
            DescricaoBreve descricao=registerDescricaoBreve(descricaoBreve);
            servicoBuilder.withDescricaoBreve(descricao);
        }
        if (descricaoCompletaServico!=null){
            DescricaoCompletaServico descricaoCompletaServico1=registerDescricaoCompleta(descricaoCompletaServico);
            servicoBuilder.withDescricaoCompleta(descricaoCompletaServico1);
        }
        if (feedback!=null){
            servicoBuilder.withFeedback(feedback);
        }
        if (tempoLimiteFeedback!=null){
            Integer tempoLimiteFeedback1= Integer.parseInt(tempoLimiteFeedback);
            servicoBuilder.withTempoLimiteFeedback(tempoLimiteFeedback1);
        }
        if (icone!=null){
            byte[]icone1=registerIcone(icone);
            servicoBuilder.withIcone(icone1);
        }
        if (!keywords.isEmpty()){
            servicoBuilder.withKeywords(keywords);
        }
        if(formularioDeSolicitacao!=null){
            servicoBuilder.withFormularioDeSolicitacao(formularioDeSolicitacao);
        }
        if (criticidade!=null){
            servicoBuilder.withCriticidade(criticidade);
        }
        if (workflow!=null){
            servicoBuilder.withWorkflow(workflow);
        }
        servicoBuilder.withEstadoServico(estadoServico);
        Servico servico=servicoBuilder.build();
        return this.repositoryServico.save(servico);
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

    public void registerAtividadeAutomatica(Script script) throws CodigoAlfaNumericoException {
        this.atividadeAutomatica= new AtividadeAutomatica(script);
    }

    public void registerWorkflow(String diaMudanca) throws CodigoAlfaNumericoException, ParseException {
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(formatoData.parse(diaMudanca));
        this.workflow= new Workflow(c,this.atividadeManualAprovacao, this.atividadeManualExecucao, this.atividadeAutomatica);
    }

    public Titulo registerTitulo(String titulo) throws TituloException {
        return new Titulo(titulo);
    }

    public void registerCodigoAlfaNumerico(String codAlfa) throws CodigoAlfaNumericoException {
        new CodigoAlfaNumerico(codAlfa);
    }
    public DescricaoBreve registerDescricaoBreve(String descricaoBreve) throws DescricaoBreveException {
        return new DescricaoBreve(descricaoBreve);
    }

    public DescricaoCompletaServico registerDescricaoCompleta(String descricaoCompleta) throws DescricaoCompletaServicoException {
        return new DescricaoCompletaServico(descricaoCompleta);
    }

    public byte[] registerIcone(String imageFilename) throws IOException {
        final InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(imageFilename);
        if (inputStream == null) {
            throw new FileNotFoundException("Imagem " + imageFilename + " não encontrada!");
        }
        return IOUtils.toByteArray(inputStream);
    }
    public Iterable<Catalogo> findAllCatalogos(){
        return this.repositoryCatalogo.findAll();
    }

    public List<Servico> allServices(){
        List<Servico> lista = new ArrayList<>();
        for (Servico servico:repositoryServico.findAll()) {
           lista.add(servico);}
        return lista;
    }

    public void changeListaServicoCatalogo(final Catalogo catalogo, final List<Servico> servicos) {
        authz.ensureAuthenticatedUserHasAnyOf(HelpdeskRoles.POWER_USER,HelpdeskRoles.SERVICE_MANAGER);
        catalogo.changeListaDeServicosTo(servicos);
        this.repositoryCatalogo.save(catalogo);
    }
    public Iterable<Criticidade> allCriticidades() { return repositoryCriticidade.findAll(); }

    public int countCatalogos() {
        int countCatalogos =0;
        for (Catalogo cat :this.findAllCatalogos()) {
            countCatalogos++;
        }
        return countCatalogos;
    }

    public int countServicos(Catalogo cat) {
        int countServicos = 0;
        for (Servico ser:cat.servicos()) {
            countServicos++;
        }
        return countServicos;
    }

    public Formulario findFormularioByCod(CodigoAlfaNumerico cod) {

        return this.repositoryFormulario.findFormularioByCod(cod);
    }
}
