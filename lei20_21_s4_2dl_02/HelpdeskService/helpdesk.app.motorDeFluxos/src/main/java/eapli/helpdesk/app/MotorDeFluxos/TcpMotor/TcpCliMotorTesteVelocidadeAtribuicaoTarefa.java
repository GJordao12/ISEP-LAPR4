package eapli.helpdesk.app.MotorDeFluxos.TcpMotor;

import eapli.helpdesk.AppSettings;
import eapli.helpdesk.Application;
import eapli.helpdesk.algoritmosAtribuicao.AlgoritmosAtribuicaoTarefas;
import eapli.helpdesk.infraestrutura.PersistenceContext;
import eapli.helpdesk.servico.repositories.ServicoRepository;
import eapli.helpdesk.ticket.domain.Ticket;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TcpCliMotorTesteVelocidadeAtribuicaoTarefa {

    private static final AlgoritmosAtribuicaoTarefas algoritmosAtribuicaoTarefas = new AlgoritmosAtribuicaoTarefas();
    private static final ServicoRepository servicoRepository = PersistenceContext.repositories().servico();
    static AppSettings app = Application.settings();
    static final int algorithmNumber = app.getAutomaticTaskAssignmentOption();

    public static void main(String[] args) {
        Ticket ticket = new Ticket(servicoRepository.findAll().iterator().next(), Calendar.getInstance(), 2, null, null);
        Date dataInicio = new Date();
        if (algorithmNumber == 1) {
            algoritmosAtribuicaoTarefas.firstTaskAssignmentAlgorithm(ticket);
            Date dataFim = new Date();
            System.out.println("[INFO] O Algoritmo de Atribuição de Tarefas 1 demorou " +
                    TimeUnit.SECONDS.convert(Math.abs(dataFim.getTime() - dataInicio.getTime()), TimeUnit.MILLISECONDS) +
                    " segundos a atribuir a tarefa");
        }

        if (algorithmNumber == 2) {
            algoritmosAtribuicaoTarefas.secondTaskAssignmentAlgorithm(ticket);
            Date dataFim = new Date();
            System.out.println("[INFO] O Algoritmo de Atribuição de Tarefas 2 demorou " +
                    TimeUnit.SECONDS.convert(Math.abs(dataFim.getTime() - dataInicio.getTime()), TimeUnit.MILLISECONDS) +
                    " segundos a atribuir a tarefa");
        }
    }
}
