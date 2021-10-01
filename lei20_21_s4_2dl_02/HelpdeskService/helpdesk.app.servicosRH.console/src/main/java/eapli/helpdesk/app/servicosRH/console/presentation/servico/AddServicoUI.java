package eapli.helpdesk.app.servicosRH.console.presentation.servico;

import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.helpdesk.antlr.formValidation.FormValidationGrammarVisitor;
import eapli.helpdesk.antlr.formValidation.FormVisitor;
import eapli.helpdesk.antlr.formValidation.ValidarScript;
import eapli.helpdesk.app.executorTarefasAutomaticas.automaticsTaskScript.ValidateScriptAutomaticTask;
import eapli.helpdesk.atividade.Script;
import eapli.helpdesk.servico.application.AddServicoController;
import eapli.helpdesk.servico.domain.DescricaoCompletaServicoException;
import eapli.helpdesk.servico.domain.Servico;
import eapli.helpdesk.servico.domain.TipoDadosException;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumericoException;
import eapli.helpdesk.tipoEquipa.domain.DescricaoException;
import eapli.helpdesk.Utils.Utils;
import eapli.helpdesk.catalogo.domain.Catalogo;
import eapli.helpdesk.catalogo.exceptions.DescricaoBreveException;
import eapli.helpdesk.catalogo.exceptions.TituloException;
import eapli.helpdesk.criticidade.domain.Criticidade;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddServicoUI extends AbstractUI {

    private final AddServicoController theController = new AddServicoController();

    @Override
    protected boolean doShow() {
        int i=0;
        String titulo =null;
        String codAlfaNumerico=null;
        String descricaoBreve=null;
        String descricaoCompletaServico=null;
        String feedback=null;
        String tempoLimiteFeedback=null;
        String icone=null;
        String nome;
        String etiqueta;
        File script = null;
        String codigoAlfaNumericoFormulario = null;
        Criticidade criticidade=null;

        List<String>listaTipoDados = new ArrayList<>();
        listaTipoDados.add("String");
        listaTipoDados.add("boolean");
        listaTipoDados.add("integer");
        listaTipoDados.add("date");

        while(i==0) {
            try {
                titulo = Console.readLine("Titulo:");
                this.theController.registerTitulo(titulo);
                i++;
            } catch (TituloException tituloException) {
                System.out.println("Titulo Inválido. Por favor insira um titulo válido.");
                i=0;
            }
        }
        i=0;
        while(i==0) {
            try {
                codAlfaNumerico = Console.readLine("Código alfa numérico:");
                this.theController.registerCodigoAlfaNumerico(codAlfaNumerico);
                i++;
            } catch (CodigoAlfaNumericoException codigoAlfaNumericoException) {
                System.out.println("Codigo Alfa Numérico Inválido. Por favor insira um código válido.");
                i=0;
            }
        }

        if (Utils.confirm("Pretende adicionar um nível de criticidade?(S/N)")) {
            criticidade=(Criticidade) Utils.showsAndSelect((List) this.theController.allCriticidades(),"Escolha a criticidade do serviço");
        }

        i=0;
        if (Utils.confirm("Pretende adicionar uma descrição breve?(S/N)")) {
            while (i == 0) {
                try {
                    descricaoBreve = Console.readLine("Descrição Breve:");
                    this.theController.registerDescricaoBreve(codAlfaNumerico);
                    i++;
                } catch (DescricaoBreveException descricaoBreveException) {
                    System.out.println("Descrição inválida. Por favor insira uma descrição válida.");
                    i = 0;
                }
            }
        }
        i=0;
        if (Utils.confirm("Pretende adicionar uma descrição completa?(S/N)")) {
            while (i == 0) {
                try {
                    descricaoCompletaServico = Console.readLine("Descrição completa:");
                    this.theController.registerDescricaoCompleta(codAlfaNumerico);
                    i++;
                } catch (DescricaoCompletaServicoException descricaoCompletaServicoException) {
                    System.out.println("Descrição inválida. Por favor insira uma descrição válida.");
                    i = 0;
                }
            }
        }


        if(Utils.confirm("Pretende definir o feedback?(S/N)")) {
            if (Utils.confirm("Feedback?(S/N)")) {
                feedback = "S";
                tempoLimiteFeedback = Console.readLine("Tempo Limite de feedback:");
            }
            else {
                feedback = "N";
            }
        }

        i=0;
        if (Utils.confirm("Deseja adicionar um icone?(S/N)")) {
            while (i == 0) {
                icone = Console.readLine("Icone:");
                try {
                    this.theController.registerIcone(icone);
                    i++;
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    i = 0;
                }
            }
        }

        Set<String> keywords = new HashSet<>();
        boolean flag = true;
        while (flag) {
            if (Utils.confirm("Deseja adicionar uma keyword?(S/N)")) {
                final String keyword = Console.readLine("Keyword");
                keywords.add(keyword);
            }
            else{
                flag=false;
            }
        }

        Catalogo catalogo=null;
        List<Servico>servicosNoCatalogo=new LinkedList<>();
        if(Utils.confirm("Pretende escolher o catálogo ao qual o serviço pertence?(S/N)")){
            catalogo=(Catalogo) Utils.showsAndSelect((List)this.theController.findAllCatalogos(),"Escolha o catálogo ao qual o servico pertence: ");
            if (catalogo!=null) {
                servicosNoCatalogo = catalogo.servicos();
            }
        }

        if (Utils.confirm("Deseja proceder à especificação do formulário de solicitação de serviço?(S/N)")) {
            codigoAlfaNumericoFormulario = Console.readLine("Código alfanumérico do formulário:");

            nome=Console.readLine("Nome do formulário:");

            etiqueta=Console.readLine("Etiqueta:");

            flag = true;
            while (flag) {
                if (Utils.confirm("Deseja adicionar um atributo?(S/N)")) {
                    final String nomeAtributo = Console.readLine("Nome:");
                    final String descricao= Console.readLine("Descricao:");
                    final String expressaoRegular= Console.readLine("Expressão regular:");
                    final String tipoDeDados = (String) Utils.showsAndSelect(listaTipoDados,"Tipo de dados:");
                    final String etiquetaAtr = Console.readLine("Etiqueta:");
                    try {
                        this.theController.registerAtributoFormularioSolicitacao(nomeAtributo,descricao,expressaoRegular,tipoDeDados, etiquetaAtr);
                    } catch (DescricaoException | TipoDadosException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    flag=false;
                }
            }
            i=1;
            Script y =null;
            while(i==1) {
                i=0;
                script = new File(Console.readLine("Insira o script:"));
                y = new Script(script);
                boolean flag1 = ValidarScript.validateScript(y);
                if (flag1 == false) {
                    i=1;
                }
            }


            try {
                this.theController.registerFormularioSolicitacao(codigoAlfaNumericoFormulario, nome, etiqueta, y);
            } catch (CodigoAlfaNumericoException codigoAlfaNumericoException) {
                codigoAlfaNumericoException.printStackTrace();
            }
        }

        DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        Calendar obj = Calendar.getInstance();
        String dataMudanca = formatter.format(obj.getTime());
        String servicoAprovacao;
        String atividadeEscolhida=null;
        String codigoAlfaNumericoFormularioAtividadeManual;
        if (Utils.confirm("Deseja proceder à especificação do workflow do serviço?(S/N)")) {
            List<String> atividadesResolucao = new LinkedList<>();
            atividadesResolucao.add("Atividade Manual");
            atividadesResolucao.add("Atividade Automática");

            if(Utils.confirm("Deseja proceder à especificação da data a partir da qual começará a ser aplicado o workflow(por default esta data será definida como a data da mudança do workflow?(S/N)")) {
                dataMudanca = Console.readLine("Data de mudança:");
            }

            if (Utils.confirm("Este serviço terá uma atividade de aprovação?(S/N)")) {
                System.out.println("Especificação do formulário da atividade manual de aprovação");
                codigoAlfaNumericoFormularioAtividadeManual = Console.readLine("Código alfanumérico do formulário:");

                nome = Console.readLine("Nome do formulário:");

                etiqueta = Console.readLine("Etiqueta:");

                flag = true;
                while (flag) {
                    if (Utils.confirm("Deseja adicionar um atributo?(S/N)")) {
                        final String nomeAtributo = Console.readLine("Nome:");
                        final String descricao = Console.readLine("Descricao:");
                        final String expressaoRegular = Console.readLine("Expressão regular:");
                        final String tipoDeDados = (String) Utils.showsAndSelect(listaTipoDados,"Tipo de dados:");
                        final String etiquetaAtributo = Console.readLine("Etiqueta:");
                        try {
                            this.theController.registerAtributoFormularioAtividadeManualAprovacao(nomeAtributo, descricao, expressaoRegular, tipoDeDados, etiquetaAtributo);
                        } catch (DescricaoException | TipoDadosException e) {
                            e.printStackTrace();
                        }
                    } else {
                        flag = false;
                    }
                }
                i=1;
                Script y =null;
                while(i==1) {
                    i=0;
                    script = new File(Console.readLine("Insira o script:"));
                    y = new Script(script);
                    boolean flag1 = ValidarScript.validateScript(y);
                    if (flag1 == false) {
                        i=1;
                    }
                }
                try {
                    this.theController.registerFormularioAtividadeManualAprovacao(codigoAlfaNumericoFormularioAtividadeManual, nome, etiqueta, y);
                } catch (CodigoAlfaNumericoException codigoAlfaNumericoException) {
                    codigoAlfaNumericoException.printStackTrace();
                }
                try {
                    this.theController.registerAtividadeManualAprovacao();
                } catch (CodigoAlfaNumericoException codigoAlfaNumericoException) {
                    codigoAlfaNumericoException.printStackTrace();
                }
            }

            atividadeEscolhida = (String) Utils.showsAndSelect(atividadesResolucao, "Escolha o tipo de atividade de resolução do serviço");

            if (atividadeEscolhida.equalsIgnoreCase("Atividade Manual")) {
                System.out.println("Especificação do formulário da atividade manual");
                codigoAlfaNumericoFormularioAtividadeManual = Console.readLine("Código alfanumérico do formulário:");

                nome = Console.readLine("Nome do formulário:");

                etiqueta = Console.readLine("Etiqueta:");

                flag = true;
                while (flag) {
                    if (Utils.confirm("Deseja adicionar um atributo?(S/N)")) {
                        final String nomeAtributo = Console.readLine("Nome:");
                        final String descricao = Console.readLine("Descricao:");
                        final String expressaoRegular = Console.readLine("Expressão regular:");
                        final String tipoDeDados = (String) Utils.showsAndSelect(listaTipoDados,"Tipo de dados:");
                        final String etiquetaAtributo = Console.readLine("Etiqueta:");
                        try {
                            this.theController.registerAtributoFormularioAtividadeManualExecucao(nomeAtributo, descricao, expressaoRegular, tipoDeDados, etiquetaAtributo);
                        } catch (DescricaoException | TipoDadosException e) {
                            e.printStackTrace();
                        }
                    } else {
                        flag = false;
                    }
                }
                i=1;
                Script y =null;
                while(i==1) {
                    i=0;
                    script = new File(Console.readLine("Insira o script:"));
                    y = new Script(script);
                    boolean flag1 = ValidarScript.validateScript(y);
                    if (flag1 == false) {
                        i=1;
                    }
                }
                try {
                    this.theController.registerFormularioAtividadeManualExecucao(codigoAlfaNumericoFormularioAtividadeManual, nome, etiqueta, y);
                } catch (CodigoAlfaNumericoException codigoAlfaNumericoException) {
                    codigoAlfaNumericoException.printStackTrace();
                }
                try {
                    this.theController.registerAtividadeManualExecucao();
                } catch (CodigoAlfaNumericoException codigoAlfaNumericoException) {
                    codigoAlfaNumericoException.printStackTrace();
                }
            }
            else {
                i=1;
                Script y =null;
                while(i==1) {
                    i=0;
                    script = new File(Console.readLine("Insira o script:"));
                    y = new Script(script);
                    boolean flag1 = ValidateScriptAutomaticTask.validateScriptTarefaAutomatica(y);
                    if (flag1 == false) {
                        i=1;
                    }
                }
                try {
                    this.theController.registerAtividadeAutomatica(y);
                } catch (CodigoAlfaNumericoException codigoAlfaNumericoException) {
                    codigoAlfaNumericoException.printStackTrace();
                }
            }
            try {
                this.theController.registerWorkflow(dataMudanca);
            } catch (CodigoAlfaNumericoException | ParseException codigoAlfaNumericoException) {
                codigoAlfaNumericoException.printStackTrace();
            }
        }
        String estadoServico="Incompleto";
        if(criticidade!=null && descricaoBreve!=null && descricaoCompletaServico!=null && feedback!=null && icone!=null && !keywords.isEmpty() && catalogo!=null && codigoAlfaNumericoFormulario!=null && atividadeEscolhida!=null){
            estadoServico="Completo";
        }

            try {
                this.theController.registerServicoTransaction(codAlfaNumerico,descricaoBreve,descricaoCompletaServico,feedback,tempoLimiteFeedback,titulo,icone,keywords,criticidade,catalogo,servicosNoCatalogo,estadoServico);
            } catch (@SuppressWarnings("unused") final IntegrityViolationException | DescricaoCompletaServicoException | DescricaoBreveException | CodigoAlfaNumericoException | IOException | TituloException e) {
                System.out.println("Ocorreu algo de errado na criação do serviço ,por favor tente novamente");
            }
            return false;
        }

        @Override
        public String headline () {
            return "Registar serviço";
        }
    }
