// Generated from C:/Users/grtjo/Documents/ISEP_Cadeiras/2Ano/2Semestre/LAPR4/lei20_21_s4_2dl_02/HelpdeskService/helpdesk.app.executorTarefasAutomaticas/src/main/java/eapli/helpdesk/app/executorTarefasAutomaticas/automaticsTaskScript\AutomaticTaskScriptGrammar.g4 by ANTLR 4.9.1
package eapli.helpdesk.app.executorTarefasAutomaticas.automaticsTaskScript;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AutomaticTaskScriptGrammarParser}.
 */
public interface AutomaticTaskScriptGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AutomaticTaskScriptGrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(AutomaticTaskScriptGrammarParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomaticTaskScriptGrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(AutomaticTaskScriptGrammarParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomaticTaskScriptGrammarParser#script}.
	 * @param ctx the parse tree
	 */
	void enterScript(AutomaticTaskScriptGrammarParser.ScriptContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomaticTaskScriptGrammarParser#script}.
	 * @param ctx the parse tree
	 */
	void exitScript(AutomaticTaskScriptGrammarParser.ScriptContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomaticTaskScriptGrammarParser#instrucao_script}.
	 * @param ctx the parse tree
	 */
	void enterInstrucao_script(AutomaticTaskScriptGrammarParser.Instrucao_scriptContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomaticTaskScriptGrammarParser#instrucao_script}.
	 * @param ctx the parse tree
	 */
	void exitInstrucao_script(AutomaticTaskScriptGrammarParser.Instrucao_scriptContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomaticTaskScriptGrammarParser#condicao}.
	 * @param ctx the parse tree
	 */
	void enterCondicao(AutomaticTaskScriptGrammarParser.CondicaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomaticTaskScriptGrammarParser#condicao}.
	 * @param ctx the parse tree
	 */
	void exitCondicao(AutomaticTaskScriptGrammarParser.CondicaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomaticTaskScriptGrammarParser#operacao_matematica}.
	 * @param ctx the parse tree
	 */
	void enterOperacao_matematica(AutomaticTaskScriptGrammarParser.Operacao_matematicaContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomaticTaskScriptGrammarParser#operacao_matematica}.
	 * @param ctx the parse tree
	 */
	void exitOperacao_matematica(AutomaticTaskScriptGrammarParser.Operacao_matematicaContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomaticTaskScriptGrammarParser#chamar_funcao}.
	 * @param ctx the parse tree
	 */
	void enterChamar_funcao(AutomaticTaskScriptGrammarParser.Chamar_funcaoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomaticTaskScriptGrammarParser#chamar_funcao}.
	 * @param ctx the parse tree
	 */
	void exitChamar_funcao(AutomaticTaskScriptGrammarParser.Chamar_funcaoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomaticTaskScriptGrammarParser#funcaoEnviarEmail}.
	 * @param ctx the parse tree
	 */
	void enterFuncaoEnviarEmail(AutomaticTaskScriptGrammarParser.FuncaoEnviarEmailContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomaticTaskScriptGrammarParser#funcaoEnviarEmail}.
	 * @param ctx the parse tree
	 */
	void exitFuncaoEnviarEmail(AutomaticTaskScriptGrammarParser.FuncaoEnviarEmailContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomaticTaskScriptGrammarParser#funcaoMudarDistrito}.
	 * @param ctx the parse tree
	 */
	void enterFuncaoMudarDistrito(AutomaticTaskScriptGrammarParser.FuncaoMudarDistritoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomaticTaskScriptGrammarParser#funcaoMudarDistrito}.
	 * @param ctx the parse tree
	 */
	void exitFuncaoMudarDistrito(AutomaticTaskScriptGrammarParser.FuncaoMudarDistritoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomaticTaskScriptGrammarParser#funcaoMudarTelemovel}.
	 * @param ctx the parse tree
	 */
	void enterFuncaoMudarTelemovel(AutomaticTaskScriptGrammarParser.FuncaoMudarTelemovelContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomaticTaskScriptGrammarParser#funcaoMudarTelemovel}.
	 * @param ctx the parse tree
	 */
	void exitFuncaoMudarTelemovel(AutomaticTaskScriptGrammarParser.FuncaoMudarTelemovelContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomaticTaskScriptGrammarParser#funcaoMudarConcelho}.
	 * @param ctx the parse tree
	 */
	void enterFuncaoMudarConcelho(AutomaticTaskScriptGrammarParser.FuncaoMudarConcelhoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomaticTaskScriptGrammarParser#funcaoMudarConcelho}.
	 * @param ctx the parse tree
	 */
	void exitFuncaoMudarConcelho(AutomaticTaskScriptGrammarParser.FuncaoMudarConcelhoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomaticTaskScriptGrammarParser#funcaoBuscarInfoXML}.
	 * @param ctx the parse tree
	 */
	void enterFuncaoBuscarInfoXML(AutomaticTaskScriptGrammarParser.FuncaoBuscarInfoXMLContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomaticTaskScriptGrammarParser#funcaoBuscarInfoXML}.
	 * @param ctx the parse tree
	 */
	void exitFuncaoBuscarInfoXML(AutomaticTaskScriptGrammarParser.FuncaoBuscarInfoXMLContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomaticTaskScriptGrammarParser#passos}.
	 * @param ctx the parse tree
	 */
	void enterPassos(AutomaticTaskScriptGrammarParser.PassosContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomaticTaskScriptGrammarParser#passos}.
	 * @param ctx the parse tree
	 */
	void exitPassos(AutomaticTaskScriptGrammarParser.PassosContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomaticTaskScriptGrammarParser#buscarElemento}.
	 * @param ctx the parse tree
	 */
	void enterBuscarElemento(AutomaticTaskScriptGrammarParser.BuscarElementoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomaticTaskScriptGrammarParser#buscarElemento}.
	 * @param ctx the parse tree
	 */
	void exitBuscarElemento(AutomaticTaskScriptGrammarParser.BuscarElementoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomaticTaskScriptGrammarParser#alterarElemento}.
	 * @param ctx the parse tree
	 */
	void enterAlterarElemento(AutomaticTaskScriptGrammarParser.AlterarElementoContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomaticTaskScriptGrammarParser#alterarElemento}.
	 * @param ctx the parse tree
	 */
	void exitAlterarElemento(AutomaticTaskScriptGrammarParser.AlterarElementoContext ctx);
	/**
	 * Enter a parse tree produced by {@link AutomaticTaskScriptGrammarParser#funcaoEnviarEmailComValor}.
	 * @param ctx the parse tree
	 */
	void enterFuncaoEnviarEmailComValor(AutomaticTaskScriptGrammarParser.FuncaoEnviarEmailComValorContext ctx);
	/**
	 * Exit a parse tree produced by {@link AutomaticTaskScriptGrammarParser#funcaoEnviarEmailComValor}.
	 * @param ctx the parse tree
	 */
	void exitFuncaoEnviarEmailComValor(AutomaticTaskScriptGrammarParser.FuncaoEnviarEmailComValorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code opExprMulDiv}
	 * labeled alternative in {@link AutomaticTaskScriptGrammarParser#inteiro}.
	 * @param ctx the parse tree
	 */
	void enterOpExprMulDiv(AutomaticTaskScriptGrammarParser.OpExprMulDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code opExprMulDiv}
	 * labeled alternative in {@link AutomaticTaskScriptGrammarParser#inteiro}.
	 * @param ctx the parse tree
	 */
	void exitOpExprMulDiv(AutomaticTaskScriptGrammarParser.OpExprMulDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code opExprSumDif}
	 * labeled alternative in {@link AutomaticTaskScriptGrammarParser#inteiro}.
	 * @param ctx the parse tree
	 */
	void enterOpExprSumDif(AutomaticTaskScriptGrammarParser.OpExprSumDifContext ctx);
	/**
	 * Exit a parse tree produced by the {@code opExprSumDif}
	 * labeled alternative in {@link AutomaticTaskScriptGrammarParser#inteiro}.
	 * @param ctx the parse tree
	 */
	void exitOpExprSumDif(AutomaticTaskScriptGrammarParser.OpExprSumDifContext ctx);
}