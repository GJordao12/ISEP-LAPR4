package eapli.helpdesk.equipa.dto;

import eapli.helpdesk.equipa.domain.Team;
import eapli.helpdesk.equipa.repositories.TeamRepository;
import eapli.framework.representations.dto.DTOParser;
import eapli.helpdesk.infraestrutura.PersistenceContext;

import java.util.Optional;

public class TeamDTOParser implements DTOParser<TeamDTO, Team> {

    private final TeamRepository teamRepository = PersistenceContext.repositories().teams();

    public TeamDTOParser() {
        // Empty
    }

    @Override
    public Team valueOf(final TeamDTO dto) {
        Optional<Team>  team = teamRepository.ofIdentity(dto.codigoAlfaNumerico);

        if (!team.isPresent())
            throw new IllegalArgumentException(String.format("No team found with code: %s", dto.codigoAlfaNumerico));

        return team.get();
    }
}
