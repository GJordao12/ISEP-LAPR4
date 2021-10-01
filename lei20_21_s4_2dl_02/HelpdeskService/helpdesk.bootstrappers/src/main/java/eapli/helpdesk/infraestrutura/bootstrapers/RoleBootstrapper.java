package eapli.helpdesk.infraestrutura.bootstrapers;

import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.helpdesk.colaborador.domain.RoleCollaborator;
import eapli.helpdesk.equipa.domain.Designation;
import eapli.helpdesk.equipa.exception.DesignationException;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumerico;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumericoException;
import eapli.helpdesk.infraestrutura.PersistenceContext;
import eapli.helpdesk.funcao.RoleRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RoleBootstrapper implements Action {
    private static final Logger LOGGER = LogManager.getLogger(RoleBootstrapper.class);
    private final RoleRepository roleRepository = PersistenceContext.repositories().roles();

    @Override
    public boolean execute() {
        try {
            CodigoAlfaNumerico cod = new CodigoAlfaNumerico("AAA111");
            Designation desig = new Designation("HR_MANAGER");
            RoleCollaborator role = new RoleCollaborator(cod, desig);
            registerRole(role);
            CodigoAlfaNumerico cod1 = new CodigoAlfaNumerico("AAA123");
            Designation desig1 = new Designation("SERVICE_MANAGER");
            RoleCollaborator role1 = new RoleCollaborator(cod1, desig1);
            registerRole(role1);
        } catch (DesignationException | CodigoAlfaNumericoException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void registerRole(RoleCollaborator roleCollaborator) {
        try {
            roleRepository.save(roleCollaborator);
        } catch (final IntegrityViolationException | ConcurrencyException ex) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", roleCollaborator.codigoAlfaNumerico());
            LOGGER.trace("Assuming existing record", ex);
        }
    }
}
