package eapli.helpdesk.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import eapli.helpdesk.Application;
import eapli.helpdesk.atividade.AtividadeManual;
import eapli.helpdesk.servico.repositories.AtividadeManualRepository;

public class JpaAtividadeManualRepository extends JpaAutoTxRepository<AtividadeManual, Long, Long> implements AtividadeManualRepository {

    public JpaAtividadeManualRepository(final TransactionalContext autoTx) {
        super(autoTx, "codigoAlfanumerico");
    }

    public JpaAtividadeManualRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "codigoAlfanumerico");
    }
}
