package eapli.helpdesk.algoritmosAtribuicao;

import eapli.helpdesk.AppSettings;
import eapli.helpdesk.Application;
import eapli.helpdesk.atividadeTicket.domain.AtividadeTicket;
import eapli.helpdesk.atividadeTicket.repositories.AtividadeTicketRepository;
import eapli.helpdesk.catalogo.domain.Catalogo;
import eapli.helpdesk.catalogo.repositories.CatalogoRepository;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.equipa.domain.Team;
import eapli.helpdesk.infraestrutura.PersistenceContext;
import eapli.helpdesk.servico.domain.Servico;
import eapli.helpdesk.ticket.domain.Ticket;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

/**
 * The type Algoritmos atribuicao tarefas.
 */
public class AlgoritmosAtribuicaoTarefas {

    static final AppSettings app = Application.settings();
    static final int automatic_task_assignment_option = app.getAutomaticTaskAssignmentOption();

    static final String serverIPProperties = app.getServerIpKey();
    static final int serverPortProperties = app.getServerPortKey();
    static final String keyStorePassProperties = app.getKeyStorePass();
    static InetAddress serverIP;
    static SSLSocket s;

    public AlgoritmosAtribuicaoTarefas(){

    }

    /**
     * The constant catalogoRepository.
     */
    public static final CatalogoRepository catalogoRepository = PersistenceContext.repositories().catalogo();
    /**
     * The constant atividadeTicketRepository.
     */
    public static final AtividadeTicketRepository atividadeTicketRepository = PersistenceContext.repositories().atividadesTicket();

    /**
     * First came first served collaborator.
     *
     * @param ticket the ticket
     * @return the collaborator
     */
    public Collaborator firstTaskAssignmentAlgorithm(Ticket ticket) {
        Catalogo catalogoDoServico = obtainCatalog(ticket);
        List<Team> teamList=catalogoDoServico.equipas();
        Calendar c= Calendar.getInstance();
        Collaborator colabToAssignTo = null;
        Map<Collaborator,Calendar> datasFim=new HashMap<>();
        Map<Collaborator,Calendar> datasAtribuicao=new HashMap<>();
        List<Collaborator>allCollabsFromTeam=new ArrayList<>();

        for (Team team : teamList) {
            for (Collaborator colab : team.listResponsibles()) {
                if (!allCollabsFromTeam.contains(colab)) {
                    allCollabsFromTeam.add(colab);
                }
            }
            for (Collaborator colab : team.listMembers()) {
                if (!allCollabsFromTeam.contains(colab)) {
                    allCollabsFromTeam.add(colab);
                }
            }
        }
            for (Collaborator colab : allCollabsFromTeam) {
                if (atividadeTicketRepository.manualTasksFromCollab(colab).isEmpty()) {
                    int i = 0;
                    List<AtividadeTicket> atividadeTicketList = atividadeTicketRepository.tasksDoneByCollab(colab);
                    if (atividadeTicketList.isEmpty()) {
                        return colab;
                    } else {
                        for (AtividadeTicket a : atividadeTicketList) {
                            if (a.dataFim().after(c) && i != 0) {
                                c = a.dataFim();
                            }
                            if (i == 0) {
                                c = a.dataFim();
                                i++;
                            }
                        }
                        datasFim.put(colab, c);
                    }
                }
            }
            if (!datasFim.isEmpty()) {
                int k=0;
                for (Collaborator collaborator:datasFim.keySet()){
                    if(datasFim.get(collaborator).before(c) && k!=0){
                        c=datasFim.get(collaborator);
                        colabToAssignTo=collaborator;
                    }
                    if (k==0){
                        c=datasFim.get(collaborator);
                        colabToAssignTo=collaborator;
                        k++;
                    }
                }
            } else {
                for (Collaborator colab : allCollabsFromTeam) {
                    int i = 0;
                    List<AtividadeTicket> atividadeTicketList = atividadeTicketRepository.manualTasksFromCollab(colab);
                    for (AtividadeTicket a : atividadeTicketList) {
                        if (a.dataAtribuicao() != null) {
                            if (a.dataAtribuicao().after(c) && i != 0) {
                                c = a.dataAtribuicao();
                            }
                            if (i == 0) {
                                c = a.dataAtribuicao();
                                i++;
                            }
                        }
                    }
                    if (i > 0) {
                        datasAtribuicao.put(colab, c);
                    }
                }
                int k = 0;
                for (Collaborator collaborator : datasAtribuicao.keySet()) {
                    if (datasAtribuicao.get(collaborator).before(c) && k != 0) {
                        c = datasAtribuicao.get(collaborator);
                        colabToAssignTo = collaborator;
                    }
                    if (k == 0) {
                        c = datasAtribuicao.get(collaborator);
                        colabToAssignTo = collaborator;
                        k++;
                    }
                }
            }

        return colabToAssignTo;
    }

    /**
     * Estimar a carga de trabalho de cada colaborador que possa satisfazer a tarefa em mãos e atribuir a
     * tarefa aquele que estará em condições de a realizar mais cedo. Exemplo: o colaborador C1 tem
     * 2 tarefas pendentes cujo tempo médio previsto de realização é de 10 minutos e 15 minutos respetivamente,
     * perfazendo um total de 25 minutos enquanto o colaborador C2 tem apenas 1 tarefa pendente mas cujo tempo
     * médio previsto de realização é 35 minutos. Perante este cenário a atribuição de uma nova tarefa realizável
     * por C1 e C2 deverá recair em C1 visto que apesar de ter mais tarefas pendentes conseguirá previsivelmente
     * conclui-las mais cedo do que C2 concluirá a sua tarefa.
     * @param ticket Ticket
     * @return Collaborator
     */
    public Collaborator secondTaskAssignmentAlgorithm(Ticket ticket) {

        // Obter catálogo
        Catalogo catalogoDoServico = obtainCatalog(ticket);

        if(catalogoDoServico == null) {
            System.out.println("Não existe Catálogo associado...\n");
            return null;
        }

        // Obter Equipas com acesso ao Catálogo obtido
        List<Team> teamsList = catalogoDoServico.equipas();

        if(teamsList.isEmpty()) {
            System.out.println("Não Existem Equipas com acesso ao Catálogo obtido...\n");
            return null;
        }

        // allCollaboratorsInTeams -> terá todos os colaboradores associados às equipas obtidas
        List<Collaborator> allCollaboratorsInTeams = new ArrayList<>();
        for (Team team : teamsList) {
            for (Collaborator colab : team.listResponsibles()) {
                if (!allCollaboratorsInTeams.contains(colab)) {
                    allCollaboratorsInTeams.add(colab);
                }
            }
            for (Collaborator colab : team.listMembers()) {
                if (!allCollaboratorsInTeams.contains(colab)) {
                    allCollaboratorsInTeams.add(colab);
                }
            }
        }

        if(allCollaboratorsInTeams.isEmpty()) {
            System.out.println("Não Existem Colaboradores em Equipas de momento...\n");
            return null;
        }

        Map<Collaborator, Integer> colabsTimesExecution = new HashMap<>();

        for (Collaborator colab : allCollaboratorsInTeams) {
            List<AtividadeTicket> atvColab = atividadeTicketRepository.manualTasksFromCollab(colab);

            // se não se encontra a realizar tarefa nenhuma, poderá ser atribuída a ele
            if (atvColab.isEmpty()) {
                return colab;

            } else {
                /* caso contrário iremos percorrer todas as tarefas pendentes de cada colaborador,
                   de modo a determinar uma estimativa de tempo que ainda lhe falta para terminar as mesmas */
                Integer tempo = 0;
                for (AtividadeTicket atv : atvColab) {
                    if (atv.tipoAtividade().equalsIgnoreCase("APROVACAO")) {
                        tempo += atv.ticket().servico().criticidade().tempoMedioAprovacao().tempo();
                    }
                    if (atv.tipoAtividade().equalsIgnoreCase("MANUAL")) {
                        tempo += atv.ticket().servico().criticidade().tempoMedioResolucao().tempo();
                    }
                }
                colabsTimesExecution.put(colab, tempo);
            }
        }
        /* obter Colaborador que se encontra em condições de terminar as suas tarefas pendentes mais cedo,
           ou seja, aquele colaborador que tem como value o menor tempo */
        Map.Entry<Collaborator, Integer> min = null;
        for (Map.Entry<Collaborator, Integer> entry : colabsTimesExecution.entrySet()) {
            if (min == null || min.getValue() > entry.getValue()) {
                min = entry;
            }
        }

        assert min != null;
        /* min.getKey() -> Key da Entry do Map com menor Value (Colaborador que demorará menos tempo a finalizar
           as suas tarefas pendentes */
        return min.getKey();
    }

    public Catalogo obtainCatalog(Ticket ticket) {
        Catalogo catalogoDoServico = null;
        Iterable<Catalogo> catalogoIterable = catalogoRepository.findAll();
        for(Catalogo c:catalogoIterable) {
            for(Servico s:c.servicos()) {
                if (s.identity().toString().equals(ticket.servico().identity().toString())) {
                    catalogoDoServico = c;
                }
            }
        }
        return catalogoDoServico;
    }

    public void automaticTaskAssignment(Ticket ticket, String tipoAtividade) throws IOException {
        if (automatic_task_assignment_option == 1) {
            createClientAndAssignTask(ticket, tipoAtividade, 11);
        }
        if (automatic_task_assignment_option == 2) {
            createClientAndAssignTask(ticket, tipoAtividade, 12);
        }
    }

    public void createClientAndAssignTask(Ticket ticket, String tipoAtividade, int option) throws IOException {

        boolean flag = true;

        try {
            serverIP = InetAddress.getByName(serverIPProperties);
        } catch (UnknownHostException ex) {
            throw new IllegalArgumentException("[ERROR] Problemas no servidor, tente mais tarde!");
        }

        String clientCertificate = "client" + (option - 2) + "Motor";
        // Trust these certificates provided by servers
        System.setProperty("javax.net.ssl.trustStore", clientCertificate + ".jks");
        System.setProperty("javax.net.ssl.trustStorePassword", keyStorePassProperties);

        // Use this certificate and private key for client certificate when requested by the server
        System.setProperty("javax.net.ssl.keyStore", clientCertificate + ".jks");
        System.setProperty("javax.net.ssl.keyStorePassword", keyStorePassProperties);

        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();

        try {
            s = (SSLSocket) sf.createSocket(serverIP, serverPortProperties);
        } catch (IOException ex) {
            throw new IllegalArgumentException("[ERROR] Problemas no servidor, tente mais tarde!");
        }

        try {
            s.startHandshake();
            DataOutputStream sOutData = new DataOutputStream(s.getOutputStream());
            DataInputStream sInData = new DataInputStream(s.getInputStream());

            //Mandar um pedido para o servido -> codigo: 0 (Teste)
            byte[] clienteMessage = {(byte) 0, (byte) 0, (byte) 0, (byte) 0};
            sOutData.write(clienteMessage);
            sOutData.flush();

            //Esperar a resposta do servidor a dizer que entendeu a mensagem
            byte[] serverMessage = sInData.readNBytes(4);
            if (serverMessage[1] == 2) {

                //Manda para o servidor a opção para ele saber o que retornar
                byte[] clienteMessageOption = {(byte) 0, (byte) option, (byte) 0, (byte) 0};
                sOutData.write(clienteMessageOption);
                sOutData.flush();

                //Esperar a resposta do servidor a dizer que entendeu a mensagem
                byte[] serverMessageOption = sInData.readNBytes(4);
                if (serverMessageOption[1] == 2) {

                    ObjectOutputStream sOutObject = new ObjectOutputStream(s.getOutputStream());

                    sOutObject.writeObject(ticket);
                    sOutObject.flush();
                    sOutObject.writeObject(tipoAtividade);
                    sOutObject.flush();

                    //Mandar um pedido para o servido -> codigo: 1 (Fim)
                    byte[] clienteMessageEnd = {(byte) 0, (byte) 1, (byte) 0, (byte) 0};
                    sOutData.write(clienteMessageEnd);
                    sOutData.flush();

                    byte[] serverMessageEnd = sInData.readNBytes(4);
                    if (serverMessageEnd[1] == 2) {
                        s.close();
                        flag = false;
                    } else {
                        s.close();
                        flag = false;
                    }
                } else {
                    throw new IllegalArgumentException("[ERROR] Falha durante a busca de informação, tente novamente mais tarde!");
                }
            } else {
                throw new IllegalArgumentException("[ERROR] Falha durante a busca de informação, tente novamente mais tarde!");
            }
        } catch (IOException ex) {
            throw new IllegalArgumentException("[ERROR] Falha durante a busca de informação, tente novamente mais tarde!");
        } finally {
            if (flag) {
                s.close();
            }
        }
    }
}
