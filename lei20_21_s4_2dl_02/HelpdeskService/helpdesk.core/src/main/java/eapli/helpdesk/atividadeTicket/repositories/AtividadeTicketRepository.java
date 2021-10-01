package eapli.helpdesk.atividadeTicket.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.atividadeTicket.domain.AtividadeTicket;

import java.io.File;
import java.util.List;

public interface AtividadeTicketRepository extends DomainRepository<Long, AtividadeTicket> {

    List<AtividadeTicket> allPendingAccessTasks(Collaborator collab);

    void assignTask(AtividadeTicket atividadeTicket, Collaborator collab);

    List<AtividadeTicket> allPendingTasks(Collaborator collab);

    File scriptParaExecutar(Long identity);

    List<AtividadeTicket> allAutomaticTasksToDo();

    List<AtividadeTicket> showTasksPossibleToBeDone(Collaborator collab);

    List<AtividadeTicket> manualTasksFromCollab(Collaborator collab);

    List<AtividadeTicket> tasksDoneByCollab(Collaborator collab);
}
