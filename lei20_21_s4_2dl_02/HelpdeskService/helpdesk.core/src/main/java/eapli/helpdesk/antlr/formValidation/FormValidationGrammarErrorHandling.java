package eapli.helpdesk.antlr.formValidation;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.ParseCancellationException;

public class FormValidationGrammarErrorHandling extends BaseErrorListener {
    public static final FormValidationGrammarErrorHandling INSTANCE = new FormValidationGrammarErrorHandling();

    @Override
    public void syntaxError(Recognizer<?,?> recognizer, Object o, int i, int i1, String s, RecognitionException r) throws ParseCancellationException{
                throw new ParseCancellationException("line"+i+","+i1+","+s);
    }
}
