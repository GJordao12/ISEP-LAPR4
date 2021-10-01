package eapli.helpdesk.antlr.formValidation;

import eapli.helpdesk.colaborador.domain.PhoneNumber;
import eapli.helpdesk.colaborador.exception.PhoneNumberException;
import eapli.helpdesk.colaborador.repositories.CollaboratorRepository;
import eapli.helpdesk.infraestrutura.PersistenceContext;
import eapli.helpdesk.servico.domain.AtributoFormulario;
import eapli.helpdesk.servico.domain.AtributoPreenchido;
import eapli.helpdesk.servico.domain.FormularioPreenchido;
import eapli.helpdesk.ticket.domain.Ticket;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Stack;

public class FormValidationGrammar {


    public static void main(String[] args) throws IOException {

        parseWithListener("ScriptsTarefasAutomaticas/ScriptServicoConsultarSalario.txt", null);
    }

    public static boolean parseWithListener(String script, FormularioPreenchido form) throws IOException {
        FormValidationGrammarLexer lexer = new FormValidationGrammarLexer(CharStreams.fromFileName(script));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        FormValidationGrammarParser parser = new FormValidationGrammarParser(tokens);
        ParseTree tree = parser.start(); // parse
        ParseTreeWalker walker = new ParseTreeWalker();
        List<AtributoFormulario> atributos = null;
        List<String> respostas = null;
        EvalListener eListener = new EvalListener(atributos, respostas);
        walker.walk(eListener, tree);
        return eListener.getResult();
    }


    static class EvalListener extends FormValidationGrammarBaseListener{
        private final List<AtributoFormulario> atributos;
        private final List<String> respostas;
        private List<String> erros = new ArrayList<>();
        private boolean flag = true;

        public EvalListener(List<AtributoFormulario> atributos, List<String> respostas) {
            super();
            this.atributos = atributos;
            this.respostas = respostas;
        }

        public boolean getResult() {
            return this.flag;
        }


        @Override
        public void exitTamanhoValor(FormValidationGrammarParser.TamanhoValorContext ctx) {
            String value = respostas.get(Integer.parseInt(ctx.Inteiro().get(0).toString()) - 1);
            int tamanho = Integer.parseInt(ctx.Inteiro().get(1).toString());
            try {
                if (value.length() == tamanho) {
                   this.flag = true;
                }
                erros.add(String.format("O tamanho do valor %s não é %s\n", value, tamanho));
                this.flag=false;
            } catch (Exception e) {
                e.printStackTrace();

                this.flag=false;
            }
        }

        @Override
        public void exitValorEntre(FormValidationGrammarParser.ValorEntreContext ctx) {
            try {
                int valor = Integer.parseInt(respostas.get(Integer.parseInt(ctx.Inteiro().toString()) - 1));
                int valor1 = Integer.parseInt(ctx.intervalo().Inteiro().get(0).toString());
                int valor2 = Integer.parseInt(ctx.intervalo().Inteiro().get(1).toString());
                if (valor <= valor1 && valor >= valor2) {
                    this.flag= true;
                }
                erros.add(String.format("O valor %d não se encontra entre %d e %d\n", valor, valor1, valor2));
                this.flag= false;
            } catch (Exception e) {
                e.printStackTrace();
                this.flag= false;
            }
        }

        @Override
        public void exitValorObrigatorio(FormValidationGrammarParser.ValorObrigatorioContext ctx) {
            try {
                String valor = respostas.get(Integer.parseInt(ctx.Inteiro().toString()) - 1);
                String obrigatoriedade = ctx.Obrigatoriedade().toString();

                if (obrigatoriedade.equalsIgnoreCase("obrigatorio")) {
                    if (valor.isBlank()) {
                        erros.add(String.format("Não respondeu ao atributo %s, mas este trata-se de um campo obrigatório!\n", atributos.get(Integer.parseInt(ctx.Inteiro().toString()) - 1).nomeAtributo()));
                        this.flag= false;
                    }
                    this.flag= true;
                } else {
                    this.flag= true;
                }
            } catch (Exception e) {
                e.printStackTrace();
                this.flag= false;
            }
        }

        @Override
        public void exitValorString(FormValidationGrammarParser.ValorStringContext ctx) {
            try {
                String value = respostas.get(Integer.parseInt(ctx.Inteiro().toString()) - 1);
                StringBuilder aux = new StringBuilder();

                FormValidationGrammarParser.ValStringContext context = ctx.condicaoString().stringEntreAspas().valString();
                do {
                    aux.append(context.String().toString());
                    aux.append(" ");
                    context = context.valString();
                } while (context != null);

                String valor = aux.toString();
                valor = valor.replace("\"", "");

                String operator = ctx.Igual_Diferente().toString();

                switch (operator) {
                    case "==":
                        if (value.trim().equalsIgnoreCase((valor.trim()))) {
                            this.flag= true;
                        }
                        erros.add(String.format("O atributo %s deveria ser %s mas não é...\n", atributos.get(Integer.parseInt(ctx.Inteiro().toString()) - 1).nomeAtributo(), valor));
                        this.flag= false;

                    case "!=":
                        if (!value.equals(valor)) {
                            this.flag= true;
                        }
                        erros.add(String.format("O atributo %s não deveria ser %s mas é...\n", atributos.get(Integer.parseInt(ctx.Inteiro().toString()) - 1).nomeAtributo(), valor));
                        this.flag= false;
                    default:
                        this.flag= false;
                }
            } catch (Exception e) {
                e.printStackTrace();

                this.flag= false;
            }
        }

        @Override
        public void exitValorString1(FormValidationGrammarParser.ValorString1Context ctx) {
            try {
                String value = respostas.get(Integer.parseInt(ctx.Inteiro().toString()) - 1);
                StringBuilder aux = new StringBuilder();

                FormValidationGrammarParser.ValStringContext context = ctx.condicaoString().stringEntreAspas().valString();
                do {
                    aux.append(context.String().toString());
                    aux.append(" ");
                    context = context.valString();
                } while (context != null);

                String valor = aux.toString();
                valor = valor.replace("\"", "");

                String operator = ctx.Igual_Diferente().toString();

                switch (operator) {
                    case "==":
                        if (value.trim().equalsIgnoreCase((valor.trim()))) {
                            this.flag= true;
                        }
                        erros.add(String.format("O atributo %s deveria ser %s mas não é...\n", atributos.get(Integer.parseInt(ctx.Inteiro().toString()) - 1).nomeAtributo(), valor));
                        this.flag= false;

                    case "!=":
                        if (!value.equals(valor)) {
                            this.flag= true;
                        }
                        erros.add(String.format("O atributo %s não deveria ser %s mas é...\n", atributos.get(Integer.parseInt(ctx.Inteiro().toString()) - 1).nomeAtributo(), valor));
                        this.flag= false;
                    default:
                        this.flag= false;
                }
            } catch (Exception e) {
                e.printStackTrace();

                this.flag= false;
            }
        }

        @Override
        public void exitValorBooleano(FormValidationGrammarParser.ValorBooleanoContext ctx) {
            try {
                String value = respostas.get(Integer.parseInt(ctx.Inteiro().toString()) - 1);
                String operator = ctx.Igual_Diferente().toString();
                String valorBooleano = ctx.ValorBooleano().toString();

                switch (operator) {
                    case "==":
                        if (value.equals(valorBooleano)) {
                            this.flag= true;
                        }
                        erros.add(String.format("O atributo %s não é %s\n", atributos.get(Integer.parseInt(ctx.Inteiro().toString()) - 1).nomeAtributo(), valorBooleano));
                        this.flag= false;
                    case "!=":
                        if (!value.equals(valorBooleano)) {
                            this.flag= true;
                        }
                        erros.add(String.format("O atributo %s não é %s\n", atributos.get(Integer.parseInt(ctx.Inteiro().toString()) - 1).nomeAtributo(), valorBooleano));
                        this.flag= false;
                    default:
                        this.flag= false;
                }
            } catch (Exception e) {
                e.printStackTrace();

                this.flag= false;
            }
        }

        @Override
        public void exitValorBooleano1(FormValidationGrammarParser.ValorBooleano1Context ctx) {
            try {
                String value = respostas.get(Integer.parseInt(ctx.Inteiro().toString()) - 1);
                String operator = ctx.Igual_Diferente().toString();
                String valorBooleano = ctx.ValorBooleano().toString();

                switch (operator) {
                    case "==":
                        if (value.equals(valorBooleano)) {
                            this.flag= true;
                        }
                        erros.add(String.format("O atributo %s não é %s\n", atributos.get(Integer.parseInt(ctx.Inteiro().toString()) - 1).nomeAtributo(), valorBooleano));
                        this.flag= false;
                    case "!=":
                        if (!value.equals(valorBooleano)) {
                            this.flag= true;
                        }
                        erros.add(String.format("O atributo %s não é %s\n", atributos.get(Integer.parseInt(ctx.Inteiro().toString()) - 1).nomeAtributo(), valorBooleano));
                        this.flag= false;
                    default:
                        this.flag= false;
                }
            } catch (Exception e) {
                e.printStackTrace();

                this.flag= false;
            }
        }

        public boolean changeDataInDB(Ticket ticket, String campo, Integer option) throws PhoneNumberException {
            boolean flag = false;
            CollaboratorRepository collaboratorRepository = PersistenceContext.repositories().collaborators();
            for (AtributoPreenchido atributoPreenchido : ticket.formularioPreenchido().atributoPreenchidoList()) {
                if (atributoPreenchido.atributoFormulario().nome().toString().equalsIgnoreCase(campo)) {
                    if (option == 13) {
                        String valor = "" + atributoPreenchido.valor();
                        ticket.collaborator().newPhoneNumber(new PhoneNumber(valor));
                    }
                    if (option == 12) {
                        String distrito = atributoPreenchido.nome();
                        ticket.collaborator().address().newDistrito(distrito);
                    }
                    if (option == 14) {
                        String concelho = atributoPreenchido.nome();
                        ticket.collaborator().address().newConcelho(concelho);
                    }
                    collaboratorRepository.save(ticket.collaborator());
                    flag = true;
                    break;
                }
            }
            return flag;
        }
    }
}






