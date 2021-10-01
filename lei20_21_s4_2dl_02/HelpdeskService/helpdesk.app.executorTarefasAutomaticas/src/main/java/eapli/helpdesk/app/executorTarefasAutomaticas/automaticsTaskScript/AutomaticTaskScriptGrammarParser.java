// Generated from C:/Users/grtjo/Documents/ISEP_Cadeiras/2Ano/2Semestre/LAPR4/lei20_21_s4_2dl_02/HelpdeskService/helpdesk.app.executorTarefasAutomaticas/src/main/java/eapli/helpdesk/app/executorTarefasAutomaticas/automaticsTaskScript\AutomaticTaskScriptGrammar.g4 by ANTLR 4.9.1
package eapli.helpdesk.app.executorTarefasAutomaticas.automaticsTaskScript;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class AutomaticTaskScriptGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, START=11, PARENTESE_RETO_ABRIR=12, PARENTESE_RETO_FECHAR=13, 
		CHAVETA_ABRIR=14, CHAVETA_FECHAR=15, PARENTESE_ABRIR=16, PARENTESE_FECHAR=17, 
		PONTO_E_VIRGULA=18, NUM=19, MULTIPLICACAO=20, DIVISAO=21, SOMA=22, SUBTRACAO=23, 
		AND=24, OR=25, CLASSE=26, XML_ATRIBUTO=27, XML_ATRIBUTO2=28, FICHEIRO_XML=29, 
		ALFANUM=30, FORM_ATRIBUTO=31, ALFANUM2=32, WS=33;
	public static final int
		RULE_start = 0, RULE_script = 1, RULE_instrucao_script = 2, RULE_condicao = 3, 
		RULE_operacao_matematica = 4, RULE_chamar_funcao = 5, RULE_funcaoEnviarEmail = 6, 
		RULE_funcaoMudarDistrito = 7, RULE_funcaoMudarTelemovel = 8, RULE_funcaoMudarConcelho = 9, 
		RULE_funcaoBuscarInfoXML = 10, RULE_passos = 11, RULE_buscarElemento = 12, 
		RULE_alterarElemento = 13, RULE_funcaoEnviarEmailComValor = 14, RULE_inteiro = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "script", "instrucao_script", "condicao", "operacao_matematica", 
			"chamar_funcao", "funcaoEnviarEmail", "funcaoMudarDistrito", "funcaoMudarTelemovel", 
			"funcaoMudarConcelho", "funcaoBuscarInfoXML", "passos", "buscarElemento", 
			"alterarElemento", "funcaoEnviarEmailComValor", "inteiro"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'='", "'EnviarEmail'", "','", "'MudarDistrito'", "'MudarTelemovel'", 
			"'MudarConcelho'", "'Ficheiro'", "'Elemento'", "'Buscar'", "'Alterar'", 
			"'START'", "'['", "']'", "'{'", "'}'", "'('", "')'", "';'", null, "'*'", 
			"'/'", "'+'", "'-'", "'AND'", "'OR'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, "START", 
			"PARENTESE_RETO_ABRIR", "PARENTESE_RETO_FECHAR", "CHAVETA_ABRIR", "CHAVETA_FECHAR", 
			"PARENTESE_ABRIR", "PARENTESE_FECHAR", "PONTO_E_VIRGULA", "NUM", "MULTIPLICACAO", 
			"DIVISAO", "SOMA", "SUBTRACAO", "AND", "OR", "CLASSE", "XML_ATRIBUTO", 
			"XML_ATRIBUTO2", "FICHEIRO_XML", "ALFANUM", "FORM_ATRIBUTO", "ALFANUM2", 
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

	@Override
	public String getGrammarFileName() { return "AutomaticTaskScriptGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public AutomaticTaskScriptGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class StartContext extends ParserRuleContext {
		public TerminalNode START() { return getToken(AutomaticTaskScriptGrammarParser.START, 0); }
		public TerminalNode CHAVETA_ABRIR() { return getToken(AutomaticTaskScriptGrammarParser.CHAVETA_ABRIR, 0); }
		public ScriptContext script() {
			return getRuleContext(ScriptContext.class,0);
		}
		public TerminalNode CHAVETA_FECHAR() { return getToken(AutomaticTaskScriptGrammarParser.CHAVETA_FECHAR, 0); }
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskScriptGrammarListener ) ((AutomaticTaskScriptGrammarListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskScriptGrammarListener ) ((AutomaticTaskScriptGrammarListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskScriptGrammarVisitor ) return ((AutomaticTaskScriptGrammarVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			match(START);
			setState(33);
			match(CHAVETA_ABRIR);
			setState(34);
			script();
			setState(35);
			match(CHAVETA_FECHAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ScriptContext extends ParserRuleContext {
		public List<Instrucao_scriptContext> instrucao_script() {
			return getRuleContexts(Instrucao_scriptContext.class);
		}
		public Instrucao_scriptContext instrucao_script(int i) {
			return getRuleContext(Instrucao_scriptContext.class,i);
		}
		public ScriptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_script; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskScriptGrammarListener ) ((AutomaticTaskScriptGrammarListener)listener).enterScript(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskScriptGrammarListener ) ((AutomaticTaskScriptGrammarListener)listener).exitScript(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskScriptGrammarVisitor ) return ((AutomaticTaskScriptGrammarVisitor<? extends T>)visitor).visitScript(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScriptContext script() throws RecognitionException {
		ScriptContext _localctx = new ScriptContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_script);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(37);
				instrucao_script();
				}
				}
				setState(40); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << XML_ATRIBUTO))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Instrucao_scriptContext extends ParserRuleContext {
		public CondicaoContext condicao() {
			return getRuleContext(CondicaoContext.class,0);
		}
		public Chamar_funcaoContext chamar_funcao() {
			return getRuleContext(Chamar_funcaoContext.class,0);
		}
		public Instrucao_scriptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instrucao_script; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskScriptGrammarListener ) ((AutomaticTaskScriptGrammarListener)listener).enterInstrucao_script(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskScriptGrammarListener ) ((AutomaticTaskScriptGrammarListener)listener).exitInstrucao_script(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskScriptGrammarVisitor ) return ((AutomaticTaskScriptGrammarVisitor<? extends T>)visitor).visitInstrucao_script(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Instrucao_scriptContext instrucao_script() throws RecognitionException {
		Instrucao_scriptContext _localctx = new Instrucao_scriptContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_instrucao_script);
		try {
			setState(44);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case XML_ATRIBUTO:
				enterOuterAlt(_localctx, 1);
				{
				setState(42);
				condicao(0);
				}
				break;
			case T__1:
			case T__3:
			case T__4:
			case T__5:
			case T__6:
				enterOuterAlt(_localctx, 2);
				{
				setState(43);
				chamar_funcao();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CondicaoContext extends ParserRuleContext {
		public Operacao_matematicaContext operacao_matematica() {
			return getRuleContext(Operacao_matematicaContext.class,0);
		}
		public List<CondicaoContext> condicao() {
			return getRuleContexts(CondicaoContext.class);
		}
		public CondicaoContext condicao(int i) {
			return getRuleContext(CondicaoContext.class,i);
		}
		public TerminalNode AND() { return getToken(AutomaticTaskScriptGrammarParser.AND, 0); }
		public TerminalNode OR() { return getToken(AutomaticTaskScriptGrammarParser.OR, 0); }
		public CondicaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condicao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskScriptGrammarListener ) ((AutomaticTaskScriptGrammarListener)listener).enterCondicao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskScriptGrammarListener ) ((AutomaticTaskScriptGrammarListener)listener).exitCondicao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskScriptGrammarVisitor ) return ((AutomaticTaskScriptGrammarVisitor<? extends T>)visitor).visitCondicao(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CondicaoContext condicao() throws RecognitionException {
		return condicao(0);
	}

	private CondicaoContext condicao(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		CondicaoContext _localctx = new CondicaoContext(_ctx, _parentState);
		CondicaoContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_condicao, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(47);
			operacao_matematica();
			}
			_ctx.stop = _input.LT(-1);
			setState(57);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(55);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
					case 1:
						{
						_localctx = new CondicaoContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_condicao);
						setState(49);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(50);
						match(AND);
						setState(51);
						condicao(4);
						}
						break;
					case 2:
						{
						_localctx = new CondicaoContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_condicao);
						setState(52);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(53);
						match(OR);
						setState(54);
						condicao(3);
						}
						break;
					}
					} 
				}
				setState(59);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Operacao_matematicaContext extends ParserRuleContext {
		public Token resultado;
		public InteiroContext inteiro() {
			return getRuleContext(InteiroContext.class,0);
		}
		public TerminalNode PONTO_E_VIRGULA() { return getToken(AutomaticTaskScriptGrammarParser.PONTO_E_VIRGULA, 0); }
		public TerminalNode XML_ATRIBUTO() { return getToken(AutomaticTaskScriptGrammarParser.XML_ATRIBUTO, 0); }
		public Operacao_matematicaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operacao_matematica; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskScriptGrammarListener ) ((AutomaticTaskScriptGrammarListener)listener).enterOperacao_matematica(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskScriptGrammarListener ) ((AutomaticTaskScriptGrammarListener)listener).exitOperacao_matematica(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskScriptGrammarVisitor ) return ((AutomaticTaskScriptGrammarVisitor<? extends T>)visitor).visitOperacao_matematica(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Operacao_matematicaContext operacao_matematica() throws RecognitionException {
		Operacao_matematicaContext _localctx = new Operacao_matematicaContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_operacao_matematica);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			((Operacao_matematicaContext)_localctx).resultado = match(XML_ATRIBUTO);
			setState(61);
			match(T__0);
			setState(62);
			inteiro();
			setState(63);
			match(PONTO_E_VIRGULA);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Chamar_funcaoContext extends ParserRuleContext {
		public FuncaoEnviarEmailContext funcaoEnviarEmail() {
			return getRuleContext(FuncaoEnviarEmailContext.class,0);
		}
		public FuncaoMudarDistritoContext funcaoMudarDistrito() {
			return getRuleContext(FuncaoMudarDistritoContext.class,0);
		}
		public FuncaoMudarTelemovelContext funcaoMudarTelemovel() {
			return getRuleContext(FuncaoMudarTelemovelContext.class,0);
		}
		public FuncaoMudarConcelhoContext funcaoMudarConcelho() {
			return getRuleContext(FuncaoMudarConcelhoContext.class,0);
		}
		public FuncaoBuscarInfoXMLContext funcaoBuscarInfoXML() {
			return getRuleContext(FuncaoBuscarInfoXMLContext.class,0);
		}
		public Chamar_funcaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_chamar_funcao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskScriptGrammarListener ) ((AutomaticTaskScriptGrammarListener)listener).enterChamar_funcao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskScriptGrammarListener ) ((AutomaticTaskScriptGrammarListener)listener).exitChamar_funcao(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskScriptGrammarVisitor ) return ((AutomaticTaskScriptGrammarVisitor<? extends T>)visitor).visitChamar_funcao(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Chamar_funcaoContext chamar_funcao() throws RecognitionException {
		Chamar_funcaoContext _localctx = new Chamar_funcaoContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_chamar_funcao);
		try {
			setState(70);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				enterOuterAlt(_localctx, 1);
				{
				setState(65);
				funcaoEnviarEmail();
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(66);
				funcaoMudarDistrito();
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(67);
				funcaoMudarTelemovel();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 4);
				{
				setState(68);
				funcaoMudarConcelho();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 5);
				{
				setState(69);
				funcaoBuscarInfoXML();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncaoEnviarEmailContext extends ParserRuleContext {
		public Token assunto;
		public Token corpo;
		public TerminalNode PARENTESE_ABRIR() { return getToken(AutomaticTaskScriptGrammarParser.PARENTESE_ABRIR, 0); }
		public TerminalNode PARENTESE_FECHAR() { return getToken(AutomaticTaskScriptGrammarParser.PARENTESE_FECHAR, 0); }
		public TerminalNode PONTO_E_VIRGULA() { return getToken(AutomaticTaskScriptGrammarParser.PONTO_E_VIRGULA, 0); }
		public List<TerminalNode> ALFANUM() { return getTokens(AutomaticTaskScriptGrammarParser.ALFANUM); }
		public TerminalNode ALFANUM(int i) {
			return getToken(AutomaticTaskScriptGrammarParser.ALFANUM, i);
		}
		public FuncaoEnviarEmailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcaoEnviarEmail; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskScriptGrammarListener ) ((AutomaticTaskScriptGrammarListener)listener).enterFuncaoEnviarEmail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskScriptGrammarListener ) ((AutomaticTaskScriptGrammarListener)listener).exitFuncaoEnviarEmail(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskScriptGrammarVisitor ) return ((AutomaticTaskScriptGrammarVisitor<? extends T>)visitor).visitFuncaoEnviarEmail(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncaoEnviarEmailContext funcaoEnviarEmail() throws RecognitionException {
		FuncaoEnviarEmailContext _localctx = new FuncaoEnviarEmailContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_funcaoEnviarEmail);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(T__1);
			setState(73);
			match(PARENTESE_ABRIR);
			setState(74);
			((FuncaoEnviarEmailContext)_localctx).assunto = match(ALFANUM);
			setState(75);
			match(T__2);
			setState(76);
			((FuncaoEnviarEmailContext)_localctx).corpo = match(ALFANUM);
			setState(77);
			match(PARENTESE_FECHAR);
			setState(78);
			match(PONTO_E_VIRGULA);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncaoMudarDistritoContext extends ParserRuleContext {
		public Token campoDoDistritoNoFormulario;
		public TerminalNode PARENTESE_ABRIR() { return getToken(AutomaticTaskScriptGrammarParser.PARENTESE_ABRIR, 0); }
		public TerminalNode PARENTESE_FECHAR() { return getToken(AutomaticTaskScriptGrammarParser.PARENTESE_FECHAR, 0); }
		public TerminalNode PONTO_E_VIRGULA() { return getToken(AutomaticTaskScriptGrammarParser.PONTO_E_VIRGULA, 0); }
		public TerminalNode FORM_ATRIBUTO() { return getToken(AutomaticTaskScriptGrammarParser.FORM_ATRIBUTO, 0); }
		public FuncaoMudarDistritoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcaoMudarDistrito; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskScriptGrammarListener ) ((AutomaticTaskScriptGrammarListener)listener).enterFuncaoMudarDistrito(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskScriptGrammarListener ) ((AutomaticTaskScriptGrammarListener)listener).exitFuncaoMudarDistrito(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskScriptGrammarVisitor ) return ((AutomaticTaskScriptGrammarVisitor<? extends T>)visitor).visitFuncaoMudarDistrito(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncaoMudarDistritoContext funcaoMudarDistrito() throws RecognitionException {
		FuncaoMudarDistritoContext _localctx = new FuncaoMudarDistritoContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_funcaoMudarDistrito);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(T__3);
			setState(81);
			match(PARENTESE_ABRIR);
			setState(82);
			((FuncaoMudarDistritoContext)_localctx).campoDoDistritoNoFormulario = match(FORM_ATRIBUTO);
			setState(83);
			match(PARENTESE_FECHAR);
			setState(84);
			match(PONTO_E_VIRGULA);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncaoMudarTelemovelContext extends ParserRuleContext {
		public Token campoDoTeleNoFormulario;
		public TerminalNode PARENTESE_ABRIR() { return getToken(AutomaticTaskScriptGrammarParser.PARENTESE_ABRIR, 0); }
		public TerminalNode PARENTESE_FECHAR() { return getToken(AutomaticTaskScriptGrammarParser.PARENTESE_FECHAR, 0); }
		public TerminalNode PONTO_E_VIRGULA() { return getToken(AutomaticTaskScriptGrammarParser.PONTO_E_VIRGULA, 0); }
		public TerminalNode FORM_ATRIBUTO() { return getToken(AutomaticTaskScriptGrammarParser.FORM_ATRIBUTO, 0); }
		public FuncaoMudarTelemovelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcaoMudarTelemovel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskScriptGrammarListener ) ((AutomaticTaskScriptGrammarListener)listener).enterFuncaoMudarTelemovel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskScriptGrammarListener ) ((AutomaticTaskScriptGrammarListener)listener).exitFuncaoMudarTelemovel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskScriptGrammarVisitor ) return ((AutomaticTaskScriptGrammarVisitor<? extends T>)visitor).visitFuncaoMudarTelemovel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncaoMudarTelemovelContext funcaoMudarTelemovel() throws RecognitionException {
		FuncaoMudarTelemovelContext _localctx = new FuncaoMudarTelemovelContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_funcaoMudarTelemovel);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			match(T__4);
			setState(87);
			match(PARENTESE_ABRIR);
			setState(88);
			((FuncaoMudarTelemovelContext)_localctx).campoDoTeleNoFormulario = match(FORM_ATRIBUTO);
			setState(89);
			match(PARENTESE_FECHAR);
			setState(90);
			match(PONTO_E_VIRGULA);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncaoMudarConcelhoContext extends ParserRuleContext {
		public Token campoDoConcelhoNoFormulario;
		public TerminalNode PARENTESE_ABRIR() { return getToken(AutomaticTaskScriptGrammarParser.PARENTESE_ABRIR, 0); }
		public TerminalNode PARENTESE_FECHAR() { return getToken(AutomaticTaskScriptGrammarParser.PARENTESE_FECHAR, 0); }
		public TerminalNode PONTO_E_VIRGULA() { return getToken(AutomaticTaskScriptGrammarParser.PONTO_E_VIRGULA, 0); }
		public TerminalNode FORM_ATRIBUTO() { return getToken(AutomaticTaskScriptGrammarParser.FORM_ATRIBUTO, 0); }
		public FuncaoMudarConcelhoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcaoMudarConcelho; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskScriptGrammarListener ) ((AutomaticTaskScriptGrammarListener)listener).enterFuncaoMudarConcelho(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskScriptGrammarListener ) ((AutomaticTaskScriptGrammarListener)listener).exitFuncaoMudarConcelho(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskScriptGrammarVisitor ) return ((AutomaticTaskScriptGrammarVisitor<? extends T>)visitor).visitFuncaoMudarConcelho(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncaoMudarConcelhoContext funcaoMudarConcelho() throws RecognitionException {
		FuncaoMudarConcelhoContext _localctx = new FuncaoMudarConcelhoContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_funcaoMudarConcelho);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(T__5);
			setState(93);
			match(PARENTESE_ABRIR);
			setState(94);
			((FuncaoMudarConcelhoContext)_localctx).campoDoConcelhoNoFormulario = match(FORM_ATRIBUTO);
			setState(95);
			match(PARENTESE_FECHAR);
			setState(96);
			match(PONTO_E_VIRGULA);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncaoBuscarInfoXMLContext extends ParserRuleContext {
		public Token nomeFicheiro;
		public TerminalNode PARENTESE_RETO_ABRIR() { return getToken(AutomaticTaskScriptGrammarParser.PARENTESE_RETO_ABRIR, 0); }
		public TerminalNode PARENTESE_RETO_FECHAR() { return getToken(AutomaticTaskScriptGrammarParser.PARENTESE_RETO_FECHAR, 0); }
		public TerminalNode FICHEIRO_XML() { return getToken(AutomaticTaskScriptGrammarParser.FICHEIRO_XML, 0); }
		public List<PassosContext> passos() {
			return getRuleContexts(PassosContext.class);
		}
		public PassosContext passos(int i) {
			return getRuleContext(PassosContext.class,i);
		}
		public FuncaoBuscarInfoXMLContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcaoBuscarInfoXML; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskScriptGrammarListener ) ((AutomaticTaskScriptGrammarListener)listener).enterFuncaoBuscarInfoXML(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskScriptGrammarListener ) ((AutomaticTaskScriptGrammarListener)listener).exitFuncaoBuscarInfoXML(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskScriptGrammarVisitor ) return ((AutomaticTaskScriptGrammarVisitor<? extends T>)visitor).visitFuncaoBuscarInfoXML(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncaoBuscarInfoXMLContext funcaoBuscarInfoXML() throws RecognitionException {
		FuncaoBuscarInfoXMLContext _localctx = new FuncaoBuscarInfoXMLContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_funcaoBuscarInfoXML);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			match(T__6);
			setState(99);
			((FuncaoBuscarInfoXMLContext)_localctx).nomeFicheiro = match(FICHEIRO_XML);
			setState(100);
			match(PARENTESE_RETO_ABRIR);
			setState(102); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(101);
				passos();
				}
				}
				setState(104); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__7) | (1L << T__9) | (1L << XML_ATRIBUTO))) != 0) );
			setState(106);
			match(PARENTESE_RETO_FECHAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PassosContext extends ParserRuleContext {
		public BuscarElementoContext buscarElemento() {
			return getRuleContext(BuscarElementoContext.class,0);
		}
		public AlterarElementoContext alterarElemento() {
			return getRuleContext(AlterarElementoContext.class,0);
		}
		public FuncaoEnviarEmailComValorContext funcaoEnviarEmailComValor() {
			return getRuleContext(FuncaoEnviarEmailComValorContext.class,0);
		}
		public Operacao_matematicaContext operacao_matematica() {
			return getRuleContext(Operacao_matematicaContext.class,0);
		}
		public PassosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_passos; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskScriptGrammarListener ) ((AutomaticTaskScriptGrammarListener)listener).enterPassos(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskScriptGrammarListener ) ((AutomaticTaskScriptGrammarListener)listener).exitPassos(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskScriptGrammarVisitor ) return ((AutomaticTaskScriptGrammarVisitor<? extends T>)visitor).visitPassos(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PassosContext passos() throws RecognitionException {
		PassosContext _localctx = new PassosContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_passos);
		try {
			setState(112);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__7:
				enterOuterAlt(_localctx, 1);
				{
				setState(108);
				buscarElemento();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 2);
				{
				setState(109);
				alterarElemento();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 3);
				{
				setState(110);
				funcaoEnviarEmailComValor();
				}
				break;
			case XML_ATRIBUTO:
				enterOuterAlt(_localctx, 4);
				{
				setState(111);
				operacao_matematica();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BuscarElementoContext extends ParserRuleContext {
		public Token varivelGuardar;
		public Token classe;
		public Token identificador;
		public Token classeAtributo;
		public Token procurar;
		public TerminalNode PARENTESE_ABRIR() { return getToken(AutomaticTaskScriptGrammarParser.PARENTESE_ABRIR, 0); }
		public TerminalNode PARENTESE_FECHAR() { return getToken(AutomaticTaskScriptGrammarParser.PARENTESE_FECHAR, 0); }
		public TerminalNode PONTO_E_VIRGULA() { return getToken(AutomaticTaskScriptGrammarParser.PONTO_E_VIRGULA, 0); }
		public TerminalNode XML_ATRIBUTO() { return getToken(AutomaticTaskScriptGrammarParser.XML_ATRIBUTO, 0); }
		public TerminalNode CLASSE() { return getToken(AutomaticTaskScriptGrammarParser.CLASSE, 0); }
		public TerminalNode XML_ATRIBUTO2() { return getToken(AutomaticTaskScriptGrammarParser.XML_ATRIBUTO2, 0); }
		public List<TerminalNode> NUM() { return getTokens(AutomaticTaskScriptGrammarParser.NUM); }
		public TerminalNode NUM(int i) {
			return getToken(AutomaticTaskScriptGrammarParser.NUM, i);
		}
		public List<TerminalNode> ALFANUM() { return getTokens(AutomaticTaskScriptGrammarParser.ALFANUM); }
		public TerminalNode ALFANUM(int i) {
			return getToken(AutomaticTaskScriptGrammarParser.ALFANUM, i);
		}
		public List<TerminalNode> FORM_ATRIBUTO() { return getTokens(AutomaticTaskScriptGrammarParser.FORM_ATRIBUTO); }
		public TerminalNode FORM_ATRIBUTO(int i) {
			return getToken(AutomaticTaskScriptGrammarParser.FORM_ATRIBUTO, i);
		}
		public BuscarElementoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_buscarElemento; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskScriptGrammarListener ) ((AutomaticTaskScriptGrammarListener)listener).enterBuscarElemento(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskScriptGrammarListener ) ((AutomaticTaskScriptGrammarListener)listener).exitBuscarElemento(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskScriptGrammarVisitor ) return ((AutomaticTaskScriptGrammarVisitor<? extends T>)visitor).visitBuscarElemento(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BuscarElementoContext buscarElemento() throws RecognitionException {
		BuscarElementoContext _localctx = new BuscarElementoContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_buscarElemento);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			match(T__7);
			setState(115);
			((BuscarElementoContext)_localctx).varivelGuardar = match(XML_ATRIBUTO);
			setState(116);
			match(T__0);
			setState(117);
			match(T__8);
			setState(118);
			((BuscarElementoContext)_localctx).classe = match(CLASSE);
			setState(119);
			match(PARENTESE_ABRIR);
			setState(120);
			((BuscarElementoContext)_localctx).identificador = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUM) | (1L << ALFANUM) | (1L << FORM_ATRIBUTO))) != 0)) ) {
				((BuscarElementoContext)_localctx).identificador = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(121);
			match(T__2);
			setState(122);
			((BuscarElementoContext)_localctx).classeAtributo = match(XML_ATRIBUTO2);
			setState(125);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(123);
				match(T__2);
				setState(124);
				((BuscarElementoContext)_localctx).procurar = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUM) | (1L << ALFANUM) | (1L << FORM_ATRIBUTO))) != 0)) ) {
					((BuscarElementoContext)_localctx).procurar = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(127);
			match(PARENTESE_FECHAR);
			setState(128);
			match(PONTO_E_VIRGULA);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AlterarElementoContext extends ParserRuleContext {
		public Token classe;
		public Token identificador;
		public Token classeAtributo;
		public Token novoValor;
		public TerminalNode PARENTESE_ABRIR() { return getToken(AutomaticTaskScriptGrammarParser.PARENTESE_ABRIR, 0); }
		public TerminalNode PARENTESE_FECHAR() { return getToken(AutomaticTaskScriptGrammarParser.PARENTESE_FECHAR, 0); }
		public TerminalNode PONTO_E_VIRGULA() { return getToken(AutomaticTaskScriptGrammarParser.PONTO_E_VIRGULA, 0); }
		public TerminalNode CLASSE() { return getToken(AutomaticTaskScriptGrammarParser.CLASSE, 0); }
		public TerminalNode XML_ATRIBUTO2() { return getToken(AutomaticTaskScriptGrammarParser.XML_ATRIBUTO2, 0); }
		public List<TerminalNode> NUM() { return getTokens(AutomaticTaskScriptGrammarParser.NUM); }
		public TerminalNode NUM(int i) {
			return getToken(AutomaticTaskScriptGrammarParser.NUM, i);
		}
		public List<TerminalNode> ALFANUM() { return getTokens(AutomaticTaskScriptGrammarParser.ALFANUM); }
		public TerminalNode ALFANUM(int i) {
			return getToken(AutomaticTaskScriptGrammarParser.ALFANUM, i);
		}
		public List<TerminalNode> FORM_ATRIBUTO() { return getTokens(AutomaticTaskScriptGrammarParser.FORM_ATRIBUTO); }
		public TerminalNode FORM_ATRIBUTO(int i) {
			return getToken(AutomaticTaskScriptGrammarParser.FORM_ATRIBUTO, i);
		}
		public AlterarElementoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alterarElemento; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskScriptGrammarListener ) ((AutomaticTaskScriptGrammarListener)listener).enterAlterarElemento(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskScriptGrammarListener ) ((AutomaticTaskScriptGrammarListener)listener).exitAlterarElemento(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskScriptGrammarVisitor ) return ((AutomaticTaskScriptGrammarVisitor<? extends T>)visitor).visitAlterarElemento(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AlterarElementoContext alterarElemento() throws RecognitionException {
		AlterarElementoContext _localctx = new AlterarElementoContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_alterarElemento);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			match(T__9);
			setState(131);
			((AlterarElementoContext)_localctx).classe = match(CLASSE);
			setState(132);
			match(PARENTESE_ABRIR);
			setState(133);
			((AlterarElementoContext)_localctx).identificador = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUM) | (1L << ALFANUM) | (1L << FORM_ATRIBUTO))) != 0)) ) {
				((AlterarElementoContext)_localctx).identificador = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(134);
			match(T__2);
			setState(135);
			((AlterarElementoContext)_localctx).classeAtributo = match(XML_ATRIBUTO2);
			setState(136);
			match(T__2);
			setState(137);
			((AlterarElementoContext)_localctx).novoValor = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NUM) | (1L << ALFANUM) | (1L << FORM_ATRIBUTO))) != 0)) ) {
				((AlterarElementoContext)_localctx).novoValor = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(138);
			match(PARENTESE_FECHAR);
			setState(139);
			match(PONTO_E_VIRGULA);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncaoEnviarEmailComValorContext extends ParserRuleContext {
		public Token assunto;
		public Token corpo;
		public TerminalNode PARENTESE_ABRIR() { return getToken(AutomaticTaskScriptGrammarParser.PARENTESE_ABRIR, 0); }
		public TerminalNode PARENTESE_FECHAR() { return getToken(AutomaticTaskScriptGrammarParser.PARENTESE_FECHAR, 0); }
		public TerminalNode PONTO_E_VIRGULA() { return getToken(AutomaticTaskScriptGrammarParser.PONTO_E_VIRGULA, 0); }
		public List<TerminalNode> ALFANUM() { return getTokens(AutomaticTaskScriptGrammarParser.ALFANUM); }
		public TerminalNode ALFANUM(int i) {
			return getToken(AutomaticTaskScriptGrammarParser.ALFANUM, i);
		}
		public List<TerminalNode> ALFANUM2() { return getTokens(AutomaticTaskScriptGrammarParser.ALFANUM2); }
		public TerminalNode ALFANUM2(int i) {
			return getToken(AutomaticTaskScriptGrammarParser.ALFANUM2, i);
		}
		public FuncaoEnviarEmailComValorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcaoEnviarEmailComValor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskScriptGrammarListener ) ((AutomaticTaskScriptGrammarListener)listener).enterFuncaoEnviarEmailComValor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskScriptGrammarListener ) ((AutomaticTaskScriptGrammarListener)listener).exitFuncaoEnviarEmailComValor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskScriptGrammarVisitor ) return ((AutomaticTaskScriptGrammarVisitor<? extends T>)visitor).visitFuncaoEnviarEmailComValor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncaoEnviarEmailComValorContext funcaoEnviarEmailComValor() throws RecognitionException {
		FuncaoEnviarEmailComValorContext _localctx = new FuncaoEnviarEmailComValorContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_funcaoEnviarEmailComValor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			match(T__1);
			setState(142);
			match(PARENTESE_ABRIR);
			setState(143);
			((FuncaoEnviarEmailComValorContext)_localctx).assunto = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==ALFANUM || _la==ALFANUM2) ) {
				((FuncaoEnviarEmailComValorContext)_localctx).assunto = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(144);
			match(T__2);
			setState(145);
			((FuncaoEnviarEmailComValorContext)_localctx).corpo = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==ALFANUM || _la==ALFANUM2) ) {
				((FuncaoEnviarEmailComValorContext)_localctx).corpo = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(146);
			match(PARENTESE_FECHAR);
			setState(147);
			match(PONTO_E_VIRGULA);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InteiroContext extends ParserRuleContext {
		public InteiroContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_inteiro; }
	 
		public InteiroContext() { }
		public void copyFrom(InteiroContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class OpExprMulDivContext extends InteiroContext {
		public Token left;
		public Token op;
		public Token right;
		public List<TerminalNode> XML_ATRIBUTO() { return getTokens(AutomaticTaskScriptGrammarParser.XML_ATRIBUTO); }
		public TerminalNode XML_ATRIBUTO(int i) {
			return getToken(AutomaticTaskScriptGrammarParser.XML_ATRIBUTO, i);
		}
		public TerminalNode MULTIPLICACAO() { return getToken(AutomaticTaskScriptGrammarParser.MULTIPLICACAO, 0); }
		public TerminalNode DIVISAO() { return getToken(AutomaticTaskScriptGrammarParser.DIVISAO, 0); }
		public OpExprMulDivContext(InteiroContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskScriptGrammarListener ) ((AutomaticTaskScriptGrammarListener)listener).enterOpExprMulDiv(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskScriptGrammarListener ) ((AutomaticTaskScriptGrammarListener)listener).exitOpExprMulDiv(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskScriptGrammarVisitor ) return ((AutomaticTaskScriptGrammarVisitor<? extends T>)visitor).visitOpExprMulDiv(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class OpExprSumDifContext extends InteiroContext {
		public Token left;
		public Token op;
		public Token right;
		public List<TerminalNode> XML_ATRIBUTO() { return getTokens(AutomaticTaskScriptGrammarParser.XML_ATRIBUTO); }
		public TerminalNode XML_ATRIBUTO(int i) {
			return getToken(AutomaticTaskScriptGrammarParser.XML_ATRIBUTO, i);
		}
		public TerminalNode SOMA() { return getToken(AutomaticTaskScriptGrammarParser.SOMA, 0); }
		public TerminalNode SUBTRACAO() { return getToken(AutomaticTaskScriptGrammarParser.SUBTRACAO, 0); }
		public OpExprSumDifContext(InteiroContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskScriptGrammarListener ) ((AutomaticTaskScriptGrammarListener)listener).enterOpExprSumDif(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof AutomaticTaskScriptGrammarListener ) ((AutomaticTaskScriptGrammarListener)listener).exitOpExprSumDif(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof AutomaticTaskScriptGrammarVisitor ) return ((AutomaticTaskScriptGrammarVisitor<? extends T>)visitor).visitOpExprSumDif(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InteiroContext inteiro() throws RecognitionException {
		InteiroContext _localctx = new InteiroContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_inteiro);
		int _la;
		try {
			setState(155);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				_localctx = new OpExprMulDivContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(149);
				((OpExprMulDivContext)_localctx).left = match(XML_ATRIBUTO);
				setState(150);
				((OpExprMulDivContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==MULTIPLICACAO || _la==DIVISAO) ) {
					((OpExprMulDivContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(151);
				((OpExprMulDivContext)_localctx).right = match(XML_ATRIBUTO);
				}
				break;
			case 2:
				_localctx = new OpExprSumDifContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(152);
				((OpExprSumDifContext)_localctx).left = match(XML_ATRIBUTO);
				setState(153);
				((OpExprSumDifContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==SOMA || _la==SUBTRACAO) ) {
					((OpExprSumDifContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(154);
				((OpExprSumDifContext)_localctx).right = match(XML_ATRIBUTO);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 3:
			return condicao_sempred((CondicaoContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean condicao_sempred(CondicaoContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3#\u00a0\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2\3"+
		"\2\3\2\3\2\3\3\6\3)\n\3\r\3\16\3*\3\4\3\4\5\4/\n\4\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\7\5:\n\5\f\5\16\5=\13\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3"+
		"\7\3\7\3\7\5\7I\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3"+
		"\f\3\f\6\fi\n\f\r\f\16\fj\3\f\3\f\3\r\3\r\3\r\3\r\5\rs\n\r\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u0080\n\16\3\16\3\16"+
		"\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u009e"+
		"\n\21\3\21\2\3\b\22\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \2\6\4\2\25"+
		"\25 !\4\2  \"\"\3\2\26\27\3\2\30\31\2\u009d\2\"\3\2\2\2\4(\3\2\2\2\6."+
		"\3\2\2\2\b\60\3\2\2\2\n>\3\2\2\2\fH\3\2\2\2\16J\3\2\2\2\20R\3\2\2\2\22"+
		"X\3\2\2\2\24^\3\2\2\2\26d\3\2\2\2\30r\3\2\2\2\32t\3\2\2\2\34\u0084\3\2"+
		"\2\2\36\u008f\3\2\2\2 \u009d\3\2\2\2\"#\7\r\2\2#$\7\20\2\2$%\5\4\3\2%"+
		"&\7\21\2\2&\3\3\2\2\2\')\5\6\4\2(\'\3\2\2\2)*\3\2\2\2*(\3\2\2\2*+\3\2"+
		"\2\2+\5\3\2\2\2,/\5\b\5\2-/\5\f\7\2.,\3\2\2\2.-\3\2\2\2/\7\3\2\2\2\60"+
		"\61\b\5\1\2\61\62\5\n\6\2\62;\3\2\2\2\63\64\f\5\2\2\64\65\7\32\2\2\65"+
		":\5\b\5\6\66\67\f\4\2\2\678\7\33\2\28:\5\b\5\59\63\3\2\2\29\66\3\2\2\2"+
		":=\3\2\2\2;9\3\2\2\2;<\3\2\2\2<\t\3\2\2\2=;\3\2\2\2>?\7\35\2\2?@\7\3\2"+
		"\2@A\5 \21\2AB\7\24\2\2B\13\3\2\2\2CI\5\16\b\2DI\5\20\t\2EI\5\22\n\2F"+
		"I\5\24\13\2GI\5\26\f\2HC\3\2\2\2HD\3\2\2\2HE\3\2\2\2HF\3\2\2\2HG\3\2\2"+
		"\2I\r\3\2\2\2JK\7\4\2\2KL\7\22\2\2LM\7 \2\2MN\7\5\2\2NO\7 \2\2OP\7\23"+
		"\2\2PQ\7\24\2\2Q\17\3\2\2\2RS\7\6\2\2ST\7\22\2\2TU\7!\2\2UV\7\23\2\2V"+
		"W\7\24\2\2W\21\3\2\2\2XY\7\7\2\2YZ\7\22\2\2Z[\7!\2\2[\\\7\23\2\2\\]\7"+
		"\24\2\2]\23\3\2\2\2^_\7\b\2\2_`\7\22\2\2`a\7!\2\2ab\7\23\2\2bc\7\24\2"+
		"\2c\25\3\2\2\2de\7\t\2\2ef\7\37\2\2fh\7\16\2\2gi\5\30\r\2hg\3\2\2\2ij"+
		"\3\2\2\2jh\3\2\2\2jk\3\2\2\2kl\3\2\2\2lm\7\17\2\2m\27\3\2\2\2ns\5\32\16"+
		"\2os\5\34\17\2ps\5\36\20\2qs\5\n\6\2rn\3\2\2\2ro\3\2\2\2rp\3\2\2\2rq\3"+
		"\2\2\2s\31\3\2\2\2tu\7\n\2\2uv\7\35\2\2vw\7\3\2\2wx\7\13\2\2xy\7\34\2"+
		"\2yz\7\22\2\2z{\t\2\2\2{|\7\5\2\2|\177\7\36\2\2}~\7\5\2\2~\u0080\t\2\2"+
		"\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0082\7\23"+
		"\2\2\u0082\u0083\7\24\2\2\u0083\33\3\2\2\2\u0084\u0085\7\f\2\2\u0085\u0086"+
		"\7\34\2\2\u0086\u0087\7\22\2\2\u0087\u0088\t\2\2\2\u0088\u0089\7\5\2\2"+
		"\u0089\u008a\7\36\2\2\u008a\u008b\7\5\2\2\u008b\u008c\t\2\2\2\u008c\u008d"+
		"\7\23\2\2\u008d\u008e\7\24\2\2\u008e\35\3\2\2\2\u008f\u0090\7\4\2\2\u0090"+
		"\u0091\7\22\2\2\u0091\u0092\t\3\2\2\u0092\u0093\7\5\2\2\u0093\u0094\t"+
		"\3\2\2\u0094\u0095\7\23\2\2\u0095\u0096\7\24\2\2\u0096\37\3\2\2\2\u0097"+
		"\u0098\7\35\2\2\u0098\u0099\t\4\2\2\u0099\u009e\7\35\2\2\u009a\u009b\7"+
		"\35\2\2\u009b\u009c\t\5\2\2\u009c\u009e\7\35\2\2\u009d\u0097\3\2\2\2\u009d"+
		"\u009a\3\2\2\2\u009e!\3\2\2\2\13*.9;Hjr\177\u009d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}