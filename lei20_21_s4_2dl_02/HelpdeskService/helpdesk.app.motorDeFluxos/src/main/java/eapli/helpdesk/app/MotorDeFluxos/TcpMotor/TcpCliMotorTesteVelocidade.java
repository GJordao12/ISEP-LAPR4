package eapli.helpdesk.app.MotorDeFluxos.TcpMotor;

import eapli.helpdesk.AppSettings;
import eapli.helpdesk.Application;
import eapli.helpdesk.atividadeTicket.domain.AtividadeTicket;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TcpCliMotorTesteVelocidade {

    static AppSettings app = Application.settings();
    static final int serverAlgorithmNumber = app.getAlgorithmNumber();
    private static final String IP_SERVER_EXECUTOR_1 = "";
    private static final String IP_SERVER_EXECUTOR_2 = "";

    public static void main(String[] args) throws InterruptedException {

        LinkedHashMap<String, Boolean> servers = TcpCliMotor.preencheMap();
        List<AtividadeTicket> listaEspera = new ArrayList<>();

        Date dataInicio = new Date();

        TcpCliMotor.listaEspera(listaEspera);

        int size = listaEspera.size();

        if (serverAlgorithmNumber == 2) {
            while (true) {
                //verificar a lista de espera e executar as tarefas por ordem de chegada caso exista um servidor disponível
                for (int i = 0; i < listaEspera.size(); i++) {
                    AtividadeTicket a = listaEspera.get(i);
                    String ip = TcpCliMotor.verificaSeHaServerDisponivel(servers);
                    if (ip != null) {
                        servers.put(ip, false);
                        listaEspera.remove(i);
                        i--;
                        new Thread(new TcpCliMotorDeFluxosThread(a, ip, servers)).start();
                    } else {
                        //para garantir que é sempre executada a primeira da lista de espera
                        break;
                    }
                }
                if (listaEspera.isEmpty()) {
                    break;
                }
            }
        } else if (serverAlgorithmNumber == 1) {
            for (int i = 0; i < listaEspera.size(); i++) {
                AtividadeTicket atividade = listaEspera.get(i);
                if (i % 2 == 0) {
                    new Thread(new TcpCliMotorDeFluxosThread(atividade, IP_SERVER_EXECUTOR_1, servers)).start();
                } else {
                    new Thread(new TcpCliMotorDeFluxosThread(atividade, IP_SERVER_EXECUTOR_2, servers)).start();
                }
            }
            listaEspera.clear();
        } else {
            System.out.println("[ERROR] Número de Algoritmo não suportado!");
        }

        if (serverAlgorithmNumber == 1 || serverAlgorithmNumber == 2) {
            Date dataFim = new Date();
            Thread.sleep(30000);
            System.out.println("[INFO] O Algoritmo de Execução " + serverAlgorithmNumber + " demorou " +
                    TimeUnit.SECONDS.convert(Math.abs(dataFim.getTime() - dataInicio.getTime()), TimeUnit.MILLISECONDS) +
                    " segundos a executar " + size + " Tarefas Automáticas");
        }
    }
}