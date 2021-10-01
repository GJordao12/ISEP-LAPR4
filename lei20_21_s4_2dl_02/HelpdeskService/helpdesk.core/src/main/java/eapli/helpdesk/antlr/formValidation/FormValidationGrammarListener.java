// Generated from C:/Users/Admin/Desktop/Repositorio/HelpdeskService/helpdesk.core/src/main/java/eapli/helpdesk/antlr/formValidation\FormValidationGrammar.g4 by ANTLR 4.9.1
package eapli.helpdesk.antlr.formValidation;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FormValidationGrammarParser}.
 */
public interface FormValidationGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FormValidationGrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(FormValidationGrammarParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormValidationGrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(FormValidationGrammarParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormValidationGrammarParser#ifCondition}.
	 * @param ctx the parse tree
	 */
	void enterIfCondition(FormValidationGrammarParser.IfConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormValidationGrammarParser#ifCondition}.
	 * @param ctx the parse tree
	 */
	void exitIfCondition(FormValidationGrammarParser.IfConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormValidationGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(FormValidationGrammarParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormValidationGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(FormValidationGrammarParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormValidationGrammarParser#dados}.
	 * @param ctx the parse tree
	 */
	void enterDados(FormValidationGrammarParser.DadosContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormValidationGrammarParser#dados}.
	 * @param ctx the parse tree
	 */
	void exitDados(FormValidationGrammarParser.DadosContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tamanhoValor}
	 * labeled alternative in {@link FormValidationGrammarParser#specs}.
	 * @param ctx the parse tree
	 */
	void enterTamanhoValor(FormValidationGrammarParser.TamanhoValorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tamanhoValor}
	 * labeled alternative in {@link FormValidationGrammarParser#specs}.
	 * @param ctx the parse tree
	 */
	void exitTamanhoValor(FormValidationGrammarParser.TamanhoValorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code valorEntre}
	 * labeled alternative in {@link FormValidationGrammarParser#specs}.
	 * @param ctx the parse tree
	 */
	void enterValorEntre(FormValidationGrammarParser.ValorEntreContext ctx);
	/**
	 * Exit a parse tree produced by the {@code valorEntre}
	 * labeled alternative in {@link FormValidationGrammarParser#specs}.
	 * @param ctx the parse tree
	 */
	void exitValorEntre(FormValidationGrammarParser.ValorEntreContext ctx);
	/**
	 * Enter a parse tree produced by the {@code valorObrigatorio}
	 * labeled alternative in {@link FormValidationGrammarParser#specs}.
	 * @param ctx the parse tree
	 */
	void enterValorObrigatorio(FormValidationGrammarParser.ValorObrigatorioContext ctx);
	/**
	 * Exit a parse tree produced by the {@code valorObrigatorio}
	 * labeled alternative in {@link FormValidationGrammarParser#specs}.
	 * @param ctx the parse tree
	 */
	void exitValorObrigatorio(FormValidationGrammarParser.ValorObrigatorioContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormValidationGrammarParser#intervalo}.
	 * @param ctx the parse tree
	 */
	void enterIntervalo(FormValidationGrammarParser.IntervaloContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormValidationGrammarParser#intervalo}.
	 * @param ctx the parse tree
	 */
	void exitIntervalo(FormValidationGrammarParser.IntervaloContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormValidationGrammarParser#condicoesDados}.
	 * @param ctx the parse tree
	 */
	void enterCondicoesDados(FormValidationGrammarParser.CondicoesDadosContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormValidationGrammarParser#condicoesDados}.
	 * @param ctx the parse tree
	 */
	void exitCondicoesDados(FormValidationGrammarParser.CondicoesDadosContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormValidationGrammarParser#condicoes}.
	 * @param ctx the parse tree
	 */
	void enterCondicoes(FormValidationGrammarParser.CondicoesContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormValidationGrammarParser#condicoes}.
	 * @param ctx the parse tree
	 */
	void exitCondicoes(FormValidationGrammarParser.CondicoesContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormValidationGrammarParser#verify}.
	 * @param ctx the parse tree
	 */
	void enterVerify(FormValidationGrammarParser.VerifyContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormValidationGrammarParser#verify}.
	 * @param ctx the parse tree
	 */
	void exitVerify(FormValidationGrammarParser.VerifyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code valoresIguais}
	 * labeled alternative in {@link FormValidationGrammarParser#condicao}.
	 * @param ctx the parse tree
	 */
	void enterValoresIguais(FormValidationGrammarParser.ValoresIguaisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code valoresIguais}
	 * labeled alternative in {@link FormValidationGrammarParser#condicao}.
	 * @param ctx the parse tree
	 */
	void exitValoresIguais(FormValidationGrammarParser.ValoresIguaisContext ctx);
	/**
	 * Enter a parse tree produced by the {@code estadoValor}
	 * labeled alternative in {@link FormValidationGrammarParser#condicao}.
	 * @param ctx the parse tree
	 */
	void enterEstadoValor(FormValidationGrammarParser.EstadoValorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code estadoValor}
	 * labeled alternative in {@link FormValidationGrammarParser#condicao}.
	 * @param ctx the parse tree
	 */
	void exitEstadoValor(FormValidationGrammarParser.EstadoValorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code valorString}
	 * labeled alternative in {@link FormValidationGrammarParser#condicao}.
	 * @param ctx the parse tree
	 */
	void enterValorString(FormValidationGrammarParser.ValorStringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code valorString}
	 * labeled alternative in {@link FormValidationGrammarParser#condicao}.
	 * @param ctx the parse tree
	 */
	void exitValorString(FormValidationGrammarParser.ValorStringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code valorString1}
	 * labeled alternative in {@link FormValidationGrammarParser#condicao}.
	 * @param ctx the parse tree
	 */
	void enterValorString1(FormValidationGrammarParser.ValorString1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code valorString1}
	 * labeled alternative in {@link FormValidationGrammarParser#condicao}.
	 * @param ctx the parse tree
	 */
	void exitValorString1(FormValidationGrammarParser.ValorString1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code valorBooleano}
	 * labeled alternative in {@link FormValidationGrammarParser#condicao}.
	 * @param ctx the parse tree
	 */
	void enterValorBooleano(FormValidationGrammarParser.ValorBooleanoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code valorBooleano}
	 * labeled alternative in {@link FormValidationGrammarParser#condicao}.
	 * @param ctx the parse tree
	 */
	void exitValorBooleano(FormValidationGrammarParser.ValorBooleanoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code valorBooleano1}
	 * labeled alternative in {@link FormValidationGrammarParser#condicao}.
	 * @param ctx the parse tree
	 */
	void enterValorBooleano1(FormValidationGrammarParser.ValorBooleano1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code valorBooleano1}
	 * labeled alternative in {@link FormValidationGrammarParser#condicao}.
	 * @param ctx the parse tree
	 */
	void exitValorBooleano1(FormValidationGrammarParser.ValorBooleano1Context ctx);
	/**
	 * Enter a parse tree produced by {@link FormValidationGrammarParser#condicaoString}.
	 * @param ctx the parse tree
	 */
	void enterCondicaoString(FormValidationGrammarParser.CondicaoStringContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormValidationGrammarParser#condicaoString}.
	 * @param ctx the parse tree
	 */
	void exitCondicaoString(FormValidationGrammarParser.CondicaoStringContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormValidationGrammarParser#stringEntreAspas}.
	 * @param ctx the parse tree
	 */
	void enterStringEntreAspas(FormValidationGrammarParser.StringEntreAspasContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormValidationGrammarParser#stringEntreAspas}.
	 * @param ctx the parse tree
	 */
	void exitStringEntreAspas(FormValidationGrammarParser.StringEntreAspasContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormValidationGrammarParser#valString}.
	 * @param ctx the parse tree
	 */
	void enterValString(FormValidationGrammarParser.ValStringContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormValidationGrammarParser#valString}.
	 * @param ctx the parse tree
	 */
	void exitValString(FormValidationGrammarParser.ValStringContext ctx);
	/**
	 * Enter a parse tree produced by {@link FormValidationGrammarParser#valInt}.
	 * @param ctx the parse tree
	 */
	void enterValInt(FormValidationGrammarParser.ValIntContext ctx);
	/**
	 * Exit a parse tree produced by {@link FormValidationGrammarParser#valInt}.
	 * @param ctx the parse tree
	 */
	void exitValInt(FormValidationGrammarParser.ValIntContext ctx);
}