package eapli.helpdesk.infraestrutura.bootstrapers;

import eapli.helpdesk.criticidade.application.DefinirCriticidadeController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;

public class CriticidadeBootstrapper implements Action {

    private static final Logger LOGGER = LogManager.getLogger(CriticidadeBootstrapper.class);

    private final DefinirCriticidadeController controller = new DefinirCriticidadeController();

    @Override
    public boolean execute() {

        register(1, "Muito Baixa", "#FFFFFF", "Muito Baixa", 50, 50, 50, 50);
        register(2, "Baixa", "#FFFFFF", "Baixa", 40, 40, 40, 40);
        register(3, "Média", "#FFFFFF", "Média", 30, 30, 30, 30);
        register(4, "Alta", "#FFFFFF", "Alta", 20, 20, 20, 20);
        register(5, "Muito Alta", "#FFFFFF", "Muito Alta", 10, 10, 10, 10);
        return true;
    }

    private void register(final Integer valor, final String designacao, final String cor, final String etiqueta, final Integer tempoMedAprovacao, final Integer tempoMaxAprovacao, final Integer tempoMedResolucao, final Integer tempoMaxResolucao) {
        try {
            controller.definirCriticidade(valor, designacao, cor, etiqueta, tempoMedAprovacao, tempoMaxAprovacao, tempoMedResolucao, tempoMaxResolucao);
            LOGGER.info(valor);
        } catch (final IntegrityViolationException | ConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", valor);
            LOGGER.trace("Assuming existing record", e);
        }
    }
}
