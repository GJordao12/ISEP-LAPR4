package eapli.helpdesk.colaborador.dto;

import eapli.framework.representations.dto.DTO;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumerico;

import java.io.Serializable;

@SuppressWarnings("squid:ClassVariableVisibilityCheck")
@DTO
public class CollaboratorRoleDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    public CodigoAlfaNumerico codigoAlfaNumerico;
    public String designation;

    public CollaboratorRoleDTO(final CodigoAlfaNumerico codigoAlfaNumerico, final String designation) {
        this.codigoAlfaNumerico = codigoAlfaNumerico;
        this.designation = designation;
    }

    @Override
    public String toString() {
        return "Código AlfaNumerico: " + codigoAlfaNumerico +
                "- Designação: " + designation;
    }
}
