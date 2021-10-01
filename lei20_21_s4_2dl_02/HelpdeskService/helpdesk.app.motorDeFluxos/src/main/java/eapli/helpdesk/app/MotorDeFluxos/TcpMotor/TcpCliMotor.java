package eapli.helpdesk.app.MotorDeFluxos.TcpMotor;

import eapli.helpdesk.AppSettings;
import eapli.helpdesk.Application;
import eapli.helpdesk.atividadeTicket.domain.AtividadeTicket;
import eapli.helpdesk.atividadeTicket.repositories.AtividadeTicketRepository;
import eapli.helpdesk.infraestrutura.PersistenceContext;
import eapli.helpdesk.ticket.repositories.TicketRepository;


import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

class TcpCliMotor {

    static AppSettings app = Application.settings();
    static final int serverAlgorithmNumber = app.getAlgorithmNumber();
    private static final String IP_SERVER_EXECUTOR_1 = "";
    private static final String IP_SERVER_EXECUTOR_2 = "";

    public static void main(String[] args) throws InterruptedException {

        LinkedHashMap<String, Boolean> servers = preencheMap();
        List<AtividadeTicket> listaEspera = new ArrayList<>();

        if (serverAlgorithmNumber == 2) {

            while (true) {

                //buscar as tarefas automáticas e adicionar à liste de espera
                listaEspera(listaEspera);

                //verificar a lista de espera e executar as tarefas por ordem de chegada caso exista um servidor disponível
                for (int i = 0; i < listaEspera.size(); i++) {
                    AtividadeTicket a = listaEspera.get(i);
                    String ip = verificaSeHaServerDisponivel(servers);
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

                //Tempo de pausa para nao sobrecarregar a base de dados
                Thread.sleep(30000);
            }
        } else if (serverAlgorithmNumber == 1) {
            while (true) {
                listaEspera(listaEspera);
                for (int i = 0; i < listaEspera.size(); i++) {
                    AtividadeTicket atividade = listaEspera.get(i);
                    if (i % 2 == 0) {
                        new Thread(new TcpCliMotorDeFluxosThread(atividade, IP_SERVER_EXECUTOR_1, servers)).start();
                    } else {
                        new Thread(new TcpCliMotorDeFluxosThread(atividade, IP_SERVER_EXECUTOR_2, servers)).start();
                    }
                }
                listaEspera.clear();

                //Tempo de pausa para nao sobrecarregar a base de dados
                Thread.sleep(30000);
            }
        } else {
            System.out.println("[ERROR] Número de Algoritmo não suportado!");
        }
    }

    static void listaEspera(List<AtividadeTicket> listaEspera) {
        Iterable<AtividadeTicket> listAtividade = PersistenceContext.repositories().atividadesTicket().findAll();
        for (AtividadeTicket a : listAtividade) {
            if (!listaEspera.contains(a)
                    && a.tipoAtividade().equalsIgnoreCase("Automatica")
                    && !a.ticket().estado().equalsIgnoreCase("Resolvido")
                    && !a.ticket().estado().equalsIgnoreCase("Em Resolucao")
                    && !a.ticket().estado().equalsIgnoreCase("Erro")
                    && (a.ticket().estado().equalsIgnoreCase("Submetido") && a.ticket().servico().workflow().atividadeManualAprovacao() == null
                    || (a.ticket().servico().workflow().atividadeManualAprovacao() != null && a.ticket().estado().equalsIgnoreCase("Aprovado")))) {
                listaEspera.add(a);
            }
        }
    }

    //preenche o map com os dois ips dos servidores que temos de execução de atividade automáticas e atribui-lhes o valor true (disponível)
    static LinkedHashMap<String, Boolean> preencheMap() {
        LinkedHashMap<String, Boolean> servers = new LinkedHashMap<>();
        servers.put(IP_SERVER_EXECUTOR_1, true);
        servers.put(IP_SERVER_EXECUTOR_2, true);
        return servers;
    }

    //verifica se há um server disponível para executar tarefas. Se existir retorna o IP se não retorna null
    static String verificaSeHaServerDisponivel(LinkedHashMap<String, Boolean> servers) {
        for (String ip : servers.keySet()) {
            if (servers.get(ip)) {
                return ip;
            }
        }
        return null;
    }
}

class TcpCliMotorDeFluxosThread implements Runnable {

    private final AtividadeTicketRepository repoAtividadeTicket = PersistenceContext.repositories().atividadesTicket();
    private final TicketRepository ticketRepository = PersistenceContext.repositories().tickets();
    private AtividadeTicket atividadeTicket;
    private final String IP;
    private LinkedHashMap<String, Boolean> servers;
    private Boolean flag;

    public TcpCliMotorDeFluxosThread(AtividadeTicket atividadeTicket, String IP, LinkedHashMap<String, Boolean> servers) {
        this.atividadeTicket = atividadeTicket;
        this.IP = IP;
        this.servers = servers;
        this.flag = true;
    }

    public void run() {

        InetAddress serverIP = null;
        SSLSocket sock = null;
        // Trust these certificates provided by servers
        System.setProperty("javax.net.ssl.trustStore", "cliente1Executor.jks");
        System.setProperty("javax.net.ssl.trustStorePassword", "123abc456def");

        // Use this certificate and private key for client certificate when requested by the server
        System.setProperty("javax.net.ssl.keyStore", "cliente1Executor.jks");
        System.setProperty("javax.net.ssl.keyStorePassword", "123abc456def");

        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();

        try {
            serverIP = InetAddress.getByName(this.IP);
        } catch (UnknownHostException ex) {
            System.out.println("Invalid server specified: " + this.IP);
            System.exit(1);
        }

        try {
            sock = (SSLSocket) sf.createSocket(serverIP, 2020);
        } catch (IOException ex) {
            System.out.println("Failed to connect to: " + this.IP + ":" + 2020);
            System.out.println("Application aborted.");
            System.exit(1);
        }

        System.out.println("Connected to: " + this.IP + ":" + 2020);

        try {
            sock.startHandshake();
            DataOutputStream sOutData = new DataOutputStream(sock.getOutputStream());
            DataInputStream sInData = new DataInputStream(sock.getInputStream());

            //Mandar um pedido para o servido -> codigo: 0 (Teste)
            byte[] clienteMessage = {(byte) 0, (byte) 0, (byte) 0, (byte) 0};
            sOutData.write(clienteMessage);
            sOutData.flush();

            //Esperar a resposta do servidor a dizer que entendeu a mensagem
            byte[] serverMessage = sInData.readNBytes(4);
            if (serverMessage[1] == 2) {

                ObjectOutputStream sOutObject = new ObjectOutputStream(sock.getOutputStream());
                ObjectInputStream sInObject = new ObjectInputStream(sock.getInputStream());

                this.atividadeTicket.ticket().changeEstado("Em resolucao");
                ticketRepository.save(this.atividadeTicket.ticket());

                sOutObject.writeObject(this.atividadeTicket);
                sOutObject.flush();

                byte[] clienteMessageOpt = sInData.readNBytes(4);
                int option = clienteMessageOpt[1];

                if (option == 3) {
                    AtividadeTicket atAtualizado = (AtividadeTicket) sInObject.readObject();
                    repoAtividadeTicket.save(atAtualizado);
                    atAtualizado.ticket().changeEstado("Resolvido");
                    ticketRepository.save(atAtualizado.ticket());
                }
                if (option == 4) {
                    atividadeTicket.ticket().changeEstado("Erro");
                    ticketRepository.save(atividadeTicket.ticket());
                }

                //Mandar um pedido para o servido -> codigo: 1 (Fim)
                byte[] clienteMessageEnd = {(byte) 0, (byte) 1, (byte) 0, (byte) 0};
                sOutData.write(clienteMessageEnd);
                sOutData.flush();

                byte[] serverMessageEnd = sInData.readNBytes(4);
                if (serverMessageEnd[1] == 2) {
                    sock.close();
                    this.flag = false;
                } else {
                    System.out.println("==> ERROR: Erro no pacote do Servidor");
                    atividadeTicket.ticket().changeEstado("Submetido");
                    ticketRepository.save(atividadeTicket.ticket());
                }
            } else {
                System.out.println("==> ERROR: Erro no pacote do Servidor");
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("==> ERROR: Falha durante a troca de informação com o server");
            atividadeTicket.ticket().changeEstado("Submetido");
            ticketRepository.save(atividadeTicket.ticket());
        } finally {
            if (this.flag) {
                try {
                    sock.close();
                } catch (IOException e) {
                    System.out.println("==> ERROR: Falha a fechar o socket");
                }
            }
        }
        this.servers.put(this.IP, true);
    }
}
