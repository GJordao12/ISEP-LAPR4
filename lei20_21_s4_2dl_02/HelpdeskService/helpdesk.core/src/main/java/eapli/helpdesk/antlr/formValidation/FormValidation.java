package eapli.helpdesk.antlr.formValidation;

import eapli.helpdesk.atividade.Script;
import eapli.helpdesk.servico.domain.AtributoFormulario;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FormValidation {
    public static boolean verificarDados(Script scriptFormulario, List<String> respostas, List<AtributoFormulario> atributos) throws IOException {
        String conteudo = Files.readString(scriptFormulario.script().toPath(), StandardCharsets.UTF_8);
        if(conteudo == null) {
            return true;
        }
        FormValidationGrammarLexer lexer = new FormValidationGrammarLexer(CharStreams.fromFileName(scriptFormulario.script().toString()));
        CommonTokenStream token = new CommonTokenStream(lexer);
        FormValidationGrammarParser parser = new FormValidationGrammarParser(token);
        ParseTree parseTree = parser.start(); // parse
        List<AtributoFormulario> aux = new ArrayList<>(atributos);
        FormVisitor formVisitor = new FormVisitor(respostas, aux);
        Object obj = formVisitor.visit(parseTree);

        if((boolean) obj) {
            return true;
        } else {
            System.out.println("Dados introduzidos de forma inválida, por favor insira os dados novamente...\n");
            return false;
        }
    }
}
