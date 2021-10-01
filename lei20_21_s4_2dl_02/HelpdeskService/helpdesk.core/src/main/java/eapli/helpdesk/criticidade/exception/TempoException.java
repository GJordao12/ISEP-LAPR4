package eapli.helpdesk.criticidade.exception;

import java.io.Serializable;

public class TempoException extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public String getMessage() {
        return "ERRO: Os Tempos da Criticidade devem ser maiores que 0!";
    }
}
