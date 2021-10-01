// Generated from C:/Users/grtjo/Documents/ISEP_Cadeiras/2Ano/2Semestre/LAPR4/lei20_21_s4_2dl_02/HelpdeskService/helpdesk.app.executorTarefasAutomaticas/src/main/java/eapli/helpdesk/app/executorTarefasAutomaticas/automaticsTaskScript\AutomaticTaskScriptGrammar.g4 by ANTLR 4.9.1
package eapli.helpdesk.app.executorTarefasAutomaticas.automaticsTaskScript;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link AutomaticTaskScriptGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AutomaticTaskScriptGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link AutomaticTaskScriptGrammarParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(AutomaticTaskScriptGrammarParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomaticTaskScriptGrammarParser#script}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScript(AutomaticTaskScriptGrammarParser.ScriptContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomaticTaskScriptGrammarParser#instrucao_script}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstrucao_script(AutomaticTaskScriptGrammarParser.Instrucao_scriptContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomaticTaskScriptGrammarParser#condicao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondicao(AutomaticTaskScriptGrammarParser.CondicaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomaticTaskScriptGrammarParser#operacao_matematica}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperacao_matematica(AutomaticTaskScriptGrammarParser.Operacao_matematicaContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomaticTaskScriptGrammarParser#chamar_funcao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChamar_funcao(AutomaticTaskScriptGrammarParser.Chamar_funcaoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomaticTaskScriptGrammarParser#funcaoEnviarEmail}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncaoEnviarEmail(AutomaticTaskScriptGrammarParser.FuncaoEnviarEmailContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomaticTaskScriptGrammarParser#funcaoMudarDistrito}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncaoMudarDistrito(AutomaticTaskScriptGrammarParser.FuncaoMudarDistritoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomaticTaskScriptGrammarParser#funcaoMudarTelemovel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncaoMudarTelemovel(AutomaticTaskScriptGrammarParser.FuncaoMudarTelemovelContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomaticTaskScriptGrammarParser#funcaoMudarConcelho}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncaoMudarConcelho(AutomaticTaskScriptGrammarParser.FuncaoMudarConcelhoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomaticTaskScriptGrammarParser#funcaoBuscarInfoXML}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncaoBuscarInfoXML(AutomaticTaskScriptGrammarParser.FuncaoBuscarInfoXMLContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomaticTaskScriptGrammarParser#passos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPassos(AutomaticTaskScriptGrammarParser.PassosContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomaticTaskScriptGrammarParser#buscarElemento}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBuscarElemento(AutomaticTaskScriptGrammarParser.BuscarElementoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomaticTaskScriptGrammarParser#alterarElemento}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterarElemento(AutomaticTaskScriptGrammarParser.AlterarElementoContext ctx);
	/**
	 * Visit a parse tree produced by {@link AutomaticTaskScriptGrammarParser#funcaoEnviarEmailComValor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncaoEnviarEmailComValor(AutomaticTaskScriptGrammarParser.FuncaoEnviarEmailComValorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code opExprMulDiv}
	 * labeled alternative in {@link AutomaticTaskScriptGrammarParser#inteiro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpExprMulDiv(AutomaticTaskScriptGrammarParser.OpExprMulDivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code opExprSumDif}
	 * labeled alternative in {@link AutomaticTaskScriptGrammarParser#inteiro}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpExprSumDif(AutomaticTaskScriptGrammarParser.OpExprSumDifContext ctx);
}