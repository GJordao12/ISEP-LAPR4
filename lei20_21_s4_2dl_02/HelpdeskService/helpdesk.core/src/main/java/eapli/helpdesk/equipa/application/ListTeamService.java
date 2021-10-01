package eapli.helpdesk.equipa.application;

import eapli.helpdesk.equipa.domain.Team;
import eapli.helpdesk.equipa.dto.TeamDTO;
import eapli.helpdesk.equipa.repositories.TeamRepository;
import eapli.helpdesk.infraestrutura.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

public class ListTeamService {

    private final TeamRepository teamRepository = PersistenceContext.repositories().teams();

    public Iterable<Team> allTeams() {
        return teamRepository.findAll();
    }

    public Iterable<TeamDTO> findAllTeams() {

        Iterable<Team> listTeam = this.teamRepository.findAll();

        List<TeamDTO> listTeamDTO = new ArrayList<>();
        listTeam.forEach(team -> listTeamDTO.add(team.toDTO()));
        return listTeamDTO;
    }
}
