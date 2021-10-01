package eapli.helpdesk.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.colaborador.domain.MecanographicNumber;
import eapli.helpdesk.colaborador.repositories.CollaboratorRepository;
import eapli.helpdesk.tipoEquipa.domain.TipoEquipa;

import java.util.Optional;

public class InMemoryCollaboratorRepository extends InMemoryDomainRepository<Collaborator, MecanographicNumber> implements CollaboratorRepository {
    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<Collaborator> collaboratorsNotInTeamType(TipoEquipa tipoEquipa) {
        return null;
    }

    @Override
    public Optional<Collaborator> findByMecanographicNumber(MecanographicNumber number) {
        return ofIdentity(number);
    }

    @Override
    public Collaborator findByEmail(String emailColab){
        return null;
    }
}
