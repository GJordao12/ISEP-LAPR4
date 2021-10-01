package eapli.helpdesk.equipa.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.equipa.domain.Team;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumerico;

public interface TeamRepository extends DomainRepository<CodigoAlfaNumerico, Team> {
    Iterable<Collaborator> findAllTeamCollaborators(Team team);

    void removeTeamCollaborator(Team team, Collaborator colab);

    void saveTeamCollaborator(Team team, Collaborator colab);

    Iterable<Collaborator> teamCollaboratorsAllowedToJoinTeam(Team team);
}
