package eapli.helpdesk.persistence.impl.jpa;

import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.equipa.domain.Team;
import eapli.helpdesk.equipa.repositories.TeamRepository;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumerico;
import eapli.helpdesk.tipoEquipa.domain.TipoEquipa;

import javax.persistence.TypedQuery;

public class JpaTeamRepository extends HelpdeskJpaRepositoryBase<Team, CodigoAlfaNumerico, CodigoAlfaNumerico>
        implements TeamRepository {

    public JpaTeamRepository() {
        super("codigoAlfaNumerico");
    }

    @Override
    public Iterable<Collaborator> findAllTeamCollaborators(final Team team) {
        final TypedQuery<Collaborator> query = entityManager().createQuery(
                "SELECT c FROM Team t JOIN t.listMembers c WHERE t.codigoAlfaNumerico = :teamCode",
                Collaborator.class);
        CodigoAlfaNumerico cod = team.codigoAlfaNumerico();
        query.setParameter("teamCode", cod);

        return query.getResultList();
    }

    @Override
    public void removeTeamCollaborator(Team team, Collaborator colab) {
        team.removerMembro(colab);
        this.save(team);
    }

    @Override
    public void saveTeamCollaborator(Team team, Collaborator colab) {
        team.adicionarMembro(colab);
        this.save(team);
    }

    @Override
    public Iterable<Collaborator> teamCollaboratorsAllowedToJoinTeam(Team team) {
        final TypedQuery<Collaborator> query = entityManager().createQuery(
                "SELECT colab from Collaborator colab WHERE colab NOT IN (SELECT c1 FROM Team t1 JOIN t1.listMembers c1 WHERE t1.tipoEquipa = :teamType) AND " +
                        "colab NOT IN (SELECT c2 FROM Team t2 JOIN t2.listResponsibles c2 WHERE t2.tipoEquipa = :teamType)",
                Collaborator.class);

        TipoEquipa tipoEquipa = team.teamType();
        query.setParameter("teamType", tipoEquipa);

        return query.getResultList();
    }
}
