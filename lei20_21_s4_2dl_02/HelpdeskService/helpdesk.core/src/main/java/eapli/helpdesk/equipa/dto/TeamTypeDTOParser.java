package eapli.helpdesk.equipa.dto;

import eapli.framework.representations.dto.DTOParser;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumerico;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumericoException;
import eapli.helpdesk.tipoEquipa.domain.TipoEquipa;
import eapli.helpdesk.tipoEquipa.repositories.TipoEquipaRepository;
import eapli.helpdesk.infraestrutura.PersistenceContext;

import java.util.Optional;


public class TeamTypeDTOParser implements DTOParser<TeamTypeDTO, TipoEquipa> {

    private final TipoEquipaRepository repo;

    public TeamTypeDTOParser() {
        repo = PersistenceContext.repositories().tipoEquipa();
    }

    @Override
    public TipoEquipa valueOf(TeamTypeDTO dto) {
        Optional<TipoEquipa> teamType = Optional.empty();
        try {
            teamType = repo.ofIdentity(CodigoAlfaNumerico.valueOf(dto.codAlfaNum));
        } catch (CodigoAlfaNumericoException e) {
            e.printStackTrace();
        }

        if (!teamType.isPresent())
            throw new IllegalArgumentException(String.format("There is no Team type with code: %s", dto.codAlfaNum));

        return teamType.get();
    }
}
