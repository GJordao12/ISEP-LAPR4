package eapli.helpdesk.colaborador.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.colaborador.domain.MecanographicNumber;
import eapli.helpdesk.tipoEquipa.domain.TipoEquipa;

import java.util.Optional;

public interface CollaboratorRepository extends DomainRepository<MecanographicNumber, Collaborator> {

    Iterable<Collaborator> collaboratorsNotInTeamType(TipoEquipa tipoEquipa);

    default Optional<Collaborator> findByMecanographicNumber(MecanographicNumber number) {
        return ofIdentity(number);
    }

    Collaborator findByEmail(String emailColab);
}
