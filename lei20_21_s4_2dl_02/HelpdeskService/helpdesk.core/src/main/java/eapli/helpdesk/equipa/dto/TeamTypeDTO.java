package eapli.helpdesk.equipa.dto;

import eapli.framework.representations.dto.DTO;

@SuppressWarnings("squid:ClassVariableVisibilityCheck")
@DTO
public class TeamTypeDTO {

    public String codAlfaNum;
    public String descricao;
    public String cor;

    public TeamTypeDTO(final String cor, final String codAlfaNum,final String descricao) {
        this.cor=cor;
        this.codAlfaNum = codAlfaNum;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Código AlfaNumérico: " + codAlfaNum +
                "- Descrição: " + descricao +
                "- Cor: " + cor;
    }
}
