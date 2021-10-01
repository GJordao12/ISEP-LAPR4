package eapli.helpdesk.colaborador.application;

import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.colaborador.domain.MecanographicNumber;
import eapli.helpdesk.colaborador.dto.CollaboratorDTO;
import eapli.helpdesk.colaborador.repositories.CollaboratorRepository;
import eapli.helpdesk.equipa.domain.Team;
import eapli.helpdesk.equipa.repositories.TeamRepository;
import eapli.helpdesk.tipoEquipa.domain.TipoEquipa;
import eapli.helpdesk.infraestrutura.PersistenceContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListCollaboratorService {

    private final CollaboratorRepository collaboratorRepository = PersistenceContext.repositories().collaborators();
    private final TeamRepository teamRepository = PersistenceContext.repositories().teams();

    public Iterable<Collaborator> allCollaborators() {
        return collaboratorRepository.findAll();
    }

    public Iterable<Collaborator> collaboratorsNotInTeamType(TipoEquipa tipoEquipa) {
        return collaboratorRepository.collaboratorsNotInTeamType(tipoEquipa);
    }

    public Iterable<CollaboratorDTO> collaboratorsFromTeam(Team team) {

        Iterable<Collaborator> listCollaborator = this.teamRepository.findAllTeamCollaborators(team);

        List<CollaboratorDTO> listCollaboratorDTO = new ArrayList<>();
        listCollaborator.forEach(colab -> listCollaboratorDTO.add(colab.toDTO()));
        return listCollaboratorDTO;
    }

    public Iterable<CollaboratorDTO> teamCollaboratorsAllowedToJoinTeam(Team team) {

        Iterable<Collaborator> listCollaborator = this.teamRepository.teamCollaboratorsAllowedToJoinTeam(team);

        List<CollaboratorDTO> listCollaboratorDTO = new ArrayList<>();
        listCollaborator.forEach(colab -> listCollaboratorDTO.add(colab.toDTO()));
        return listCollaboratorDTO;
    }

    public Optional<Collaborator> findByMecanographicNumber(MecanographicNumber number) {
        return this.collaboratorRepository.findByMecanographicNumber(number);
    }
}
