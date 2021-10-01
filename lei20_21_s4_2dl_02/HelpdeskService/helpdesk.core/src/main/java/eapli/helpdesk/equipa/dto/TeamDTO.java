package eapli.helpdesk.equipa.dto;

import eapli.framework.representations.dto.DTO;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumerico;

@SuppressWarnings("squid:ClassVariableVisibilityCheck")
@DTO
public class TeamDTO {
    public CodigoAlfaNumerico codigoAlfaNumerico;
    public String acronym;
    public String designation;
    public TeamTypeDTO tipoEquipa;

    public TeamDTO(final CodigoAlfaNumerico codigoAlfaNumerico, final String acronym, final String designation, final TeamTypeDTO tipoEquipa) {
        this.codigoAlfaNumerico = codigoAlfaNumerico;
        this.acronym = acronym;
        this.designation = designation;
        this.tipoEquipa = tipoEquipa;
    }

    @Override
    public String toString() {
        return "EQUIPA-> " +
                "Código AlfaNumerico: " + codigoAlfaNumerico +
                "- Acronimo: " + acronym +
                "- Designação: " + designation +
                "- Tipo Equipa-> " + tipoEquipa;
    }
}
