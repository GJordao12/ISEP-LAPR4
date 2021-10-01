package eapli.helpdesk.app.executorTarefasAutomaticas.automaticsTaskScript;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import eapli.helpdesk.atividade.Script;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.io.IOException;

public class ValidateScriptAutomaticTask {
    public static boolean validateScriptTarefaAutomatica(Script script)  {
        try {
            String content = Files.toString(script.script(), Charsets.UTF_8);
            if(content == null){
                System.out.println("This script doesn't exist");
                return false;
            }else {
                CharStream charStream = CharStreams.fromString(content);
                AutomaticTaskScriptGrammarLexer atividadeLexer = new AutomaticTaskScriptGrammarLexer(charStream);
                atividadeLexer.removeErrorListeners();
                atividadeLexer.addErrorListener(AutomaticTaskErrorHandling.INSTANCE);
                CommonTokenStream commonTokenStream = new CommonTokenStream(atividadeLexer);
                AutomaticTaskScriptGrammarParser automaticTaskScriptGrammarParser = new AutomaticTaskScriptGrammarParser(commonTokenStream);
                automaticTaskScriptGrammarParser.removeErrorListeners();
                automaticTaskScriptGrammarParser.addErrorListener(AutomaticTaskErrorHandling.INSTANCE);

                ParserRuleContext tree = automaticTaskScriptGrammarParser.start();
                return true;
            }
        } catch (ParseCancellationException | IOException ex){
            System.out.println("This script is not valid");
            return false;
        }
    }

}
