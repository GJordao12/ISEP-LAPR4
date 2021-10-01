package eapli.helpdesk.equipa.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.helpdesk.colaborador.domain.*;
import eapli.helpdesk.equipa.domain.Acronym;
import eapli.helpdesk.equipa.domain.Designation;
import eapli.helpdesk.equipa.domain.Team;
import eapli.helpdesk.equipa.exception.AcronymException;
import eapli.helpdesk.equipa.exception.DesignationException;
import eapli.helpdesk.equipa.repositories.TeamRepository;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumerico;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumericoException;
import eapli.helpdesk.tipoEquipa.domain.TipoEquipa;
import eapli.helpdesk.tipoEquipa.repositories.TipoEquipaRepository;
import eapli.helpdesk.infraestrutura.PersistenceContext;
import eapli.helpdesk.user.domain.HelpdeskRoles;

import java.util.List;

public class AddTeamController {

    private final TeamRepository teamRepository = PersistenceContext.repositories().teams();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final TipoEquipaRepository teamTypeRepository = PersistenceContext.repositories().tipoEquipa();

    public void addTeam(String codigoAlfaNumerico, String acronym, String designation, TipoEquipa teamType, List<Collaborator> listR, List<Collaborator> listM) throws CodigoAlfaNumericoException, AcronymException, DesignationException {

        authz.ensureAuthenticatedUserHasAnyOf(HelpdeskRoles.HR_MANAGER, HelpdeskRoles.POWER_USER);

        CodigoAlfaNumerico codAlfaNumerico = registerAlphanumericCode(codigoAlfaNumerico);
        Acronym acron = registerAcronym(acronym);
        Designation desig = registerDesignation(designation);

        Team team = new Team(codAlfaNumerico, acron, desig, teamType, listR, listM);
        this.teamRepository.save(team);
    }

    public Iterable<TipoEquipa> allTeamTypes() {
        return teamTypeRepository.findAll();
    }

    public CodigoAlfaNumerico registerAlphanumericCode(String alphanumericCode) throws CodigoAlfaNumericoException {
        return new CodigoAlfaNumerico(alphanumericCode);
    }

    public Acronym registerAcronym(String acronym) throws AcronymException {
        return new Acronym(acronym);
    }

    public Designation registerDesignation(String designation) throws DesignationException {
        return new Designation(designation);
    }
}
