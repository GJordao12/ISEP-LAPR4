// Generated from C:/Users/Admin/Desktop/Repositorio/HelpdeskService/helpdesk.core/src/main/java/eapli/helpdesk/antlr/formValidation\FormValidationGrammar.g4 by ANTLR 4.9.1
package eapli.helpdesk.antlr.formValidation;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link FormValidationGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface FormValidationGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link FormValidationGrammarParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(FormValidationGrammarParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormValidationGrammarParser#ifCondition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfCondition(FormValidationGrammarParser.IfConditionContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormValidationGrammarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(FormValidationGrammarParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormValidationGrammarParser#dados}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDados(FormValidationGrammarParser.DadosContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tamanhoValor}
	 * labeled alternative in {@link FormValidationGrammarParser#specs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTamanhoValor(FormValidationGrammarParser.TamanhoValorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code valorEntre}
	 * labeled alternative in {@link FormValidationGrammarParser#specs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValorEntre(FormValidationGrammarParser.ValorEntreContext ctx);
	/**
	 * Visit a parse tree produced by the {@code valorObrigatorio}
	 * labeled alternative in {@link FormValidationGrammarParser#specs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValorObrigatorio(FormValidationGrammarParser.ValorObrigatorioContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormValidationGrammarParser#intervalo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntervalo(FormValidationGrammarParser.IntervaloContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormValidationGrammarParser#condicoesDados}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondicoesDados(FormValidationGrammarParser.CondicoesDadosContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormValidationGrammarParser#condicoes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondicoes(FormValidationGrammarParser.CondicoesContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormValidationGrammarParser#verify}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVerify(FormValidationGrammarParser.VerifyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code valoresIguais}
	 * labeled alternative in {@link FormValidationGrammarParser#condicao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValoresIguais(FormValidationGrammarParser.ValoresIguaisContext ctx);
	/**
	 * Visit a parse tree produced by the {@code estadoValor}
	 * labeled alternative in {@link FormValidationGrammarParser#condicao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEstadoValor(FormValidationGrammarParser.EstadoValorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code valorString}
	 * labeled alternative in {@link FormValidationGrammarParser#condicao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValorString(FormValidationGrammarParser.ValorStringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code valorString1}
	 * labeled alternative in {@link FormValidationGrammarParser#condicao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValorString1(FormValidationGrammarParser.ValorString1Context ctx);
	/**
	 * Visit a parse tree produced by the {@code valorBooleano}
	 * labeled alternative in {@link FormValidationGrammarParser#condicao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValorBooleano(FormValidationGrammarParser.ValorBooleanoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code valorBooleano1}
	 * labeled alternative in {@link FormValidationGrammarParser#condicao}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValorBooleano1(FormValidationGrammarParser.ValorBooleano1Context ctx);
	/**
	 * Visit a parse tree produced by {@link FormValidationGrammarParser#condicaoString}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondicaoString(FormValidationGrammarParser.CondicaoStringContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormValidationGrammarParser#stringEntreAspas}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringEntreAspas(FormValidationGrammarParser.StringEntreAspasContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormValidationGrammarParser#valString}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValString(FormValidationGrammarParser.ValStringContext ctx);
	/**
	 * Visit a parse tree produced by {@link FormValidationGrammarParser#valInt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValInt(FormValidationGrammarParser.ValIntContext ctx);
}