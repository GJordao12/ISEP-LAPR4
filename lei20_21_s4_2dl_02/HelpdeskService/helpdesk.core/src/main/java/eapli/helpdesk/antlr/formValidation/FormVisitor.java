package eapli.helpdesk.antlr.formValidation;

import eapli.helpdesk.servico.domain.AtributoFormulario;
import java.util.ArrayList;
import java.util.List;

public class FormVisitor extends FormValidationGrammarBaseVisitor {

    private final List<AtributoFormulario> atributos;
    private final List<String> respostas;
    private List<String> erros = new ArrayList<>();
    private Boolean erroEncontrado = false;

    public FormVisitor(List<String> respostas, List<AtributoFormulario> atributos) {
        this.respostas = respostas;
        this.atributos = atributos;
    }

    @Override
    public Boolean visitStart(FormValidationGrammarParser.StartContext ctx) {
        try {
            erros = new ArrayList<>();
            if (ctx.start() != null) {
                if (!((Boolean) this.visit(ctx.start()))) {
                }
            }
            if (ctx.ifCondition() != null) {
                if (!((Boolean) this.visit(ctx.ifCondition()))) {
                    for (String s : erros) {
                        System.out.println(s);
                    }
                    erroEncontrado = true;
                    return false;
                }
            }
            if (ctx.specs() != null) {
                if (!((Boolean) this.visit(ctx.specs()))) {
                    for (String s : erros) {
                        System.out.println(s);
                    }
                    erroEncontrado = true;
                    return false;
                }
            }
            return !erroEncontrado;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean visitIfCondition(FormValidationGrammarParser.IfConditionContext ctx) {
        try {
            if (!((Boolean) this.visit(ctx.condicoes()))) {
                return true;
            }
            FormValidationGrammarParser.DadosContext valorDados = ctx.dados();
            Object eval;
            Boolean flag = true;
            do {
                if (valorDados.condicoesDados() != null) {
                    eval = this.visit(valorDados.condicoesDados().condicoes());
                    if (!((Boolean) eval)) {
                        flag = false;
                    }
                }
                if (valorDados.specs() != null) {
                    eval = this.visit(valorDados.specs());
                    if (!((Boolean) eval)) {
                        flag = false;
                    }
                }
                if (valorDados.ifCondition() != null) {
                    eval = this.visit(valorDados.ifCondition());
                    if (!((Boolean) eval)) {
                        flag = false;
                    }
                }
                valorDados = valorDados.dados();
            } while (valorDados != null);
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean visitCondicoes(FormValidationGrammarParser.CondicoesContext ctx) {
        try {
            Object eval;
            FormValidationGrammarParser.CondicoesContext condicoes = ctx.condicoes();
            do {
                eval = this.visit(condicoes);
                if (!((Boolean) eval)) {
                    return false;
                }
            } while (condicoes != null);

            String operator = ctx.OperadorCondicional().toString();
            Object eval2 = this.visit(ctx.condicao());

            switch (operator) {
                case "&&":
                    if ((Boolean) eval && (Boolean) eval2) {
                        return true;
                    } else {
                        return false;
                    }
                case "||":
                    if ((Boolean) eval || (Boolean) eval2) {
                        return true;
                    } else {
                        return false;
                    }
                default:
                    return false;

            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean visitValoresIguais(FormValidationGrammarParser.ValoresIguaisContext ctx) {
        try {
            double valor = Double.parseDouble(respostas.get(Integer.parseInt(ctx.Inteiro().toString()) - 1));
            double valorCalculado = visitValInt((FormValidationGrammarParser.ValIntContext) ctx.Inteiro());
            if (valorCalculado == 0.0) {
                System.out.println("Ocorreu um erro no cálculo...\n");
                return false;
            }
            if (ctx.Igual_Diferente() == null && ctx.OperadorLogico() != null) {
                String operador = ctx.OperadorLogico().toString();
                switch (operador) {
                    case ">":
                        if (valor > valorCalculado) {
                            return true;
                        }
                        erros.add(String.format("O valor do atributo %s não é superior ao valor calculado: %.2f\n", atributos.get(Integer.parseInt(ctx.Inteiro().toString()) - 1).nomeAtributo(), valorCalculado));
                        return false;
                    case "<":
                        if (valor < valorCalculado) {
                            return true;
                        }
                        erros.add(String.format("O valor do atributo %s não é inferior ao valor calculado: %.2f\n", atributos.get(Integer.parseInt(ctx.Inteiro().toString()) - 1).nomeAtributo(), valorCalculado));
                        return false;
                    case ">=":
                        if (valor >= valorCalculado) {
                            return true;
                        }
                        erros.add(String.format("O valor do atributo %s não é superior nem igual ao valor calculado: %.2f\n", atributos.get(Integer.parseInt(ctx.Inteiro().toString()) - 1).nomeAtributo(), valorCalculado));
                        return false;
                    case "<=":
                        if (valor <= valorCalculado) {
                            return true;
                        }
                        erros.add(String.format("O valor do atributo %s não é inferior nem igual ao valor calculado: %.2f\n", atributos.get(Integer.parseInt(ctx.Inteiro().toString()) - 1).nomeAtributo(), valorCalculado));
                        return false;
                }
            } else if (ctx.Igual_Diferente() != null && ctx.OperadorLogico() == null) {
                String operator = ctx.Igual_Diferente().toString();
                switch (operator) {
                    case "!=":
                        if (valor != valorCalculado) {
                            return true;
                        }
                        erros.add(String.format("O valor do atributo %s não é diferente ao valor calculado: %.2f\n", atributos.get(Integer.parseInt(ctx.Inteiro().toString()) - 1).nomeAtributo(), valorCalculado));
                        return false;
                    case "==":
                        if (valor == valorCalculado) {
                            return true;
                        }
                        erros.add(String.format("O valor do atributo %s não é igual ao valor calculado: %.2f\n", atributos.get(Integer.parseInt(ctx.Inteiro().toString()) - 1).nomeAtributo(), valorCalculado));
                        return false;
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean visitEstadoValor(FormValidationGrammarParser.EstadoValorContext ctx) {
        try {
            String value = respostas.get(Integer.parseInt(ctx.Inteiro().toString()) - 1);
            String state = ctx.EstadoString().toString();
            if (state.equalsIgnoreCase("preenchido")) {
                if (value.isBlank()) {
                    erros.add(String.format("O atributo %s não foi preenchido mas devia...\n", atributos.get(Integer.parseInt(ctx.Inteiro().toString()) - 1).nomeAtributo()));
                    return false;
                }
                return true;
            } else if (state.equalsIgnoreCase("vazio")) {
                if (value.isBlank()) {
                    return true;
                }
                erros.add(String.format("O atributo %s foi preenchido mas não devia...\n", atributos.get(Integer.parseInt(ctx.Inteiro().toString()) - 1).nomeAtributo()));
                return false;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public Boolean visitTamanhoValor(FormValidationGrammarParser.TamanhoValorContext ctx) {
        String value = respostas.get(Integer.parseInt(ctx.Inteiro().get(0).toString()) - 1);
        int tamanho = Integer.parseInt(ctx.Inteiro().get(1).toString());
        try {
            if (value.length() == tamanho) {
                return true;
            }
            erros.add(String.format("O tamanho do valor %s não é %s\n", value, tamanho));
            return false;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public Boolean visitValorEntre(FormValidationGrammarParser.ValorEntreContext ctx) {
        try {
            int valor = Integer.parseInt(respostas.get(Integer.parseInt(ctx.Inteiro().toString()) - 1));
            int valor1 = Integer.parseInt(ctx.intervalo().Inteiro().get(0).toString());
            int valor2 = Integer.parseInt(ctx.intervalo().Inteiro().get(1).toString());
            if (valor <= valor1 && valor >= valor2) {
                return true;
            }
            erros.add(String.format("O valor %d não se encontra entre %d e %d\n", valor, valor1, valor2));
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean visitValorObrigatorio(FormValidationGrammarParser.ValorObrigatorioContext ctx) {
        try {
            String valor = respostas.get(Integer.parseInt(ctx.Inteiro().toString()) - 1);
            String obrigatoriedade = ctx.Obrigatoriedade().toString();

            if (obrigatoriedade.equalsIgnoreCase("obrigatorio")) {
                if (valor.isBlank()) {
                    erros.add(String.format("Não respondeu ao atributo %s, mas este trata-se de um campo obrigatório!\n", atributos.get(Integer.parseInt(ctx.Inteiro().toString()) - 1).nomeAtributo()));
                    return false;
                }
                return true;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean visitValorString(FormValidationGrammarParser.ValorStringContext ctx) {
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
                        return true;
                    }
                    erros.add(String.format("O atributo %s deveria ser %s mas não é...\n", atributos.get(Integer.parseInt(ctx.Inteiro().toString()) - 1).nomeAtributo(), valor));
                    return false;

                case "!=":
                    if (!value.equals(valor)) {
                        return true;
                    }
                    erros.add(String.format("O atributo %s não deveria ser %s mas é...\n", atributos.get(Integer.parseInt(ctx.Inteiro().toString()) - 1).nomeAtributo(), valor));
                    return false;
                default:
                    return false;
            }
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public Boolean visitValorString1(FormValidationGrammarParser.ValorString1Context ctx) {
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
                        return true;
                    }
                    erros.add(String.format("O atributo %s deveria ser %s mas não é...\n", atributos.get(Integer.parseInt(ctx.Inteiro().toString()) - 1).nomeAtributo(), valor));
                    return false;

                case "!=":
                    if (!value.equals(valor)) {
                        return true;
                    }
                    erros.add(String.format("O atributo %s não deveria ser %s mas é...\n", atributos.get(Integer.parseInt(ctx.Inteiro().toString()) - 1).nomeAtributo(), valor));
                    return false;
                default:
                    return false;
            }
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public Boolean visitValorBooleano(FormValidationGrammarParser.ValorBooleanoContext ctx) {
        try {
            String value = respostas.get(Integer.parseInt(ctx.Inteiro().toString()) - 1);
            String operator = ctx.Igual_Diferente().toString();
            String valorBooleano = ctx.ValorBooleano().toString();

            switch (operator) {
                case "==":
                    if (value.equals(valorBooleano)) {
                        return true;
                    }
                    erros.add(String.format("O atributo %s não é %s\n", atributos.get(Integer.parseInt(ctx.Inteiro().toString()) - 1).nomeAtributo(), valorBooleano));
                    return false;
                case "!=":
                    if (!value.equals(valorBooleano)) {
                        return true;
                    }
                    erros.add(String.format("O atributo %s não é %s\n", atributos.get(Integer.parseInt(ctx.Inteiro().toString()) - 1).nomeAtributo(), valorBooleano));
                    return false;
                default:
                    return false;
            }
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    @Override
    public Boolean visitValorBooleano1(FormValidationGrammarParser.ValorBooleano1Context ctx) {
        try {
            String value = respostas.get(Integer.parseInt(ctx.Inteiro().toString()) - 1);
            String operator = ctx.Igual_Diferente().toString();
            String valorBooleano = ctx.ValorBooleano().toString();

            switch (operator) {
                case "==":
                    if (value.equals(valorBooleano)) {
                        return true;
                    }
                    erros.add(String.format("O atributo %s não é %s\n", atributos.get(Integer.parseInt(ctx.Inteiro().toString()) - 1).nomeAtributo(), valorBooleano));
                    return false;
                case "!=":
                    if (!value.equals(valorBooleano)) {
                        return true;
                    }
                    erros.add(String.format("O atributo %s não é %s\n", atributos.get(Integer.parseInt(ctx.Inteiro().toString()) - 1).nomeAtributo(), valorBooleano));
                    return false;
                default:
                    return false;
            }
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    public Double visitValInt(FormValidationGrammarParser.ValIntContext ctx) {
        try {
            String operador = ctx.Operador().toString();
            double v1 = Double.parseDouble(ctx.Inteiro().toString());
            if (ctx.Inteiro() == null) {
                double v2 = Double.parseDouble(ctx.valInt().toString());
                switch (operador) {
                    case "+":
                        return v1 + v2;
                    case "-":
                        return v1 - v2;
                    case "*":
                        return v1 * v2;
                    case "/":
                        return v1 / v2;
                    default:
                        return 0.0;
                }
            } else {
                double v2 = visitValInt(ctx.valInt());
                switch (operador) {
                    case "+":
                        return v1 + v2;
                    case "-":
                        return v1 - v2;
                    case "*":
                        return v1 * v2;
                    case "/":
                        return v1 / v2;
                    default:
                        return 0.0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }
}
