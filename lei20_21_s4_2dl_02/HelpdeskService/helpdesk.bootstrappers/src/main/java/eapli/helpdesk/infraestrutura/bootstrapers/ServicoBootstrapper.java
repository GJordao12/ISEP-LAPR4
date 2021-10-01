package eapli.helpdesk.infraestrutura.bootstrapers;

import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.helpdesk.atividade.Script;
import eapli.helpdesk.catalogo.domain.Catalogo;
import eapli.helpdesk.catalogo.domain.Id;
import eapli.helpdesk.catalogo.repositories.CatalogoRepository;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.colaborador.repositories.CollaboratorRepository;
import eapli.helpdesk.criticidade.repositories.CriticidadeRepository;
import eapli.helpdesk.servico.application.AddServicoController;
import eapli.helpdesk.servico.domain.*;
import eapli.helpdesk.equipa.domain.Team;
import eapli.helpdesk.tipoEquipa.domain.*;
import eapli.helpdesk.catalogo.application.AddCatalogoController;
import eapli.helpdesk.catalogo.exceptions.DescricaoBreveException;
import eapli.helpdesk.catalogo.exceptions.DescricaoCompletaException;
import eapli.helpdesk.catalogo.exceptions.IdException;
import eapli.helpdesk.catalogo.exceptions.TituloException;
import eapli.helpdesk.criticidade.application.DefinirCriticidadeController;
import eapli.helpdesk.criticidade.domain.Criticidade;
import eapli.helpdesk.criticidade.domain.Tempo;
import eapli.helpdesk.criticidade.domain.Valor;
import eapli.helpdesk.criticidade.exception.TempoException;
import eapli.helpdesk.criticidade.exception.ValorException;
import eapli.helpdesk.infraestrutura.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class ServicoBootstrapper implements Action {
    private static final Logger LOGGER = LogManager.getLogger(ServicoBootstrapper.class);
    final AddServicoController controller=new AddServicoController();
    final AddServicoController controller2=new AddServicoController();
    final AddServicoController controller3=new AddServicoController();
    final AddServicoController controller4=new AddServicoController();
    final AddServicoController controller6=new AddServicoController();
    final AddServicoController controller7=new AddServicoController();
    final AddServicoController controller8=new AddServicoController();
    private final CatalogoRepository catalogoRepository =PersistenceContext.repositories().catalogo();
    private final CriticidadeRepository criticidadeRepository =PersistenceContext.repositories().criticidades();

    @Override
    public boolean execute() {
        try {
            Set<String> keywords=new HashSet<>();
            keywords.add("pedido para ausentar");
            keywords.add("ausentar");
            registerAtributoFormulario(controller,"Periodo de ausência(data de inicio)","Data de inicio da ausência","(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((?:19|20)[0-9][0-9])","Date", "pa1");
            registerAtributoFormulario(controller,"Periodo de ausência(data de fim)","Data de fim da ausência","(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((?:19|20)[0-9][0-9])","Date", "pa2");
            registerAtributoFormulario(controller,"Tipo de ausência","Tipo de ausência(ex:férias)","[a-zA-Z ]*","String", "ta1");
            registerAtributoFormulario(controller,"Justificação","Justificação da ausência","[a-zA-Z ]*","String", "j1");
            registerFormulario(controller,"fsEPA1","Form sol Serv EPA", "fsolServEPA", null);

            registerAtributoFormularioAprovacao(controller,"Fundamentação (Obrigatória)","Texto fundamentando a decisão","[a-zA-Z ]*","String", "38rf");
            registerFormularioAtividadeManualAprovacao(controller,"faEPA1","Form aprov Serv EPA","faprovServEPA", new Script(new File("ScriptsFormularios/AprovacaoScriptS1.txt")));
            registerAtividadeManualAprovacao(controller);

            registerAtributoFormularioExecucao(controller,"Dias de férias já gozados no ano","Dias de férias já gozados no ano","^(?:[1-9]\\d?|[12]\\d{2}|3[0-5]\\d|36[0-5])$","Integer","dfga1");
            registerAtributoFormularioExecucao(controller,"Dias de férias gozados do período solicitado","Dias de férias gozados do período solicitado","^(?:[1-9]\\d?|[12]\\d{2}|3[0-5]\\d|36[0-5])$","Integer","dfgps1");
            registerAtributoFormularioExecucao(controller,"Dias de férias totais","Dias de férias totais","[0-9]*","Integer","dft1");
            registerAtributoFormularioExecucao(controller,"Dias de falta justificadas já ocorridas no ano","Dias de falta justificadas já ocorridas no ano","^(?:[1-9]\\d?|[12]\\d{2}|3[0-5]\\d|36[0-5])$","Integer","dfjoa1");
            registerAtributoFormularioExecucao(controller,"Dias de faltas justificadas do período solicitado","Dias de faltas justificadas do período solicitado","^(?:[1-9]\\d?|[12]\\d{2}|3[0-5]\\d|36[0-5])$","Integer","dfjps1");
            registerAtributoFormularioExecucao(controller,"Dias de faltas justificadas totais","Dias de faltas justificadas totais","[0-9]*","Integer","dfjt1");
            registerAtributoFormularioExecucao(controller,"Dias falta não justificadas(ano)","Dias de falta não justificadas já ocorridas no ano","^(?:[1-9]\\d?|[12]\\d{2}|3[0-5]\\d|36[0-5])$","Integer","dfnjoa1");
            registerAtributoFormularioExecucao(controller,"Dias de faltas não justificadas(periodo)","Dias de faltas não justificadas do período solicitado","^(?:[1-9]\\d?|[12]\\d{2}|3[0-5]\\d|36[0-5])$","Integer","dfnjps1");
            registerAtributoFormularioExecucao(controller,"Dias de faltas não justificadas(total)","Dias de faltas não justificadas totais","[0-9]*","Integer","dfnjt1");
            registerAtributoFormularioExecucao(controller,"comentário","comentário","[a-zA-Z]*","String","c1");

            registerFormularioAtividadeManualExecucao(controller,"feEPA1","Form exec Serv EPA","fexecServEPA",new Script(new File("ScriptsFormularios/RealizacaoScriptS1.txt")));

            registerAtividadeManualExecucao(controller);
            registerWorkflow(controller,"21/08/2021");
            Criticidade crit=criticidadeRepository.findAll().iterator().next();
            List<Servico>servicosCatalogo=new ArrayList<>();

            Optional<Catalogo> catalogo=catalogoRepository.ofIdentity(new Id(1));


            controller.registerServicoTransaction("epa01","Pedido de ausência","Efetuar pedido para se ausentar","N", null, "Pedido de ausência", "images/Servico1.jpg",
                    keywords,crit,catalogo.stream().iterator().next(),servicosCatalogo,"Completo");

            List<Servico>servicosCatalogo2=new ArrayList<>();

            Optional<Catalogo> catalogo2=catalogoRepository.ofIdentity(new Id(2));

            registerAtributoFormulario(controller2,"Numero de telefone","Numero novo:","[9][1-3][0-9]{7}","Integer", "nt1");
            registerFormulario(controller2,"fsMNT1","Form sol Serv MNT", "fsolServMNT", null);

            registerAtividadeAutomatica(controller2,new Script(new File("ScriptsTarefasAutomaticas/ScriptServicoMudarTelefone.txt")));

            registerWorkflow(controller2,"21/08/2021");

            Set<String> keywords1=new HashSet<>();
            keywords1.add("telefone");
            keywords1.add("numero");
            controller2.registerServicoTransaction("mnt01","Mudar numero de telefone","Mudar numero de telefone caso exista algum problema","S","3","Telefone","images/Servico1.jpg",keywords1,crit,
                    catalogo2.stream().iterator().next(),servicosCatalogo2,"Completo");


            registerAtributoFormulario(controller3,"ID","ID","[0-9]*","Integer", "id3");
            registerAtributoFormulario(controller3,"Desconto","Desconto","[0-9]|[1-9][0-9]|100","Integer", "desc1");
            registerAtributoFormulario(controller3,"Código Interno Cliente","Código interno do cliente que solicita o serviço","([b-df-hj-np-tv-z]{3})([0-9]{3})","String", "cod1");
            registerAtributoFormulario(controller3,"Nome","Nome do cliente","[a-zA-Z]*","String", "n1");
            registerAtributoFormulario(controller3,"Tipo de desconto","Tipo de desconto a ser aplicado","[a-zA-Z ]*","String", "td1");
            registerAtributoFormulario(controller3,"Recorrência","Recorrência","[a-zA-Z]*","String", "r1");
            registerAtributoFormulario(controller3,"Valor de Desconto","Valor de desconto aplicado","[0-9]*","Integer", "vd1");
            registerAtributoFormulario(controller3,"Identificação da Fatura","Identificação da fatura","[0-9]*","Integer", "if1");
            registerAtributoFormulario(controller3,"Fundamentação do pedido","Fundamentação do pedido","[a-zA-Z ]*","String", "fp1");
            registerFormulario(controller3,"fsAAD1","Form sol Serv AAD", "fsolServAAD", null);

            registerAtributoFormularioAprovacao(controller3,"Fundamentação (Obrigatória)","Texto fundamentando a decisão","[a-zA-Z ]*","String", "f1");
            registerAtributoFormularioAprovacao(controller3,"Percentagem de Desconto","Confirmação de Percentagem de Desconto","([0-9]|[1-9][0-9]|100)?","Integer", "f2");
            registerAtributoFormularioAprovacao(controller3,"Valor de Desconto","Confirmação de Valor de Desconto","[0-9]*","Integer", "f3");
            registerFormularioAtividadeManualAprovacao(controller3,"famaAAD1","Form aprov Serv AAD","faprovServAAD", new Script(new File("ScriptsFormularios/AprovacaoScriptS2.txt")));
            registerAtividadeManualAprovacao(controller3);

            registerAtividadeAutomatica(controller3,new Script(new File("ScriptsTarefasAutomaticas/ScriptServicoAlterarDescontoProduto.txt")));

            registerWorkflow(controller3,"21/08/2021");

            Set<String> keywords2=new HashSet<>();
            keywords2.add("aplicação de desconto");
            keywords2.add("desconto");
            Optional<Criticidade> crit2=criticidadeRepository.ofIdentity(new Valor(3));
            controller3.registerServicoTransaction("ad01","Autorização para Aplicação de Desconto","Pedir autorização para aplicação de desconto","S","3","Autorização para Aplicação de Desconto","images/Servico2.jpg",keywords2,crit2.stream().iterator().next(),
                    catalogo2.stream().iterator().next(),catalogo2.stream().iterator().next().servicos(),"Completo");



            registerAtributoFormulario(controller4,"Distrito","Distrito de residência novo","[a-zA-Z ]*","String", "d1");
            registerAtributoFormulario(controller4,"Concelho","Concelho de residência novo","[a-zA-Z ]*","String", "c1");
            registerFormulario(controller4,"fsCAR1","Form sol Serv CAR", "fsolServCAR", null);

            registerAtributoFormularioExecucao(controller4,"Texto de observações (Obrigatório)","Texto de observações","[a-zA-Z]*","String", "to1");
            registerFormularioAtividadeManualExecucao(controller4,"famaCAR1","Form aprov Serv CAR","faprovServCAR", new Script(new File("ScriptsFormularios/RealizacaoScriptS3.txt")));
            registerAtividadeManualExecucao(controller4);

            registerWorkflow(controller4,"21/08/2021");

            Set<String> keywords3=new HashSet<>();
            keywords3.add("alteração de residencia");
            keywords3.add("residencia");
            controller4.registerServicoTransaction("car01","Comunicação de Alteração de Residência","Comunicar alteração da residência","S","3","Comunicação de Alteração de Residência","images/Servico2.jpg",keywords3,crit2.stream().iterator().next(),
                    catalogo2.stream().iterator().next(),catalogo2.stream().iterator().next().servicos(),"Completo");



            registerAtributoFormulario(controller6,"Número Mecanográfico","Número Mecanográfico:","[0-9]*","Integer", "nm1");
            registerAtributoFormulario(controller6,"Mês","Mês","[a-zA-Z]*","String", "m1");
            registerFormulario(controller6,"fsCBMA1","Form sol Serv CBMA", "fsolServCBMA", null);

            registerAtividadeAutomatica(controller6,new Script(new File("ScriptsTarefasAutomaticas/ScriptServicoConsultarBonus.txt")));

            registerWorkflow(controller6,"21/08/2021");

            Set<String> keywords5=new HashSet<>();
            keywords5.add("mes deste ano");
            keywords5.add("consultar bonus");
            controller6.registerServicoTransaction("cbma01","Consultar Bónus de um Mês deste Ano","Consultar Bónus de um Mês deste Ano","S","3","Consultar Bónus de um Mês deste Ano","images/Servico2.jpg",keywords5,crit2.stream().iterator().next(),
                    catalogo2.stream().iterator().next(),catalogo2.stream().iterator().next().servicos(),"Completo");

            registerAtributoFormulario(controller7,"ID","ID","[0-9]*","Integer", "id1");
            registerFormulario(controller7,"fsSPP1","Form sol Serv SPP", "fsolServSPP", null);

            registerAtividadeAutomatica(controller7,new Script(new File("ScriptsTarefasAutomaticas/ScriptServicoBuscarPrecoProduto.txt")));

            registerWorkflow(controller7,"21/08/2021");

            Set<String> keywords6=new HashSet<>();
            keywords6.add("Saber preço de um produto");
            keywords6.add("produto");
            controller7.registerServicoTransaction("spp01","Saber Preço de um Produto","Saber Preço de um Produto","S","3","Saber Preço de um Produto","images/Servico2.jpg",keywords6,crit2.stream().iterator().next(),
                    catalogo2.stream().iterator().next(),catalogo2.stream().iterator().next().servicos(),"Completo");

            registerAtributoFormulario(controller8,"ID","ID","[0-9]*","Integer", "id2");
            registerFormulario(controller8,"fsSDSP1","Form sol Serv SDSP", "fsolServSDSP", null);

            registerAtividadeAutomatica(controller8,new Script(new File("ScriptsTarefasAutomaticas/ScriptServicoSaberMaximoDinheiroComProduto.txt")));

            registerWorkflow(controller8,"21/08/2021");

            Set<String> keywords7=new HashSet<>();
            keywords6.add("Saber preço dos stocks");
            keywords6.add("stock");
            controller8.registerServicoTransaction("sdsp01","Vender os stocks de um produto","Saber quanto dinheiro consigo fazer se vender os stocks todos de um produto","S","6","Dinheiro dos stocks todos de um produto","images/Servico2.jpg",keywords7,crit2.stream().iterator().next(),
                    catalogo2.stream().iterator().next(),catalogo2.stream().iterator().next().servicos(),"Completo");

        } catch (DescricaoCompletaServicoException | DescricaoBreveException | IOException | TituloException | CodigoAlfaNumericoException | ValorException | IdException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void registerFormulario(AddServicoController controller,String codAlfaNum, String nome, String etiqueta, Script script){
        try {
            controller.registerFormularioSolicitacao(codAlfaNum,nome,etiqueta,script);
        } catch (final IntegrityViolationException | ConcurrencyException | CodigoAlfaNumericoException ex) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", codAlfaNum);
            LOGGER.trace("Assuming existing record", ex);
        }
    }
    private void registerAtributoFormulario(AddServicoController controller,String nomeAtributo,String descricao,String expressaoRegular,String tipoDados, String etiqueta ){
        try {
            controller.registerAtributoFormularioSolicitacao(nomeAtributo,descricao,expressaoRegular,tipoDados, etiqueta);
        } catch (final IntegrityViolationException | ConcurrencyException | DescricaoException | TipoDadosException ex) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", nomeAtributo);
            LOGGER.trace("Assuming existing record", ex);
        }
    }

    private void registerAtributoFormularioAprovacao(AddServicoController controller,String nomeAtributo,String descricao,String expressaoRegular,String tipoDados, String etiqueta ){
        try {
            controller.registerAtributoFormularioAtividadeManualAprovacao(nomeAtributo,descricao,expressaoRegular,tipoDados, etiqueta);
        } catch (final IntegrityViolationException | ConcurrencyException | DescricaoException | TipoDadosException ex) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", nomeAtributo);
            LOGGER.trace("Assuming existing record", ex);
        }
    }

    private void registerWorkflow(AddServicoController controller,String diaMudanca){
        try {
            controller.registerWorkflow(diaMudanca);
        } catch (final IntegrityViolationException | ConcurrencyException  ex) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)",diaMudanca);
            LOGGER.trace("Assuming existing record", ex);
        } catch (CodigoAlfaNumericoException | ParseException e) {
            e.printStackTrace();
        }
    }
    private void registerAtividadeAutomatica(AddServicoController controller,Script script){
        try {
            controller.registerAtividadeAutomatica(script);
        } catch (final IntegrityViolationException | ConcurrencyException ex) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)",script);
            LOGGER.trace("Assuming existing record", ex);
        } catch (CodigoAlfaNumericoException  e) {
            e.printStackTrace();
        }
    }

    private void registerAtividadeManualAprovacao(AddServicoController controller){
        try {
            controller.registerAtividadeManualAprovacao();
        } catch (final IntegrityViolationException | ConcurrencyException ex) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)");
            LOGGER.trace("Assuming existing record", ex);
        } catch (CodigoAlfaNumericoException  e) {
            e.printStackTrace();
        }
    }
    private void registerFormularioAtividadeManualAprovacao(AddServicoController controller,String codAlfaNum, String nome, String etiqueta, Script script){
        try {
            controller.registerFormularioAtividadeManualAprovacao(codAlfaNum,nome,etiqueta,script);
        } catch (final IntegrityViolationException | ConcurrencyException ex) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)",script);
            LOGGER.trace("Assuming existing record", ex);
        } catch (CodigoAlfaNumericoException  e) {
            e.printStackTrace();
        }
    }
    private void registerFormularioAtividadeManualExecucao(AddServicoController controller,String codAlfaNum, String nome, String etiqueta, Script script){
        try {
            controller.registerFormularioAtividadeManualExecucao(codAlfaNum,nome,etiqueta,script);
        } catch (final IntegrityViolationException | ConcurrencyException ex) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)",script);
            LOGGER.trace("Assuming existing record", ex);
        } catch (CodigoAlfaNumericoException  e) {
            e.printStackTrace();
        }
    }
    private void registerAtividadeManualExecucao(AddServicoController controller){
        try {
            controller.registerAtividadeManualExecucao();
        } catch (final IntegrityViolationException | ConcurrencyException ex) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)");
            LOGGER.trace("Assuming existing record", ex);
        } catch (CodigoAlfaNumericoException  e) {
            e.printStackTrace();
        }
    }

    private void registerAtributoFormularioExecucao(AddServicoController controller,String nomeAtributo,String descricao,String expressaoRegular,String tipoDados, String etiqueta){
        try {
            controller.registerAtributoFormularioAtividadeManualExecucao(nomeAtributo,descricao,expressaoRegular,tipoDados, etiqueta);
        } catch (final IntegrityViolationException | ConcurrencyException | DescricaoException | TipoDadosException ex) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", nomeAtributo);
            LOGGER.trace("Assuming existing record", ex);
        }
    }
    }

