// Generated from C:/Users/Admin/Desktop/Repositorio/HelpdeskService/helpdesk.core/src/main/java/eapli/helpdesk/antlr/formValidation\FormValidationGrammar.g4 by ANTLR 4.9.1
package eapli.helpdesk.antlr.formValidation;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FormValidationGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, PONTO_E_VIRGULA=9, 
		ValorBooleano=10, Tipo=11, EstadoString=12, Obrigatoriedade=13, OperadorLogico=14, 
		Igual_Diferente=15, OperadorCondicional=16, Operador=17, String=18, Inteiro=19, 
		WS=20;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "PONTO_E_VIRGULA", 
			"ValorBooleano", "Tipo", "EstadoString", "Obrigatoriedade", "OperadorLogico", 
			"Igual_Diferente", "OperadorCondicional", "Operador", "String", "Inteiro", 
			"WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'if'", "'then'", "'tamanho: '", "'esta entre '", "'-> '", "'e'", 
			"':'", "'\"'", "';'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "PONTO_E_VIRGULA", 
			"ValorBooleano", "Tipo", "EstadoString", "Obrigatoriedade", "OperadorLogico", 
			"Igual_Diferente", "OperadorCondicional", "Operador", "String", "Inteiro", 
			"WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public FormValidationGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "FormValidationGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\26\u00c6\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13_\n\13\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\fr\n\f\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u0083\n\r\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u009f\n\16"+
		"\3\17\3\17\3\17\3\17\3\17\5\17\u00a6\n\17\3\20\3\20\3\20\3\20\5\20\u00ac"+
		"\n\20\3\21\3\21\3\21\3\21\5\21\u00b2\n\21\3\22\3\22\3\23\6\23\u00b7\n"+
		"\23\r\23\16\23\u00b8\3\24\6\24\u00bc\n\24\r\24\16\24\u00bd\3\25\6\25\u00c1"+
		"\n\25\r\25\16\25\u00c2\3\25\3\25\2\2\26\3\3\5\4\7\5\t\6\13\7\r\b\17\t"+
		"\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26\3\2"+
		"\7\4\2>>@@\5\2,-//\61\61\4\2C\\c|\3\2\62;\5\2\13\f\17\17\"\"\2\u00d1\2"+
		"\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2"+
		"\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2"+
		"\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2"+
		"\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\3+\3\2\2\2\5.\3\2\2\2\7\63\3\2\2"+
		"\2\t=\3\2\2\2\13I\3\2\2\2\rM\3\2\2\2\17O\3\2\2\2\21Q\3\2\2\2\23S\3\2\2"+
		"\2\25^\3\2\2\2\27q\3\2\2\2\31\u0082\3\2\2\2\33\u009e\3\2\2\2\35\u00a5"+
		"\3\2\2\2\37\u00ab\3\2\2\2!\u00b1\3\2\2\2#\u00b3\3\2\2\2%\u00b6\3\2\2\2"+
		"\'\u00bb\3\2\2\2)\u00c0\3\2\2\2+,\7k\2\2,-\7h\2\2-\4\3\2\2\2./\7v\2\2"+
		"/\60\7j\2\2\60\61\7g\2\2\61\62\7p\2\2\62\6\3\2\2\2\63\64\7v\2\2\64\65"+
		"\7c\2\2\65\66\7o\2\2\66\67\7c\2\2\678\7p\2\289\7j\2\29:\7q\2\2:;\7<\2"+
		"\2;<\7\"\2\2<\b\3\2\2\2=>\7g\2\2>?\7u\2\2?@\7v\2\2@A\7c\2\2AB\7\"\2\2"+
		"BC\7g\2\2CD\7p\2\2DE\7v\2\2EF\7t\2\2FG\7g\2\2GH\7\"\2\2H\n\3\2\2\2IJ\7"+
		"/\2\2JK\7@\2\2KL\7\"\2\2L\f\3\2\2\2MN\7g\2\2N\16\3\2\2\2OP\7<\2\2P\20"+
		"\3\2\2\2QR\7$\2\2R\22\3\2\2\2ST\7=\2\2T\24\3\2\2\2UV\7v\2\2VW\7t\2\2W"+
		"X\7w\2\2X_\7g\2\2YZ\7h\2\2Z[\7c\2\2[\\\7n\2\2\\]\7u\2\2]_\7g\2\2^U\3\2"+
		"\2\2^Y\3\2\2\2_\26\3\2\2\2`a\7K\2\2ab\7p\2\2bc\7v\2\2cd\7g\2\2de\7i\2"+
		"\2ef\7g\2\2fr\7t\2\2gh\7T\2\2hi\7g\2\2ij\7c\2\2jr\7n\2\2kl\7U\2\2lm\7"+
		"v\2\2mn\7t\2\2no\7k\2\2op\7p\2\2pr\7i\2\2q`\3\2\2\2qg\3\2\2\2qk\3\2\2"+
		"\2r\30\3\2\2\2st\7r\2\2tu\7t\2\2uv\7g\2\2vw\7g\2\2wx\7p\2\2xy\7e\2\2y"+
		"z\7j\2\2z{\7k\2\2{|\7f\2\2|\u0083\7q\2\2}~\7x\2\2~\177\7c\2\2\177\u0080"+
		"\7|\2\2\u0080\u0081\7k\2\2\u0081\u0083\7q\2\2\u0082s\3\2\2\2\u0082}\3"+
		"\2\2\2\u0083\32\3\2\2\2\u0084\u0085\7q\2\2\u0085\u0086\7d\2\2\u0086\u0087"+
		"\7t\2\2\u0087\u0088\7k\2\2\u0088\u0089\7i\2\2\u0089\u008a\7c\2\2\u008a"+
		"\u008b\7v\2\2\u008b\u008c\7q\2\2\u008c\u008d\7t\2\2\u008d\u008e\7k\2\2"+
		"\u008e\u009f\7q\2\2\u008f\u0090\7p\2\2\u0090\u0091\7c\2\2\u0091\u0092"+
		"\7q\2\2\u0092\u0093\7\"\2\2\u0093\u0094\7q\2\2\u0094\u0095\7d\2\2\u0095"+
		"\u0096\7t\2\2\u0096\u0097\7k\2\2\u0097\u0098\7i\2\2\u0098\u0099\7c\2\2"+
		"\u0099\u009a\7v\2\2\u009a\u009b\7q\2\2\u009b\u009c\7t\2\2\u009c\u009d"+
		"\7k\2\2\u009d\u009f\7q\2\2\u009e\u0084\3\2\2\2\u009e\u008f\3\2\2\2\u009f"+
		"\34\3\2\2\2\u00a0\u00a6\t\2\2\2\u00a1\u00a2\7>\2\2\u00a2\u00a6\7?\2\2"+
		"\u00a3\u00a4\7@\2\2\u00a4\u00a6\7?\2\2\u00a5\u00a0\3\2\2\2\u00a5\u00a1"+
		"\3\2\2\2\u00a5\u00a3\3\2\2\2\u00a6\36\3\2\2\2\u00a7\u00a8\7?\2\2\u00a8"+
		"\u00ac\7?\2\2\u00a9\u00aa\7#\2\2\u00aa\u00ac\7?\2\2\u00ab\u00a7\3\2\2"+
		"\2\u00ab\u00a9\3\2\2\2\u00ac \3\2\2\2\u00ad\u00ae\7(\2\2\u00ae\u00b2\7"+
		"(\2\2\u00af\u00b0\7~\2\2\u00b0\u00b2\7~\2\2\u00b1\u00ad\3\2\2\2\u00b1"+
		"\u00af\3\2\2\2\u00b2\"\3\2\2\2\u00b3\u00b4\t\3\2\2\u00b4$\3\2\2\2\u00b5"+
		"\u00b7\t\4\2\2\u00b6\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00b6\3\2"+
		"\2\2\u00b8\u00b9\3\2\2\2\u00b9&\3\2\2\2\u00ba\u00bc\t\5\2\2\u00bb\u00ba"+
		"\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00bb\3\2\2\2\u00bd\u00be\3\2\2\2\u00be"+
		"(\3\2\2\2\u00bf\u00c1\t\6\2\2\u00c0\u00bf\3\2\2\2\u00c1\u00c2\3\2\2\2"+
		"\u00c2\u00c0\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c5"+
		"\b\25\2\2\u00c5*\3\2\2\2\r\2^q\u0082\u009e\u00a5\u00ab\u00b1\u00b8\u00bd"+
		"\u00c2\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}