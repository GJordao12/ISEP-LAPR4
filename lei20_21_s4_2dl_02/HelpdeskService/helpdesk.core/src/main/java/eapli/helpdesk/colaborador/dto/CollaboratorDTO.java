package eapli.helpdesk.colaborador.dto;

import eapli.framework.representations.dto.DTO;
import eapli.helpdesk.colaborador.domain.MecanographicNumber;

import java.io.Serializable;

@SuppressWarnings("squid:ClassVariableVisibilityCheck")
@DTO
public class CollaboratorDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    public MecanographicNumber mecanographicNumber;
    public String longName;
    public String shortName;
    public String district;
    public String county;
    public String phoneNumber;
    public String birthDate;
    public CollaboratorRoleDTO role;

    public CollaboratorDTO(final MecanographicNumber mecanographicNumber, final String longName, final String shortName, final String district,
                           final String county, final String phoneNumber, final String birthDate,
                           final CollaboratorRoleDTO role) {

        this.mecanographicNumber = mecanographicNumber;
        this.longName = longName;
        this.shortName = shortName;
        this.district = district;
        this.county = county;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Colaborador-> " +
                "- Número Mecanografico: " + mecanographicNumber +
                "- Nome Longo: " + longName +
                "- Nome Curto: " + shortName +
                "- Distrito: " + district +
                "- Concelho: " + county +
                "- Telemovel: " + phoneNumber +
                "- Data de Nascimento: " + birthDate +
                "- Função-> " + role;
    }
}
