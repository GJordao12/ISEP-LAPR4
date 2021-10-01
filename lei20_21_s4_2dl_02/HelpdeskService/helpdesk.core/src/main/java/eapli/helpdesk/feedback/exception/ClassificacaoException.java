package eapli.helpdesk.feedback.exception;

import java.io.Serializable;

public class ClassificacaoException extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public String getMessage() {
        return "ERRO: A Classificação deve ser maior ou igual a 0 e menor ou igual a 5!";
    }
}
