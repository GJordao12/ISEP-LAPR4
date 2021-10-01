package eapli.helpdesk.persistence.impl.jpa;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.atividadeTicket.domain.AtividadeTicket;
import eapli.helpdesk.atividadeTicket.repositories.AtividadeTicketRepository;
import eapli.helpdesk.servico.domain.Servico;
import eapli.helpdesk.equipa.domain.Team;
import eapli.helpdesk.catalogo.domain.Catalogo;

import javax.persistence.TypedQuery;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JpaAtividadeTicketRepository extends HelpdeskJpaRepositoryBase<AtividadeTicket, Long, Long>
        implements AtividadeTicketRepository {

    public JpaAtividadeTicketRepository() {
        super("id");
    }


    @Override
    public List<AtividadeTicket> allPendingAccessTasks(Collaborator collab) {

        List<AtividadeTicket> empty = new ArrayList<>();

        final TypedQuery<Team> colabEquipas = entityManager().createQuery(
                "SELECT t1 from Team t1 WHERE :collab in (SELECT c1 FROM Team t2 JOIN t2.listMembers c1 WHERE t2 = t1) " +
                        "OR :collab in (SELECT c2 FROM Team t3 JOIN t3.listResponsibles c2 WHERE t3 = t1)",
                Team.class);
        colabEquipas.setParameter("collab", collab);

        if (!colabEquipas.getResultList().isEmpty()) {

            final TypedQuery<Catalogo> catalogos = entityManager().createQuery(
                    "SELECT c from Catalogo c",
                    Catalogo.class);

            if (!catalogos.getResultList().isEmpty()) {

                List<Servico> servicosQueTemAcesso = new ArrayList<>();
                for (Catalogo c : catalogos.getResultList()) {
                    for (Team e : colabEquipas.getResultList()) {
                        if (c.equipas().contains(e)) {
                            servicosQueTemAcesso.addAll(c.servicos());
                            break;
                        }
                    }
                }

                final TypedQuery<AtividadeTicket> query = entityManager().createQuery(
                        "SELECT te FROM AtividadeTicket te " +
                                "INNER JOIN Ticket tick on tick = te.ticket " +
                                "INNER JOIN Servico serv on serv = tick.Servico " +
                                "WHERE te.collaborator IS NULL AND (UPPER(te.tipoAtividade) LIKE 'APROVACAO' OR UPPER(te.tipoAtividade) LIKE 'MANUAL') " +
                                "AND serv in :servicosQueTemAcesso",
                        AtividadeTicket.class);
                query.setParameter("servicosQueTemAcesso", servicosQueTemAcesso);

                return query.getResultList();
            }
        }
        return empty;
    }

    @Override
    public void assignTask(AtividadeTicket atividadeTicket, Collaborator collab) {
        atividadeTicket.atribuirColaborador(collab);
        this.save(atividadeTicket);
    }

    @Override
    public List<AtividadeTicket> allPendingTasks(Collaborator collab) {
        final TypedQuery<AtividadeTicket> query = entityManager().createQuery(
                "SELECT te FROM AtividadeTicket te WHERE te.collaborator = :collab AND " +
                        " (te.tipoAtividade like 'MANUAL' OR te.tipoAtividade like 'APROVACAO') " +
                        " AND te.dataFim IS NULL",
                AtividadeTicket.class);
        query.setParameter("collab", collab);

        return query.getResultList();
    }

    @Override
    public File scriptParaExecutar(Long identity) {
        final TypedQuery<File> query = entityManager().createQuery(
                "",
                File.class);
        query.setParameter("id", identity);

        return query.getResultList().get(0);
    }

    @Override
    public List<AtividadeTicket> allAutomaticTasksToDo(){
        final TypedQuery<AtividadeTicket> query = entityManager().createQuery(
                "SELECT te FROM AtividadeTicket te WHERE  " +
                        " te.tipoAtividade like 'AUTOMATICA'  " +
                        " AND te.dataFim IS NULL",
                AtividadeTicket.class);


        return query.getResultList();
    }

    @Override
    public List<AtividadeTicket> showTasksPossibleToBeDone(Collaborator collab) {
        final TypedQuery<AtividadeTicket> query = entityManager().createQuery(
                "SELECT te FROM AtividadeTicket te WHERE te.collaborator = :collab AND UPPER(te.ticket.estado) not like 'REJEITADO' AND" +
                        "(UPPER(te.tipoAtividade) like 'MANUAL' AND te.ticket NOT IN " +
                        "(SELECT te1.ticket FROM AtividadeTicket te1 WHERE te1.collaborator = :collab " +
                        "AND UPPER(te1.tipoAtividade) like 'APROVACAO' AND te1.dataFim IS NULL)" +
                        " OR UPPER(te.tipoAtividade) like 'APROVACAO') " +
                        " AND te.dataFim IS NULL",
                AtividadeTicket.class);
        query.setParameter("collab", collab);

        return query.getResultList();
    }

    @Override
    public List<AtividadeTicket> manualTasksFromCollab(Collaborator collab) {
        final TypedQuery<AtividadeTicket> query = entityManager().createQuery(
                "SELECT te FROM AtividadeTicket te WHERE te.collaborator = :collab AND" +
                        "((UPPER(te.tipoAtividade)) like 'MANUAL' or (UPPER(te.tipoAtividade)) like 'APROVACAO')" +
                        " AND te.dataFim IS NULL",
                AtividadeTicket.class);
        query.setParameter("collab", collab);

        return query.getResultList();
    }

    @Override
    public List<AtividadeTicket> tasksDoneByCollab(Collaborator collab) {
        final TypedQuery<AtividadeTicket> query = entityManager().createQuery(
                "SELECT te FROM AtividadeTicket te WHERE te.collaborator = :collab AND" +
                        "((UPPER(te.tipoAtividade)) like 'MANUAL' or (UPPER(te.tipoAtividade)) like 'APROVACAO')" +
                        " AND te.dataFim is not null",
                AtividadeTicket.class);
        query.setParameter("collab", collab);

        return query.getResultList();
    }

}
