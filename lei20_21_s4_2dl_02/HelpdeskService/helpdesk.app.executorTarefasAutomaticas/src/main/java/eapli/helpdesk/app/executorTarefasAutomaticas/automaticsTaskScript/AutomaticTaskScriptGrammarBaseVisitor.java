// Generated from C:/Users/grtjo/Documents/ISEP_Cadeiras/2Ano/2Semestre/LAPR4/lei20_21_s4_2dl_02/HelpdeskService/helpdesk.app.executorTarefasAutomaticas/src/main/java/eapli/helpdesk/app/executorTarefasAutomaticas/automaticsTaskScript\AutomaticTaskScriptGrammar.g4 by ANTLR 4.9.1
package eapli.helpdesk.app.executorTarefasAutomaticas.automaticsTaskScript;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link AutomaticTaskScriptGrammarVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public class AutomaticTaskScriptGrammarBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements AutomaticTaskScriptGrammarVisitor<T> {
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitStart(AutomaticTaskScriptGrammarParser.StartContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitScript(AutomaticTaskScriptGrammarParser.ScriptContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitInstrucao_script(AutomaticTaskScriptGrammarParser.Instrucao_scriptContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitCondicao(AutomaticTaskScriptGrammarParser.CondicaoContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitOperacao_matematica(AutomaticTaskScriptGrammarParser.Operacao_matematicaContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitChamar_funcao(AutomaticTaskScriptGrammarParser.Chamar_funcaoContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitFuncaoEnviarEmail(AutomaticTaskScriptGrammarParser.FuncaoEnviarEmailContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitFuncaoMudarDistrito(AutomaticTaskScriptGrammarParser.FuncaoMudarDistritoContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitFuncaoMudarTelemovel(AutomaticTaskScriptGrammarParser.FuncaoMudarTelemovelContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitFuncaoMudarConcelho(AutomaticTaskScriptGrammarParser.FuncaoMudarConcelhoContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitFuncaoBuscarInfoXML(AutomaticTaskScriptGrammarParser.FuncaoBuscarInfoXMLContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitPassos(AutomaticTaskScriptGrammarParser.PassosContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitBuscarElemento(AutomaticTaskScriptGrammarParser.BuscarElementoContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitAlterarElemento(AutomaticTaskScriptGrammarParser.AlterarElementoContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitFuncaoEnviarEmailComValor(AutomaticTaskScriptGrammarParser.FuncaoEnviarEmailComValorContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitOpExprMulDiv(AutomaticTaskScriptGrammarParser.OpExprMulDivContext ctx) { return visitChildren(ctx); }
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public T visitOpExprSumDif(AutomaticTaskScriptGrammarParser.OpExprSumDifContext ctx) { return visitChildren(ctx); }
}