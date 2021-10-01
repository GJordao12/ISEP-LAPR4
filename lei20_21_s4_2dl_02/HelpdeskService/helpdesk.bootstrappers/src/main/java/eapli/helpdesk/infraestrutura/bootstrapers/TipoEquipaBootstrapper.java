package eapli.helpdesk.infraestrutura.bootstrapers;

import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.helpdesk.tipoEquipa.application.AddTipoEquipaController;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumericoException;
import eapli.helpdesk.tipoEquipa.domain.CorException;
import eapli.helpdesk.tipoEquipa.domain.DescricaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class TipoEquipaBootstrapper implements Action {
    private static final Logger LOGGER = LogManager.getLogger(ServicoBootstrapper.class);

    @Override
    public boolean execute() {

        registerTipoEquipa("#FFFFFF","tpRH1","Equipas de Recursos humanos");
        registerTipoEquipa("#FFFFFF","tpDV1","Equipas de Departamento de Vendas");
        return false;
    }

    private void registerTipoEquipa(final String cor, final String codAlfaNum, final String descricao)  {
        final AddTipoEquipaController controller = new AddTipoEquipaController();
        try {
            controller.registerTipoEquipa(cor,codAlfaNum,descricao);
        } catch (final IntegrityViolationException | ConcurrencyException | CodigoAlfaNumericoException | CorException | DescricaoException ex) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)",codAlfaNum);
            LOGGER.trace("Assuming existing record", ex);
        }
    }

}
