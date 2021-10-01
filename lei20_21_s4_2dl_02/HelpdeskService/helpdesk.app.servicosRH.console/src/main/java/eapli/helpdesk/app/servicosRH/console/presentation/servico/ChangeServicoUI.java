package eapli.helpdesk.app.servicosRH.console.presentation.servico;

import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.helpdesk.antlr.formValidation.ValidarScript;
import eapli.helpdesk.app.executorTarefasAutomaticas.automaticsTaskScript.ValidateScriptAutomaticTask;
import eapli.helpdesk.atividade.Script;
import eapli.helpdesk.servico.application.ChangeServicoController;
import eapli.helpdesk.servico.domain.*;
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

public class ChangeServicoUI extends AbstractUI {

    private final ChangeServicoController theController = new ChangeServicoController();

    @Override
    protected boolean doShow() {
        Iterable<Servico> listServico = this.theController.findAllServicos();
        Servico servicoSelecionado;
        List<String>listaTipoDados = new ArrayList<>();
        listaTipoDados.add("String");
        listaTipoDados.add("boolean");
        listaTipoDados.add("integer");
        listaTipoDados.add("date");

        if (!listServico.iterator().hasNext()) {
            System.out.println("Não existem serviços !");
        } else {
            servicoSelecionado = (Servico) Utils.showsAndSelect((List) listServico, "Escolha o serviço que pretende alterar");
            int i = 0;
            String nome ;
            String etiqueta ;
            File script = null;

            System.out.println("Titulo atual: " + servicoSelecionado.titulo().toString());
            if (Utils.confirm("Pretende modificar o titulo do serviço?(S/N)")) {
                while (i == 0) {
                    try {
                        final String tituloNovo = Console.readLine("Titulo novo: ");
                        this.theController.changeTitulo(servicoSelecionado, tituloNovo);
                        i++;
                    } catch (@SuppressWarnings("unused") final ConcurrencyException ex) {
                        System.out.println("WARNING:Não foi possível alterar o serviço porque este foi alterado por outro utilizador");
                    } catch (final IntegrityViolationException ex) {
                        System.out.println(
                                "Infelizmente ocorreu um erro inesperado na aplicação.");
                    } catch (TituloException tituloException) {
                        System.out.println("Titulo Inválido. Por favor insira um titulo válido.");
                        i = 0;
                    }
                }
            }
            i = 0;
            if (servicoSelecionado.descricaoBreve() != null) {
                System.out.println("Descricao breve atual : " + servicoSelecionado.descricaoBreve().toString());
                if (Utils.confirm("Pretende modificar a descrição breve?(S/N)")) {
                    while (i == 0) {
                        try {
                            final String descricaoBreveNova = Console.readLine("Descricao Breve Nova: ");
                            this.theController.changeDescricaoBreve(servicoSelecionado, descricaoBreveNova);
                            i++;
                        } catch (@SuppressWarnings("unused") final ConcurrencyException ex) {
                            System.out.println("WARNING:Não foi possível alterar o serviço porque este foi alterado por outro utilizador");
                        } catch (final IntegrityViolationException ex) {
                            System.out.println(
                                    "Infelizmente ocorreu um erro inesperado na aplicação.");
                        } catch (DescricaoBreveException descricaoBreveException) {
                            System.out.println("Descricao Inválida. Por favor insira uma descricao válido.");
                            i = 0;
                        }
                    }
                }
            } else {
                if (Utils.confirm("Pretende adicionar uma descrição breve?(S/N)")) {
                    while (i == 0) {
                        try {
                            final String descricaoBreveNova = Console.readLine("Descrição breve: ");
                            this.theController.changeDescricaoBreve(servicoSelecionado, descricaoBreveNova);
                            i++;
                        } catch (@SuppressWarnings("unused") final ConcurrencyException ex) {
                            System.out.println("WARNING:Não foi possível alterar o serviço porque este foi alterado por outro utilizador");
                        } catch (final IntegrityViolationException ex) {
                            System.out.println(
                                    "Infelizmente ocorreu um erro inesperado na aplicação.");
                        } catch (DescricaoBreveException descricaoBreveException) {
                            System.out.println("Descricao Inválida. Por favor insira uma descricao válida.");
                            i = 0;
                        }
                    }
                }
            }
            i = 0;
            if (servicoSelecionado.descricaoCompleta() != null) {
                System.out.println("Descricao completa atual : " + servicoSelecionado.descricaoCompleta().toString());
                if (Utils.confirm("Pretende modificar a descrição completa?(S/N)")) {
                    while (i == 0) {
                        try {
                            final String descricaoCompletaNova = Console.readLine("Descricao Completa Nova: ");
                            this.theController.changeDescricaoCompleta(servicoSelecionado, descricaoCompletaNova);
                            i++;
                        } catch (@SuppressWarnings("unused") final ConcurrencyException ex) {
                            System.out.println("WARNING:Não foi possível alterar o serviço porque este foi alterado por outro utilizador");
                        } catch (final IntegrityViolationException ex) {
                            System.out.println(
                                    "Infelizmente ocorreu um erro inesperado na aplicação.");
                        } catch (DescricaoCompletaServicoException descricaoCompletaServicoException) {
                            System.out.println("Descricao Inválida. Por favor insira uma descricao válido.");
                            i = 0;
                        }
                    }
                }
            } else {
                if (Utils.confirm("Pretende adicionar uma descrição completa?(S/N)")) {
                    while (i == 0) {
                        try {
                            final String descricaoCompletaNova = Console.readLine("Descrição completa: ");
                            this.theController.changeDescricaoCompleta(servicoSelecionado, descricaoCompletaNova);
                            i++;
                        } catch (@SuppressWarnings("unused") final ConcurrencyException ex) {
                            System.out.println("WARNING:Não foi possível alterar o serviço porque este foi alterado por outro utilizador");
                        } catch (final IntegrityViolationException ex) {
                            System.out.println(
                                    "Infelizmente ocorreu um erro inesperado na aplicação.");
                        } catch (DescricaoCompletaServicoException descricaoCompletaServicoException) {
                            System.out.println("Descricao Inválida. Por favor insira uma descricao válida.");
                            i = 0;
                        }
                    }
                }
            }
            i = 0;
            String feedback = null;
            if (servicoSelecionado.feedback() != null) {
                System.out.println("Definição de feedback atual : " + servicoSelecionado.feedback());
                if (Utils.confirm("Pretende modificar o feedback?(S/N)")) {
                    if (Utils.confirm("Feedback?(S/N)")) {
                        feedback = "S";
                    } else {
                        feedback = "N";
                    }
                    try {
                        this.theController.changeFeedback(servicoSelecionado, feedback);
                    } catch (@SuppressWarnings("unused") final ConcurrencyException ex) {
                        System.out.println("WARNING:Não foi possível alterar o serviço porque este foi alterado por outro utilizador");
                    } catch (final IntegrityViolationException ex) {
                        System.out.println(
                                "Infelizmente ocorreu um erro inesperado na aplicação.");

                    }
                }
            } else {
                if (Utils.confirm("Pretende especificar o feedback?(S/N)")) {
                    if (Utils.confirm("Feedback?(S/N)")) {
                        feedback = "S";
                    } else {
                        feedback = "N";
                    }

                    try {
                        this.theController.changeFeedback(servicoSelecionado, feedback);
                    } catch (@SuppressWarnings("unused") final ConcurrencyException ex) {
                        System.out.println("WARNING:Não foi possível alterar o serviço porque este foi alterado por outro utilizador");
                    } catch (final IntegrityViolationException ex) {
                        System.out.println(
                                "Infelizmente ocorreu um erro inesperado na aplicação.");
                    }
                }
            }
            if (servicoSelecionado.tempoLimiteFeedback() != null && servicoSelecionado.feedback() != null && servicoSelecionado.feedback().equalsIgnoreCase("S")) {
                System.out.println("Tempo limite de feedback atual : " + servicoSelecionado.tempoLimiteFeedback());
                if (Utils.confirm("Pretende modificar o tempo limite de feedback?(S/N)")) {
                    while (i == 0) {
                        System.out.println("Tempo limite de feedback atual : " + servicoSelecionado.descricaoCompleta().toString());
                        try {
                            final String tempoLimiteFeedback = Console.readLine("Tempo Limite Feedback: ");
                            this.theController.changeTempoLimiteFeedback(servicoSelecionado, tempoLimiteFeedback);
                            i++;
                        } catch (@SuppressWarnings("unused") final ConcurrencyException ex) {
                            System.out.println("WARNING:Não foi possível alterar o serviço porque este foi alterado por outro utilizador");
                        } catch (final IntegrityViolationException ex) {
                            System.out.println(
                                    "Infelizmente ocorreu um erro inesperado na aplicação.");
                        }
                    }
                }
            } else if (servicoSelecionado.tempoLimiteFeedback() == null && servicoSelecionado.feedback() != null && servicoSelecionado.feedback().equalsIgnoreCase("S")) {
                if (Utils.confirm("Pretende adicionar o tempo limite de feedback?(S/N)")) {
                    while (i == 0) {
                        try {
                            final String tempoLimiteFeedback = Console.readLine("Tempo Limite Feedback: ");
                            this.theController.changeTempoLimiteFeedback(servicoSelecionado, tempoLimiteFeedback);
                            i++;
                        } catch (@SuppressWarnings("unused") final ConcurrencyException ex) {
                            System.out.println("WARNING:Não foi possível alterar o serviço porque este foi alterado por outro utilizador");
                        } catch (final IntegrityViolationException ex) {
                            System.out.println(
                                    "Infelizmente ocorreu um erro inesperado na aplicação.");
                        }
                    }
                }
            }
            i = 0;
            if (servicoSelecionado.icone2() != null) {
                if (Utils.confirm("Pretende modificar o icone?(S/N)")) {
                    while (i == 0) {
                        try {
                            final String icone = Console.readLine("Icone Novo: ");
                            this.theController.changeIcone(servicoSelecionado, icone);
                            i++;
                        } catch (@SuppressWarnings("unused") final ConcurrencyException ex) {
                            System.out.println("WARNING:Não foi possível alterar o serviço porque este foi alterado por outro utilizador");
                        } catch (final IntegrityViolationException ex) {
                            System.out.println(
                                    "Infelizmente ocorreu um erro inesperado na aplicação.");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                if (Utils.confirm("Pretende adicionar um icone?(S/N)")) {
                    while (i == 0) {
                        try {
                            final String icone = Console.readLine("Icone Novo: ");
                            this.theController.changeIcone(servicoSelecionado, icone);
                            i++;
                        } catch (@SuppressWarnings("unused") final ConcurrencyException ex) {
                            System.out.println("WARNING:Não foi possível alterar o serviço porque este foi alterado por outro utilizador");
                        } catch (final IntegrityViolationException ex) {
                            System.out.println(
                                    "Infelizmente ocorreu um erro inesperado na aplicação.");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            boolean flag = true;
            Set<String> keywords = servicoSelecionado.keywords();
            int k = 0;
            while (flag) {
                if (Utils.confirm("Pretende adicionar uma keyword?(S/N)")) {
                    final String keyword = Console.readLine("Keyword: ");
                    keywords.add(keyword);
                    k++;
                } else {
                    flag = false;
                }
            }
            if (k > 0) {
                try {
                    this.theController.changeKeywords(servicoSelecionado, keywords);
                } catch (@SuppressWarnings("unused") final ConcurrencyException ex) {
                    System.out.println("WARNING:Não foi possível alterar o serviço porque este foi alterado por outro utilizador");
                } catch (final IntegrityViolationException ex) {
                    System.out.println(
                            "Infelizmente ocorreu um erro inesperado na aplicação.");
                }
            }

            if (servicoSelecionado.criticidade() != null) {
                System.out.println("Valor da Criticidade atual:" + servicoSelecionado.criticidade().toString());
                if (Utils.confirm("Pretende modificar a criticidade?(S/N)")) {
                    try {
                        final Criticidade criticidade = (Criticidade) Utils.showsAndSelect((List) theController.allCriticidades(), "Escolha a nova criticidade: ");
                        this.theController.changeCriticidade(servicoSelecionado, criticidade);
                    } catch (@SuppressWarnings("unused") final ConcurrencyException ex) {
                        System.out.println("WARNING:Não foi possível alterar o serviço porque este foi alterado por outro utilizador");
                    } catch (final IntegrityViolationException ex) {
                        System.out.println(
                                "Infelizmente ocorreu um erro inesperado na aplicação.");
                    }
                }
            } else {
                if (Utils.confirm("Pretende adicionar a criticidade ao servico?(S/N)")) {
                    try {
                        final Criticidade criticidade = (Criticidade) Utils.showsAndSelect((List) theController.allCriticidades(), "Escolha a nova criticidade: ");
                        this.theController.changeCriticidade(servicoSelecionado, criticidade);
                    } catch (@SuppressWarnings("unused") final ConcurrencyException ex) {
                        System.out.println("WARNING:Não foi possível alterar o serviço porque este foi alterado por outro utilizador");
                    } catch (final IntegrityViolationException ex) {
                        System.out.println(
                                "Infelizmente ocorreu um erro inesperado na aplicação.");
                    }
                }
            }
            Iterable<Catalogo> catalogos = this.theController.findAllCatalogos();
            Catalogo catalogoDoServico = null;

            for (Catalogo catalogo : catalogos) {
                if (catalogo.servicos().contains(servicoSelecionado)) {
                    catalogoDoServico = catalogo;
                }
            }
            List<Servico> servicosNoCatalogo = new LinkedList<>();
            if (catalogoDoServico == null) {
                if (Utils.confirm("Deseja especificar o catálogo?(S/N)")) {
                    catalogoDoServico = (Catalogo) Utils.showsAndSelect((List) this.theController.findAllCatalogos(), "Escolha o catálogo ao qual o servico pertence: ");
                    if (catalogoDoServico != null) {
                        servicosNoCatalogo = catalogoDoServico.servicos();
                    }
                    try {
                        if (catalogoDoServico != null) {
                            servicosNoCatalogo.add(servicoSelecionado);
                            this.theController.changeListaServicoCatalogo(catalogoDoServico, servicosNoCatalogo);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println("Catalogo atual:"+catalogoDoServico);
                if (Utils.confirm("Deseja modificar o catálogo?(S/N)")) {
                    final Catalogo novocatalogoDoServico = (Catalogo) Utils.showsAndSelect((List) this.theController.findAllCatalogos(), "Escolha o novo catálogo: ");
                    if (novocatalogoDoServico != null) {
                        servicosNoCatalogo = novocatalogoDoServico.servicos();
                    }
                    try {
                        if (novocatalogoDoServico != null) {
                            List<Servico>listaCatalogoAntigo=catalogoDoServico.servicos();
                            listaCatalogoAntigo.remove(servicoSelecionado);
                            servicosNoCatalogo.add(servicoSelecionado);
                            this.theController.changeListaServicoCatalogo(catalogoDoServico,listaCatalogoAntigo);
                            this.theController.changeListaServicoCatalogo(novocatalogoDoServico, servicosNoCatalogo);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (servicoSelecionado.formularioSolicitacao() == null) {
                if (Utils.confirm("Deseja proceder à especificação do formulário de solicitação de serviço?(S/N)")) {
                    final String codigoAlfaNumericoFormulario = Console.readLine("Código alfanumérico do formulário:");

                    nome = Console.readLine("Nome do formulário:");

                    etiqueta = Console.readLine("Etiqueta:");

                    flag = true;
                    while (flag) {
                        if (Utils.confirm("Deseja adicionar um atributo?(S/N)")) {
                            final String nomeAtributo = Console.readLine("Nome:");
                            final String descricao = Console.readLine("Descricao:");
                            final String expressaoRegular = Console.readLine("Expressão regular:");
                            final String tipoDeDados = (String) Utils.showsAndSelect(listaTipoDados,"Tipo de dados:");
                            final String etiquetaAt = Console.readLine("Etiqueta:");
                            try {
                                this.theController.registerAtributoFormularioSolicitacao(nomeAtributo, descricao, expressaoRegular, tipoDeDados, etiquetaAt);
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
                        if (codigoAlfaNumericoFormulario != null) {
                            this.theController.registerFormularioSolicitacao(codigoAlfaNumericoFormulario, nome, etiqueta, y);
                        }
                    } catch (@SuppressWarnings("unused") final IntegrityViolationException | CodigoAlfaNumericoException codAlfaNumException) {
                        System.out.println("Esse código alfanumérico já está a ser usado");
                    }
                    this.theController.registerFormularioTransaction(servicoSelecionado);
                }
            }
            if (servicoSelecionado.workflow() == null) {
                    DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
                    Calendar obj = Calendar.getInstance();
                    String dataMudanca = formatter.format(obj.getTime());
                    String servicoAprovacao;
                    String atividadeEscolhida;
                    String codigoAlfaNumericoFormulario;
                    if (Utils.confirm("Deseja proceder à especificação do workflow do serviço?(S/N)")) {
                        List<String> atividadesResolucao = new LinkedList<>();
                        atividadesResolucao.add("Atividade Manual");
                        atividadesResolucao.add("Atividade Automática");


                        if(Utils.confirm("Deseja proceder à especificação da data a partir da qual começará a ser aplicado o workflow(por default esta data será definida como a data da mudança do workflow?(S/N)")) {
                            dataMudanca = Console.readLine("Data de mudança:");
                        }

                        if (Utils.confirm("Este serviço terá uma atividade de aprovação?(S/N)")) {
                            System.out.println("Especificação do formulário da atividade manual de aprovação");
                            codigoAlfaNumericoFormulario = Console.readLine("Código alfanumérico do formulário:");

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
                                this.theController.registerFormularioAtividadeManualAprovacao(codigoAlfaNumericoFormulario, nome, etiqueta, y);
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
                            codigoAlfaNumericoFormulario = Console.readLine("Código alfanumérico do formulário:");

                            nome = Console.readLine("Nome do formulário:");

                            etiqueta = Console.readLine("Etiqueta:");

                            flag = true;
                            while (flag) {
                                if (Utils.confirm("Deseja adicionar um atributo?(S/N)")) {
                                    final String nomeAtributo = Console.readLine("Nome:");
                                    final String descricao = Console.readLine("Descricao:");
                                    final String expressaoRegular = Console.readLine("Expressão regular:");
                                    final String tipoDeDados = (String) Utils.showsAndSelect(listaTipoDados,"Tipo de dados:");
                                    final String etiquetaAtr = Console.readLine("Etiqueta:");
                                    try {
                                        this.theController.registerAtributoFormularioAtividadeManualExecucao(nomeAtributo, descricao, expressaoRegular, tipoDeDados, etiquetaAtr);
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
                                this.theController.registerFormularioAtividadeManualExecucao(codigoAlfaNumericoFormulario, nome, etiqueta, y);
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
                        this.theController.registerWorkflowTransaction(servicoSelecionado);
                    }
            }
            else{

                DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
                Calendar obj = Calendar.getInstance();
                String dataMudanca = formatter.format(obj.getTime());
                String servicoAprovacao;
                String atividadeEscolhida;

                String codigoAlfaNumericoFormulario;
                if (Utils.confirm("Pretende alterar o workflow do serviço?(S/N)")) {
                    List<String> atividadesResolucao = new LinkedList<>();
                    atividadesResolucao.add("Atividade Manual");
                    atividadesResolucao.add("Atividade Automática");



                    if(Utils.confirm("Deseja proceder à especificação da data a partir da qual começará a ser aplicado o workflow(por default esta data será definida como a data da mudança do workflow?(S/N)")) {
                        dataMudanca = Console.readLine("Data de mudança:");
                    }

                    if (Utils.confirm("Este serviço terá uma atividade de aprovação?(S/N)")) {
                        System.out.println("Especificação do formulário da atividade manual de aprovação");
                        codigoAlfaNumericoFormulario = Console.readLine("Código alfanumérico do formulário:");

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
                            this.theController.registerFormularioAtividadeManualAprovacao(codigoAlfaNumericoFormulario, nome, etiqueta, y);
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
                        codigoAlfaNumericoFormulario = Console.readLine("Código alfanumérico do formulário:");

                        nome = Console.readLine("Nome do formulário:");

                        etiqueta = Console.readLine("Etiqueta:");

                        flag = true;
                        while (flag) {
                            if (Utils.confirm("Deseja adicionar um atributo?(S/N)")) {
                                final String nomeAtributo = Console.readLine("Nome:");
                                final String descricao = Console.readLine("Descricao:");
                                final String expressaoRegular = Console.readLine("Expressão regular:");
                                final String tipoDeDados = (String) Utils.showsAndSelect(listaTipoDados,"Tipo de dados:");
                                final String etiquetaAtributo = Console.readLine("Etiqueta");
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
                            this.theController.registerFormularioAtividadeManualExecucao(codigoAlfaNumericoFormulario, nome, etiqueta, y);
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
                    this.theController.changeWorkflowTransaction(servicoSelecionado);
                }
            }
            String estadoServico;
            if(servicoSelecionado.criticidade()!=null && servicoSelecionado.descricaoBreve()!=null && servicoSelecionado.descricaoCompleta()!=null
                    && servicoSelecionado.feedback()!=null && servicoSelecionado.icone()!=null && !servicoSelecionado.keywords().isEmpty() &&
                    catalogoDoServico!=null && servicoSelecionado.formularioSolicitacao()!=null && servicoSelecionado.workflow()!=null && servicoSelecionado.estado().equalsIgnoreCase("Incompleto")){
                estadoServico="Completo";
                this.theController.changeEstadoServico(estadoServico,servicoSelecionado);
            }
        }
        return false;
    }

    @Override
    public String headline() {
        return "Alterar serviço";
    }
}
