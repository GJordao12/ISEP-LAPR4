package eapli.helpdesk.equipa.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.helpdesk.colaborador.application.ListCollaboratorService;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.colaborador.dto.CollaboratorDTO;
import eapli.helpdesk.colaborador.dto.CollaboratorDTOParser;
import eapli.helpdesk.equipa.domain.Team;
import eapli.helpdesk.equipa.dto.TeamDTO;
import eapli.helpdesk.equipa.dto.TeamDTOParser;
import eapli.helpdesk.equipa.repositories.TeamRepository;
import eapli.helpdesk.infraestrutura.PersistenceContext;
import eapli.helpdesk.user.domain.HelpdeskRoles;

public class RemoveTeamCollaboratorController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final TeamRepository teamRepository = PersistenceContext.repositories().teams();
    private final ListTeamService listTeamService = new ListTeamService();
    private final ListCollaboratorService listCollaboratorService = new ListCollaboratorService();
    private final TeamDTOParser teamDTOParser = new TeamDTOParser();
    private final CollaboratorDTOParser collaboratorDTOParser = new CollaboratorDTOParser();
    private Team team;

    public Iterable<TeamDTO> allTeams() {
        return this.listTeamService.findAllTeams();
    }

    public Iterable<CollaboratorDTO> teamCollaborators(TeamDTO teamDTO){
        this.team = teamDTOParser.valueOf(teamDTO);
        return this.listCollaboratorService.collaboratorsFromTeam(team);
    }

    public void removeTeamCollaboraotr(CollaboratorDTO collaboratorDTO) {
        authz.ensureAuthenticatedUserHasAnyOf(HelpdeskRoles.POWER_USER,HelpdeskRoles.HR_MANAGER);
        Collaborator colab = collaboratorDTOParser.valueOf(collaboratorDTO);
       teamRepository.removeTeamCollaborator(this.team,colab);
    }
}
