package eapli.helpdesk.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.atividadeTicket.domain.AtividadeTicket;
import eapli.helpdesk.atividadeTicket.repositories.AtividadeTicketRepository;

import java.io.File;
import java.util.List;

public class InMemoryAtividadeTicketRepository extends InMemoryDomainRepository<AtividadeTicket, Long> implements AtividadeTicketRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public List<AtividadeTicket> allPendingAccessTasks(Collaborator collab) {
        return null;
    }

    @Override
    public void assignTask(AtividadeTicket atividadeTicket, Collaborator collab) {

    }

    @Override
    public List<AtividadeTicket> allPendingTasks(Collaborator collab) {
        return null;
    }

    @Override
    public File scriptParaExecutar(Long identity) {
        return null;
    }

    @Override
    public List<AtividadeTicket> allAutomaticTasksToDo() {
        return null;
    }

    @Override
    public List<AtividadeTicket> showTasksPossibleToBeDone(Collaborator collab) {
        return null;
    }

    @Override
    public List<AtividadeTicket> manualTasksFromCollab(Collaborator collab) {
        return null;
    }

    @Override
    public List<AtividadeTicket> tasksDoneByCollab(Collaborator collab) {
        return null;
    }
}
