package eapli.helpdesk.app.servicosRH.console.presentation.criticidade;

import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.helpdesk.tipoEquipa.domain.CorException;
import eapli.helpdesk.tipoEquipa.domain.DescricaoException;
import eapli.helpdesk.criticidade.application.DefinirCriticidadeController;
import eapli.helpdesk.criticidade.exception.TempoException;
import eapli.helpdesk.criticidade.exception.ValorException;

/**
 * Definir Criticidade User Interface
 *
 * @author Gonçalo Jordão - 1190633
 */
@SuppressWarnings({"squid:S106"})
public class DefinirCriticidadeUI extends AbstractUI {
    private final DefinirCriticidadeController controller = new DefinirCriticidadeController();

    @Override
    protected boolean doShow() {
        boolean flag = true;
        String designacao = "";
        String cor = "";
        String etiqueta = "";
        int valor = 0;
        int tempoMedAprovacao = 0;
        int tempoMaxAprovacao = 0;
        int tempoMedResolucao = 0;
        int tempoMaxResolucao = 0;

        while (flag) {
            try {
                designacao = Console.readLine("Designação da Criticidade:");
                this.controller.registarDesignacao(designacao);
                flag = false;
            } catch (DescricaoException descricaoException) {
                System.out.println("Designação inválida. Por favor insira uma descrição válida.");
            }
        }

        flag = true;

        while (flag) {
            try {
                cor = Console.readLine("Cor da Criticidade (e.g #FFFFFF):");
                this.controller.registarCor(cor);
                flag = false;
            } catch (CorException corException) {
                System.out.println("Cor inválida. Por favor insira uma cor válida (e.g #FFFFFF).");
            }
        }

        while (etiqueta.isEmpty()) {
            etiqueta = Console.readLine("Etiqueta da Criticidade:");
            if (etiqueta.isEmpty()) {
                System.out.println("Etiqueta inválida. Por favor insira uma etiqueta válida.");
            }
        }

        flag = true;

        while (flag) {
            try {
                valor = Console.readInteger("Valor da Criticidade (>= 0):");
                this.controller.registarValor(valor);
                flag = false;
            } catch (ValorException valorException) {
                System.out.println("Valor inválido. Por favor insira um valor válido (>= 0).");
            }
        }

        flag = true;

        while (flag) {
            try {
                tempoMedAprovacao = Console.readInteger("Tempo Médio de Aprovação da Criticidade:");
                this.controller.registarTempo(tempoMedAprovacao);
                flag = false;
            } catch (TempoException tempoException) {
                System.out.println("Tempo Médio de Aprovação inválido. Por favor insira um Tempo Médio de Aprovação válido.");
            }
        }

        flag = true;

        while (flag) {
            try {
                tempoMaxAprovacao = Console.readInteger("Tempo Máximo de Aprovação da Criticidade:");
                this.controller.registarTempo(tempoMaxAprovacao);
                flag = false;
            } catch (TempoException tempoException) {
                System.out.println("Tempo Máximo de Aprovação inválido. Por favor insira um Tempo Máximo de Aprovação válido.");
            }
        }

        flag = true;

        while (flag) {
            try {
                tempoMedResolucao = Console.readInteger("Tempo Médio de Resolução da Criticidade:");
                this.controller.registarTempo(tempoMedResolucao);
                flag = false;
            } catch (TempoException tempoException) {
                System.out.println("Tempo Médio de Resolução inválido. Por favor insira um Tempo Médio de Resolução válido.");
            }
        }

        flag = true;

        while (flag) {
            try {
                tempoMaxResolucao = Console.readInteger("Tempo Máximo de Resolução da Criticidade:");
                this.controller.registarTempo(tempoMaxResolucao);
                flag = false;
            } catch (TempoException tempoException) {
                System.out.println("Tempo Máximo de Resolução inválido. Por favor insira um Tempo Máximo de Resolução válido.");
            }
        }

        try {
            this.controller.definirCriticidade(valor, designacao, cor, etiqueta, tempoMedAprovacao, tempoMaxAprovacao, tempoMedResolucao, tempoMaxResolucao);
        } catch (@SuppressWarnings("unused") final IntegrityViolationException ex) {
            System.out.println("Já existe uma criticidade definida com esse valor!");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Definir Criticidade";
    }
}
