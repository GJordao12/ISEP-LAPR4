package eapli.helpdesk.servico.dto;

import eapli.helpdesk.servico.domain.AtributoFormulario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FormularioDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    public String nome;

    public String codigo;

    public List<AtributoFormulario> attributes;

    public FormularioDTO(String nome, String codigo, List<AtributoFormulario> attributes) {
        this.nome = nome;
        this.codigo = codigo;
        this.attributes = attributes;
    }
}
