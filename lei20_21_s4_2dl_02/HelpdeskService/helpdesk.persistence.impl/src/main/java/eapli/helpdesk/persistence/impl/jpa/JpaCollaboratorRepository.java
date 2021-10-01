package eapli.helpdesk.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import eapli.helpdesk.Application;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.colaborador.domain.MecanographicNumber;
import eapli.helpdesk.colaborador.repositories.CollaboratorRepository;
import eapli.helpdesk.tipoEquipa.domain.TipoEquipa;

import javax.persistence.TypedQuery;

public class JpaCollaboratorRepository extends JpaAutoTxRepository<Collaborator, MecanographicNumber, MecanographicNumber>
        implements CollaboratorRepository {

    public JpaCollaboratorRepository(final TransactionalContext autoTx) {
        super(autoTx, "mecanographicNumber");
    }

    public JpaCollaboratorRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(), "mecanographicNumber");
    }

    @Override
    public Iterable<Collaborator> collaboratorsNotInTeamType(TipoEquipa tipoEquipa) {
        return null;
    }

    @Override
    public Collaborator findByEmail(String emailColab) {
        final TypedQuery<Collaborator> query = createQuery(
                "SELECT c FROM Collaborator c WHERE c.systemUser.email.email =:emailColab",
                Collaborator.class);
        query.setParameter("emailColab", emailColab);

        return query.getResultList().get(0);
    }
}
