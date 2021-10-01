package eapli.helpdesk.criticidade.dto;

import eapli.helpdesk.criticidade.domain.Valor;

import java.io.Serializable;

public class CriticidadeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    public Valor valor;

    public CriticidadeDTO(Valor valor) {
        this.valor = valor;
    }
}
