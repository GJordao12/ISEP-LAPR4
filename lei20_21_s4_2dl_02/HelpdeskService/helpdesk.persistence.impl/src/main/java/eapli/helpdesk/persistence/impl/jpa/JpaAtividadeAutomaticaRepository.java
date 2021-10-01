package eapli.helpdesk.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import eapli.helpdesk.Application;
import eapli.helpdesk.atividade.AtividadeAutomatica;
import eapli.helpdesk.servico.repositories.AtividadeAutomaticaRepository;

public class JpaAtividadeAutomaticaRepository extends JpaAutoTxRepository<AtividadeAutomatica, Long, Long> implements AtividadeAutomaticaRepository {

    public JpaAtividadeAutomaticaRepository(final TransactionalContext autoTx) {
        super(autoTx, "codigoAlfanumerico");
    }

    public JpaAtividadeAutomaticaRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "codigoAlfanumerico");
    }
}
