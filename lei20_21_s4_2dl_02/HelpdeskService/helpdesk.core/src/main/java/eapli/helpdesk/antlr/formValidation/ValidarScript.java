package eapli.helpdesk.antlr.formValidation;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import eapli.helpdesk.atividade.Script;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.io.IOException;

public class ValidarScript {

    public static boolean validateScript(Script script)  {
        try {
            String content = Files.toString(script.script(), Charsets.UTF_8);
            if(content == null){
                System.out.println("This script doesn't exist");
                return false;
            }else {
                CharStream charStream = CharStreams.fromString(content);
                FormValidationGrammarLexer formLexer = new FormValidationGrammarLexer(charStream);
                formLexer.removeErrorListeners();
                formLexer.addErrorListener(FormValidationGrammarErrorHandling.INSTANCE);
                CommonTokenStream commonTokenStream = new CommonTokenStream(formLexer);
                FormValidationGrammarParser formParser = new FormValidationGrammarParser(commonTokenStream);
                formParser.removeErrorListeners();
                formParser.addErrorListener(FormValidationGrammarErrorHandling.INSTANCE);

                ParserRuleContext tree = formParser.start();
                return true;
            }
        } catch (ParseCancellationException | IOException ex){
            System.out.println("This script is not valid");
            return false;
        }
    }
}
