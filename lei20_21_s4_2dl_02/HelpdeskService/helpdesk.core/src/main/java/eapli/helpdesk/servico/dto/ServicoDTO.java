package eapli.helpdesk.servico.dto;

import eapli.framework.representations.dto.DTO;
import eapli.helpdesk.criticidade.dto.CriticidadeDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@DTO
public class ServicoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    public String code;
    public String title;
    public String briefDescription;
    public String detailedDescription;
    public String feedback;
    public List<String> keywords;
    public byte[] icon;
    public FormularioDTO formDTO;
    public CriticidadeDTO critDTO;

    public ServicoDTO(String code, String title, String briefDescription, String detailedDescription,
                      String feedback, List<String> keywords, byte[] icon, FormularioDTO formDTO, CriticidadeDTO critDTO) {
        this.code = code;
        this.title = title;
        this.briefDescription = briefDescription;
        this.detailedDescription = detailedDescription;
        this.feedback = feedback;
        this.keywords = new ArrayList<>(keywords);
        this.icon = icon;
        this.formDTO = formDTO;
        this.critDTO=critDTO;
    }


}
