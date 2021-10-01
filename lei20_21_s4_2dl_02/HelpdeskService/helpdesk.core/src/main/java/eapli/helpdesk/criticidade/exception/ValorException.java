package eapli.helpdesk.criticidade.exception;

import java.io.Serializable;

public class ValorException extends Exception implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public String getMessage() {
        return "ERRO: O Valor da Criticidade deve ser maior ou igual a 0!";
    }
}
