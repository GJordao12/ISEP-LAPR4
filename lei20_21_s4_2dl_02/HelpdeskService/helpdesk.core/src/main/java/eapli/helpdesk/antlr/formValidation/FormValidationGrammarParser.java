// Generated from C:/Users/Admin/Desktop/Repositorio/HelpdeskService/helpdesk.core/src/main/java/eapli/helpdesk/antlr/formValidation\FormValidationGrammar.g4 by ANTLR 4.9.1
package eapli.helpdesk.antlr.formValidation;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FormValidationGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, PONTO_E_VIRGULA=9, 
		ValorBooleano=10, Tipo=11, EstadoString=12, Obrigatoriedade=13, OperadorLogico=14, 
		Igual_Diferente=15, OperadorCondicional=16, Operador=17, String=18, Inteiro=19, 
		WS=20;
	public static final int
		RULE_start = 0, RULE_ifCondition = 1, RULE_statement = 2, RULE_dados = 3, 
		RULE_specs = 4, RULE_intervalo = 5, RULE_condicoesDados = 6, RULE_condicoes = 7, 
		RULE_verify = 8, RULE_condicao = 9, RULE_condicaoString = 10, RULE_stringEntreAspas = 11, 
		RULE_valString = 12, RULE_valInt = 13;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "ifCondition", "statement", "dados", "specs", "intervalo", "condicoesDados", 
			"condicoes", "verify", "condicao", "condicaoString", "stringEntreAspas", 
			"valString", "valInt"
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

	@Override
	public String getGrammarFileName() { return "FormValidationGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public FormValidationGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class StartContext extends ParserRuleContext {
		public StartContext start() {
			return getRuleContext(StartContext.class,0);
		}
		public IfConditionContext ifCondition() {
			return getRuleContext(IfConditionContext.class,0);
		}
		public SpecsContext specs() {
			return getRuleContext(SpecsContext.class,0);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationGrammarVisitor ) return ((FormValidationGrammarVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		return start(0);
	}

	private StartContext start(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		StartContext _localctx = new StartContext(_ctx, _parentState);
		StartContext _prevctx = _localctx;
		int _startState = 0;
		enterRecursionRule(_localctx, 0, RULE_start, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			}
			_ctx.stop = _input.LT(-1);
			setState(35);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(33);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
					case 1:
						{
						_localctx = new StartContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_start);
						setState(29);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(30);
						ifCondition();
						}
						break;
					case 2:
						{
						_localctx = new StartContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_start);
						setState(31);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(32);
						specs();
						}
						break;
					}
					} 
				}
				setState(37);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
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

	public static class IfConditionContext extends ParserRuleContext {
		public CondicoesContext condicoes() {
			return getRuleContext(CondicoesContext.class,0);
		}
		public DadosContext dados() {
			return getRuleContext(DadosContext.class,0);
		}
		public TerminalNode PONTO_E_VIRGULA() { return getToken(FormValidationGrammarParser.PONTO_E_VIRGULA, 0); }
		public IfConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifCondition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).enterIfCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).exitIfCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationGrammarVisitor ) return ((FormValidationGrammarVisitor<? extends T>)visitor).visitIfCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfConditionContext ifCondition() throws RecognitionException {
		IfConditionContext _localctx = new IfConditionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_ifCondition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			match(T__0);
			setState(39);
			condicoes(0);
			setState(40);
			match(T__1);
			setState(41);
			dados(0);
			setState(42);
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

	public static class StatementContext extends ParserRuleContext {
		public IfConditionContext ifCondition() {
			return getRuleContext(IfConditionContext.class,0);
		}
		public VerifyContext verify() {
			return getRuleContext(VerifyContext.class,0);
		}
		public SpecsContext specs() {
			return getRuleContext(SpecsContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationGrammarVisitor ) return ((FormValidationGrammarVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_statement);
		try {
			setState(47);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(44);
				ifCondition();
				}
				break;
			case OperadorLogico:
			case Igual_Diferente:
				enterOuterAlt(_localctx, 2);
				{
				setState(45);
				verify();
				}
				break;
			case Inteiro:
				enterOuterAlt(_localctx, 3);
				{
				setState(46);
				specs();
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

	public static class DadosContext extends ParserRuleContext {
		public IfConditionContext ifCondition() {
			return getRuleContext(IfConditionContext.class,0);
		}
		public SpecsContext specs() {
			return getRuleContext(SpecsContext.class,0);
		}
		public CondicoesDadosContext condicoesDados() {
			return getRuleContext(CondicoesDadosContext.class,0);
		}
		public DadosContext dados() {
			return getRuleContext(DadosContext.class,0);
		}
		public DadosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dados; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).enterDados(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).exitDados(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationGrammarVisitor ) return ((FormValidationGrammarVisitor<? extends T>)visitor).visitDados(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DadosContext dados() throws RecognitionException {
		return dados(0);
	}

	private DadosContext dados(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		DadosContext _localctx = new DadosContext(_ctx, _parentState);
		DadosContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_dados, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(50);
				ifCondition();
				}
				break;
			case 2:
				{
				setState(51);
				specs();
				}
				break;
			case 3:
				{
				setState(52);
				condicoesDados();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(63);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(61);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
					case 1:
						{
						_localctx = new DadosContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_dados);
						setState(55);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(56);
						condicoesDados();
						}
						break;
					case 2:
						{
						_localctx = new DadosContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_dados);
						setState(57);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(58);
						specs();
						}
						break;
					case 3:
						{
						_localctx = new DadosContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_dados);
						setState(59);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(60);
						ifCondition();
						}
						break;
					}
					} 
				}
				setState(65);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
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

	public static class SpecsContext extends ParserRuleContext {
		public SpecsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_specs; }
	 
		public SpecsContext() { }
		public void copyFrom(SpecsContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ValorObrigatorioContext extends SpecsContext {
		public TerminalNode Inteiro() { return getToken(FormValidationGrammarParser.Inteiro, 0); }
		public TerminalNode Obrigatoriedade() { return getToken(FormValidationGrammarParser.Obrigatoriedade, 0); }
		public ValorObrigatorioContext(SpecsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).enterValorObrigatorio(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).exitValorObrigatorio(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationGrammarVisitor ) return ((FormValidationGrammarVisitor<? extends T>)visitor).visitValorObrigatorio(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TamanhoValorContext extends SpecsContext {
		public List<TerminalNode> Inteiro() { return getTokens(FormValidationGrammarParser.Inteiro); }
		public TerminalNode Inteiro(int i) {
			return getToken(FormValidationGrammarParser.Inteiro, i);
		}
		public TerminalNode PONTO_E_VIRGULA() { return getToken(FormValidationGrammarParser.PONTO_E_VIRGULA, 0); }
		public TamanhoValorContext(SpecsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).enterTamanhoValor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).exitTamanhoValor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationGrammarVisitor ) return ((FormValidationGrammarVisitor<? extends T>)visitor).visitTamanhoValor(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ValorEntreContext extends SpecsContext {
		public TerminalNode Inteiro() { return getToken(FormValidationGrammarParser.Inteiro, 0); }
		public IntervaloContext intervalo() {
			return getRuleContext(IntervaloContext.class,0);
		}
		public ValorEntreContext(SpecsContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).enterValorEntre(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).exitValorEntre(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationGrammarVisitor ) return ((FormValidationGrammarVisitor<? extends T>)visitor).visitValorEntre(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpecsContext specs() throws RecognitionException {
		SpecsContext _localctx = new SpecsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_specs);
		try {
			setState(76);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				_localctx = new TamanhoValorContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(66);
				match(Inteiro);
				setState(67);
				match(T__2);
				setState(68);
				match(Inteiro);
				setState(69);
				match(PONTO_E_VIRGULA);
				}
				break;
			case 2:
				_localctx = new ValorEntreContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(70);
				match(Inteiro);
				setState(71);
				match(T__3);
				setState(72);
				intervalo();
				}
				break;
			case 3:
				_localctx = new ValorObrigatorioContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(73);
				match(Inteiro);
				setState(74);
				match(T__4);
				setState(75);
				match(Obrigatoriedade);
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

	public static class IntervaloContext extends ParserRuleContext {
		public List<TerminalNode> Inteiro() { return getTokens(FormValidationGrammarParser.Inteiro); }
		public TerminalNode Inteiro(int i) {
			return getToken(FormValidationGrammarParser.Inteiro, i);
		}
		public IntervaloContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_intervalo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).enterIntervalo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).exitIntervalo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationGrammarVisitor ) return ((FormValidationGrammarVisitor<? extends T>)visitor).visitIntervalo(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntervaloContext intervalo() throws RecognitionException {
		IntervaloContext _localctx = new IntervaloContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_intervalo);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			match(Inteiro);
			setState(79);
			match(T__5);
			setState(80);
			match(Inteiro);
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

	public static class CondicoesDadosContext extends ParserRuleContext {
		public CondicoesContext condicoes() {
			return getRuleContext(CondicoesContext.class,0);
		}
		public TerminalNode PONTO_E_VIRGULA() { return getToken(FormValidationGrammarParser.PONTO_E_VIRGULA, 0); }
		public CondicoesDadosContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condicoesDados; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).enterCondicoesDados(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).exitCondicoesDados(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationGrammarVisitor ) return ((FormValidationGrammarVisitor<? extends T>)visitor).visitCondicoesDados(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CondicoesDadosContext condicoesDados() throws RecognitionException {
		CondicoesDadosContext _localctx = new CondicoesDadosContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_condicoesDados);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			condicoes(0);
			setState(83);
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

	public static class CondicoesContext extends ParserRuleContext {
		public CondicaoContext condicao() {
			return getRuleContext(CondicaoContext.class,0);
		}
		public CondicoesContext condicoes() {
			return getRuleContext(CondicoesContext.class,0);
		}
		public TerminalNode OperadorCondicional() { return getToken(FormValidationGrammarParser.OperadorCondicional, 0); }
		public TerminalNode PONTO_E_VIRGULA() { return getToken(FormValidationGrammarParser.PONTO_E_VIRGULA, 0); }
		public CondicoesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condicoes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).enterCondicoes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).exitCondicoes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationGrammarVisitor ) return ((FormValidationGrammarVisitor<? extends T>)visitor).visitCondicoes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CondicoesContext condicoes() throws RecognitionException {
		return condicoes(0);
	}

	private CondicoesContext condicoes(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		CondicoesContext _localctx = new CondicoesContext(_ctx, _parentState);
		CondicoesContext _prevctx = _localctx;
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_condicoes, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(86);
			condicao();
			}
			_ctx.stop = _input.LT(-1);
			setState(95);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new CondicoesContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_condicoes);
					setState(88);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(89);
					match(OperadorCondicional);
					setState(90);
					condicao();
					setState(91);
					match(PONTO_E_VIRGULA);
					}
					} 
				}
				setState(97);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
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

	public static class VerifyContext extends ParserRuleContext {
		public TerminalNode OperadorLogico() { return getToken(FormValidationGrammarParser.OperadorLogico, 0); }
		public TerminalNode Igual_Diferente() { return getToken(FormValidationGrammarParser.Igual_Diferente, 0); }
		public VerifyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_verify; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).enterVerify(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).exitVerify(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationGrammarVisitor ) return ((FormValidationGrammarVisitor<? extends T>)visitor).visitVerify(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VerifyContext verify() throws RecognitionException {
		VerifyContext _localctx = new VerifyContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_verify);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			_la = _input.LA(1);
			if ( !(_la==OperadorLogico || _la==Igual_Diferente) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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
		public CondicaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condicao; }
	 
		public CondicaoContext() { }
		public void copyFrom(CondicaoContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class EstadoValorContext extends CondicaoContext {
		public TerminalNode Inteiro() { return getToken(FormValidationGrammarParser.Inteiro, 0); }
		public TerminalNode EstadoString() { return getToken(FormValidationGrammarParser.EstadoString, 0); }
		public EstadoValorContext(CondicaoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).enterEstadoValor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).exitEstadoValor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationGrammarVisitor ) return ((FormValidationGrammarVisitor<? extends T>)visitor).visitEstadoValor(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ValorStringContext extends CondicaoContext {
		public TerminalNode Inteiro() { return getToken(FormValidationGrammarParser.Inteiro, 0); }
		public TerminalNode Igual_Diferente() { return getToken(FormValidationGrammarParser.Igual_Diferente, 0); }
		public CondicaoStringContext condicaoString() {
			return getRuleContext(CondicaoStringContext.class,0);
		}
		public ValorStringContext(CondicaoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).enterValorString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).exitValorString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationGrammarVisitor ) return ((FormValidationGrammarVisitor<? extends T>)visitor).visitValorString(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ValorBooleanoContext extends CondicaoContext {
		public TerminalNode Inteiro() { return getToken(FormValidationGrammarParser.Inteiro, 0); }
		public TerminalNode Igual_Diferente() { return getToken(FormValidationGrammarParser.Igual_Diferente, 0); }
		public TerminalNode ValorBooleano() { return getToken(FormValidationGrammarParser.ValorBooleano, 0); }
		public ValorBooleanoContext(CondicaoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).enterValorBooleano(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).exitValorBooleano(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationGrammarVisitor ) return ((FormValidationGrammarVisitor<? extends T>)visitor).visitValorBooleano(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ValorBooleano1Context extends CondicaoContext {
		public TerminalNode ValorBooleano() { return getToken(FormValidationGrammarParser.ValorBooleano, 0); }
		public TerminalNode Igual_Diferente() { return getToken(FormValidationGrammarParser.Igual_Diferente, 0); }
		public TerminalNode Inteiro() { return getToken(FormValidationGrammarParser.Inteiro, 0); }
		public ValorBooleano1Context(CondicaoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).enterValorBooleano1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).exitValorBooleano1(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationGrammarVisitor ) return ((FormValidationGrammarVisitor<? extends T>)visitor).visitValorBooleano1(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ValorString1Context extends CondicaoContext {
		public CondicaoStringContext condicaoString() {
			return getRuleContext(CondicaoStringContext.class,0);
		}
		public TerminalNode Igual_Diferente() { return getToken(FormValidationGrammarParser.Igual_Diferente, 0); }
		public TerminalNode Inteiro() { return getToken(FormValidationGrammarParser.Inteiro, 0); }
		public ValorString1Context(CondicaoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).enterValorString1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).exitValorString1(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationGrammarVisitor ) return ((FormValidationGrammarVisitor<? extends T>)visitor).visitValorString1(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ValoresIguaisContext extends CondicaoContext {
		public List<TerminalNode> Inteiro() { return getTokens(FormValidationGrammarParser.Inteiro); }
		public TerminalNode Inteiro(int i) {
			return getToken(FormValidationGrammarParser.Inteiro, i);
		}
		public TerminalNode OperadorLogico() { return getToken(FormValidationGrammarParser.OperadorLogico, 0); }
		public TerminalNode Igual_Diferente() { return getToken(FormValidationGrammarParser.Igual_Diferente, 0); }
		public ValoresIguaisContext(CondicaoContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).enterValoresIguais(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).exitValoresIguais(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationGrammarVisitor ) return ((FormValidationGrammarVisitor<? extends T>)visitor).visitValoresIguais(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CondicaoContext condicao() throws RecognitionException {
		CondicaoContext _localctx = new CondicaoContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_condicao);
		int _la;
		try {
			setState(119);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				_localctx = new ValoresIguaisContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(100);
				match(Inteiro);
				setState(101);
				_la = _input.LA(1);
				if ( !(_la==OperadorLogico || _la==Igual_Diferente) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(102);
				match(Inteiro);
				}
				break;
			case 2:
				_localctx = new EstadoValorContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(103);
				match(Inteiro);
				setState(104);
				match(T__6);
				setState(105);
				match(EstadoString);
				}
				break;
			case 3:
				_localctx = new ValorStringContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(106);
				match(Inteiro);
				setState(107);
				match(Igual_Diferente);
				setState(108);
				condicaoString();
				}
				break;
			case 4:
				_localctx = new ValorString1Context(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(109);
				condicaoString();
				setState(110);
				match(Igual_Diferente);
				setState(111);
				match(Inteiro);
				}
				break;
			case 5:
				_localctx = new ValorBooleanoContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(113);
				match(Inteiro);
				setState(114);
				match(Igual_Diferente);
				setState(115);
				match(ValorBooleano);
				}
				break;
			case 6:
				_localctx = new ValorBooleano1Context(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(116);
				match(ValorBooleano);
				setState(117);
				match(Igual_Diferente);
				setState(118);
				match(Inteiro);
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

	public static class CondicaoStringContext extends ParserRuleContext {
		public StringEntreAspasContext stringEntreAspas() {
			return getRuleContext(StringEntreAspasContext.class,0);
		}
		public ValStringContext valString() {
			return getRuleContext(ValStringContext.class,0);
		}
		public CondicaoStringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condicaoString; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).enterCondicaoString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).exitCondicaoString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationGrammarVisitor ) return ((FormValidationGrammarVisitor<? extends T>)visitor).visitCondicaoString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CondicaoStringContext condicaoString() throws RecognitionException {
		CondicaoStringContext _localctx = new CondicaoStringContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_condicaoString);
		try {
			setState(123);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__7:
				enterOuterAlt(_localctx, 1);
				{
				setState(121);
				stringEntreAspas();
				}
				break;
			case String:
				enterOuterAlt(_localctx, 2);
				{
				setState(122);
				valString();
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

	public static class StringEntreAspasContext extends ParserRuleContext {
		public ValStringContext valString() {
			return getRuleContext(ValStringContext.class,0);
		}
		public StringEntreAspasContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringEntreAspas; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).enterStringEntreAspas(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).exitStringEntreAspas(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationGrammarVisitor ) return ((FormValidationGrammarVisitor<? extends T>)visitor).visitStringEntreAspas(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringEntreAspasContext stringEntreAspas() throws RecognitionException {
		StringEntreAspasContext _localctx = new StringEntreAspasContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_stringEntreAspas);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			match(T__7);
			setState(126);
			valString();
			setState(127);
			match(T__7);
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

	public static class ValStringContext extends ParserRuleContext {
		public TerminalNode String() { return getToken(FormValidationGrammarParser.String, 0); }
		public ValStringContext valString() {
			return getRuleContext(ValStringContext.class,0);
		}
		public ValStringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valString; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).enterValString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).exitValString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationGrammarVisitor ) return ((FormValidationGrammarVisitor<? extends T>)visitor).visitValString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValStringContext valString() throws RecognitionException {
		ValStringContext _localctx = new ValStringContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_valString);
		try {
			setState(132);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(129);
				match(String);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(130);
				match(String);
				setState(131);
				valString();
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

	public static class ValIntContext extends ParserRuleContext {
		public TerminalNode Inteiro() { return getToken(FormValidationGrammarParser.Inteiro, 0); }
		public ValIntContext valInt() {
			return getRuleContext(ValIntContext.class,0);
		}
		public TerminalNode Operador() { return getToken(FormValidationGrammarParser.Operador, 0); }
		public ValIntContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valInt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).enterValInt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormValidationGrammarListener ) ((FormValidationGrammarListener)listener).exitValInt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormValidationGrammarVisitor ) return ((FormValidationGrammarVisitor<? extends T>)visitor).visitValInt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValIntContext valInt() throws RecognitionException {
		return valInt(0);
	}

	private ValIntContext valInt(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ValIntContext _localctx = new ValIntContext(_ctx, _parentState);
		ValIntContext _prevctx = _localctx;
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_valInt, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(135);
			match(Inteiro);
			}
			_ctx.stop = _input.LT(-1);
			setState(142);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new ValIntContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_valInt);
					setState(137);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(138);
					match(Operador);
					setState(139);
					match(Inteiro);
					}
					} 
				}
				setState(144);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 0:
			return start_sempred((StartContext)_localctx, predIndex);
		case 3:
			return dados_sempred((DadosContext)_localctx, predIndex);
		case 7:
			return condicoes_sempred((CondicoesContext)_localctx, predIndex);
		case 13:
			return valInt_sempred((ValIntContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean start_sempred(StartContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean dados_sempred(DadosContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 6);
		case 3:
			return precpred(_ctx, 5);
		case 4:
			return precpred(_ctx, 4);
		}
		return true;
	}
	private boolean condicoes_sempred(CondicoesContext _localctx, int predIndex) {
		switch (predIndex) {
		case 5:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean valInt_sempred(ValIntContext _localctx, int predIndex) {
		switch (predIndex) {
		case 6:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\26\u0094\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\2\3\2\7\2$\n\2"+
		"\f\2\16\2\'\13\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\5\4\62\n\4\3\5\3"+
		"\5\3\5\3\5\5\58\n\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5@\n\5\f\5\16\5C\13\5\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6O\n\6\3\7\3\7\3\7\3\7\3\b\3"+
		"\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t`\n\t\f\t\16\tc\13\t\3\n\3\n"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\5\13z\n\13\3\f\3\f\5\f~\n\f\3\r\3\r\3\r\3\r"+
		"\3\16\3\16\3\16\5\16\u0087\n\16\3\17\3\17\3\17\3\17\3\17\3\17\7\17\u008f"+
		"\n\17\f\17\16\17\u0092\13\17\3\17\2\6\2\b\20\34\20\2\4\6\b\n\f\16\20\22"+
		"\24\26\30\32\34\2\3\3\2\20\21\2\u0099\2\36\3\2\2\2\4(\3\2\2\2\6\61\3\2"+
		"\2\2\b\67\3\2\2\2\nN\3\2\2\2\fP\3\2\2\2\16T\3\2\2\2\20W\3\2\2\2\22d\3"+
		"\2\2\2\24y\3\2\2\2\26}\3\2\2\2\30\177\3\2\2\2\32\u0086\3\2\2\2\34\u0088"+
		"\3\2\2\2\36%\b\2\1\2\37 \f\4\2\2 $\5\4\3\2!\"\f\3\2\2\"$\5\n\6\2#\37\3"+
		"\2\2\2#!\3\2\2\2$\'\3\2\2\2%#\3\2\2\2%&\3\2\2\2&\3\3\2\2\2\'%\3\2\2\2"+
		"()\7\3\2\2)*\5\20\t\2*+\7\4\2\2+,\5\b\5\2,-\7\13\2\2-\5\3\2\2\2.\62\5"+
		"\4\3\2/\62\5\22\n\2\60\62\5\n\6\2\61.\3\2\2\2\61/\3\2\2\2\61\60\3\2\2"+
		"\2\62\7\3\2\2\2\63\64\b\5\1\2\648\5\4\3\2\658\5\n\6\2\668\5\16\b\2\67"+
		"\63\3\2\2\2\67\65\3\2\2\2\67\66\3\2\2\28A\3\2\2\29:\f\b\2\2:@\5\16\b\2"+
		";<\f\7\2\2<@\5\n\6\2=>\f\6\2\2>@\5\4\3\2?9\3\2\2\2?;\3\2\2\2?=\3\2\2\2"+
		"@C\3\2\2\2A?\3\2\2\2AB\3\2\2\2B\t\3\2\2\2CA\3\2\2\2DE\7\25\2\2EF\7\5\2"+
		"\2FG\7\25\2\2GO\7\13\2\2HI\7\25\2\2IJ\7\6\2\2JO\5\f\7\2KL\7\25\2\2LM\7"+
		"\7\2\2MO\7\17\2\2ND\3\2\2\2NH\3\2\2\2NK\3\2\2\2O\13\3\2\2\2PQ\7\25\2\2"+
		"QR\7\b\2\2RS\7\25\2\2S\r\3\2\2\2TU\5\20\t\2UV\7\13\2\2V\17\3\2\2\2WX\b"+
		"\t\1\2XY\5\24\13\2Ya\3\2\2\2Z[\f\3\2\2[\\\7\22\2\2\\]\5\24\13\2]^\7\13"+
		"\2\2^`\3\2\2\2_Z\3\2\2\2`c\3\2\2\2a_\3\2\2\2ab\3\2\2\2b\21\3\2\2\2ca\3"+
		"\2\2\2de\t\2\2\2e\23\3\2\2\2fg\7\25\2\2gh\t\2\2\2hz\7\25\2\2ij\7\25\2"+
		"\2jk\7\t\2\2kz\7\16\2\2lm\7\25\2\2mn\7\21\2\2nz\5\26\f\2op\5\26\f\2pq"+
		"\7\21\2\2qr\7\25\2\2rz\3\2\2\2st\7\25\2\2tu\7\21\2\2uz\7\f\2\2vw\7\f\2"+
		"\2wx\7\21\2\2xz\7\25\2\2yf\3\2\2\2yi\3\2\2\2yl\3\2\2\2yo\3\2\2\2ys\3\2"+
		"\2\2yv\3\2\2\2z\25\3\2\2\2{~\5\30\r\2|~\5\32\16\2}{\3\2\2\2}|\3\2\2\2"+
		"~\27\3\2\2\2\177\u0080\7\n\2\2\u0080\u0081\5\32\16\2\u0081\u0082\7\n\2"+
		"\2\u0082\31\3\2\2\2\u0083\u0087\7\24\2\2\u0084\u0085\7\24\2\2\u0085\u0087"+
		"\5\32\16\2\u0086\u0083\3\2\2\2\u0086\u0084\3\2\2\2\u0087\33\3\2\2\2\u0088"+
		"\u0089\b\17\1\2\u0089\u008a\7\25\2\2\u008a\u0090\3\2\2\2\u008b\u008c\f"+
		"\3\2\2\u008c\u008d\7\23\2\2\u008d\u008f\7\25\2\2\u008e\u008b\3\2\2\2\u008f"+
		"\u0092\3\2\2\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091\35\3\2\2"+
		"\2\u0092\u0090\3\2\2\2\16#%\61\67?ANay}\u0086\u0090";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}