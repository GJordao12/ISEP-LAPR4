package eapli.helpdesk.criticidade.dto;

import eapli.framework.representations.dto.DTOParser;
import eapli.helpdesk.criticidade.domain.Criticidade;

public class CriticidadeDTOParser implements DTOParser<CriticidadeDTO, Criticidade> {

    public CriticidadeDTOParser() {
        // Empty
    }

    @Override
    public Criticidade valueOf(CriticidadeDTO dto) {
        return null;
    }
}
