package eapli.helpdesk.app.MotorDeFluxos.TcpMotor;

import eapli.framework.domain.repositories.TransactionalContext;
import eapli.helpdesk.AppSettings;
import eapli.helpdesk.Application;
import eapli.helpdesk.algoritmosAtribuicao.AlgoritmosAtribuicaoTarefas;
import eapli.helpdesk.atividadeTicket.dto.AtividadeTicketDTOParser;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.atividadeTicket.domain.AtividadeTicket;
import eapli.helpdesk.atividadeTicket.dto.AtividadeTicketDTO;
import eapli.helpdesk.atividadeTicket.repositories.AtividadeTicketRepository;
import eapli.helpdesk.feedback.exception.ClassificacaoException;
import eapli.helpdesk.feedback.repository.FeedbackRepository;
import eapli.helpdesk.infraestrutura.PersistenceContext;
import eapli.helpdesk.servico.domain.FormularioPreenchido;
import eapli.helpdesk.sla.domain.SLA;
import eapli.helpdesk.sla.repositories.SLARepository;
import eapli.helpdesk.ticket.domain.Ticket;
import eapli.helpdesk.ticket.dto.TicketDTO;
import eapli.helpdesk.ticket.dto.TicketDTOParser;
import eapli.helpdesk.ticket.repositories.FormularioPreenchidoRepository;
import eapli.helpdesk.ticket.repositories.TicketRepository;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

class TcpSrvMotorDeFluxos {

    static final String TRUSTED_STORE = "serverMotorFluxos.jks";
    static final String KEYSTORE_PASS = "forgotten";
    static SSLServerSocket sock;
    static int serverPortProperties;
    static Calendar c;

    public static void main(String[] args) throws Exception {

        c = Calendar.getInstance();

        // Trust these certificates provided by authorized clients
        System.setProperty("javax.net.ssl.trustStore", TRUSTED_STORE);
        System.setProperty("javax.net.ssl.trustStorePassword", KEYSTORE_PASS);

        // Use this certificate and private key as server certificate
        System.setProperty("javax.net.ssl.keyStore", TRUSTED_STORE);
        System.setProperty("javax.net.ssl.keyStorePassword", KEYSTORE_PASS);

        SSLServerSocketFactory sslF = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        Socket cliSock;
        try {
            sock = (SSLServerSocket) sslF.createServerSocket(serverPortProperties);
            sock.setNeedClientAuth(true);
        } catch (IOException ex) {
            System.out.println("Failed to open server socket");
            System.exit(1);
        }

        while (true) {
            cliSock = sock.accept();
            new Thread(new TcpSrvMotorDeFluxosThread(cliSock,c)).start();
        }
    }
}


class TcpSrvMotorDeFluxosThread implements Runnable {

    private Socket s;
    private List<AtividadeTicketDTO> at;
    private List<TicketDTO> ticket;

    private final TransactionalContext ctx = PersistenceContext.repositories().newTransactionalContext();
    private final AtividadeTicketRepository repoAtividadeTicket = PersistenceContext.repositories().atividadesTicket();
    private final AtividadeTicketRepository repoAtividadeTicketCTX = PersistenceContext.repositories().atividadesTicket(ctx);
    private final AtividadeTicketDTOParser atividadeTicketDTOParser = new AtividadeTicketDTOParser();
    private final TicketRepository ticketRepository = PersistenceContext.repositories().tickets();
    private final TicketRepository ticketRepositoryCTX = PersistenceContext.repositories().tickets(ctx);
    private final FeedbackRepository feedbackRepository=PersistenceContext.repositories().feedbacks();
    private final SLARepository SLARepository = PersistenceContext.repositories().SLAs();
    private final SLARepository SLARepositoryCTX = PersistenceContext.repositories().SLAs(ctx);
    private final AlgoritmosAtribuicaoTarefas algoritmosAtribuicaoTarefas=new AlgoritmosAtribuicaoTarefas();

    private Calendar c;

    private final FormularioPreenchidoRepository formRepository = PersistenceContext.repositories().formPreenchido();
    private final FormularioPreenchidoRepository formRepositoryCTX = PersistenceContext.repositories().formPreenchido(ctx);

    public TcpSrvMotorDeFluxosThread(Socket cli_s,Calendar c) {
        this.s = cli_s;
        this.c = c;
    }

    public void run() {
        InetAddress clientIP;

        try {
            clientIP = this.s.getInetAddress();
            System.out.println("==> New client connection from " + clientIP.getHostAddress() + ", port number " + this.s.getPort());

            DataInputStream sIn = new DataInputStream(this.s.getInputStream());
            DataOutputStream sOut = new DataOutputStream(this.s.getOutputStream());
            byte[] clienteMessage = sIn.readNBytes(4);
            if (clienteMessage[1] == 0) {
                System.out.println("==> Pedido de Teste do cliente recebido com Sucesso");

                //Dizer ao cliente que entendeu
                System.out.println("==> Mandar mensagem ao cliente a dizer que entendeu");
                byte[] serverMessage = {(byte) 0, (byte) 2, (byte) 0, (byte) 0};
                sOut.write(serverMessage);
                sOut.flush();

                byte[] clienteMessageOpt = sIn.readNBytes(4);
                int option = clienteMessageOpt[1];

                sOut.write(serverMessage);
                sOut.flush();

                ObjectInputStream sInputObject = new ObjectInputStream(this.s.getInputStream());
                ObjectOutputStream sOutputObject = new ObjectOutputStream(this.s.getOutputStream());


                if ((option >=3 && option <=8) || option==10) {
                    Collaborator collab = (Collaborator) sInputObject.readObject();
                    System.out.println("==> Colaborador logado: " + collab.toString());

                    if (option == 4) {
                        this.at = allPendingTasks(collab);
                        sOutputObject.writeObject(at);
                        sOutputObject.flush();

                        for (AtividadeTicketDTO atividadeTicketDTO : at) {
                            System.out.println("==> AtividadeTicket enviada: " + atividadeTicketDTO.toString());
                        }
                    }
                    if (option == 3) {
                        this.at = allPendingAccessTasks(collab);
                        sOutputObject.writeObject(at);
                        sOutputObject.flush();

                        for (AtividadeTicketDTO atividadeTicketDTO : at) {
                            System.out.println("==> AtividadeTicket enviada: " + atividadeTicketDTO.toString());
                        }
                    }
                    if (option == 5) {
                        AtividadeTicketDTO atividadeTicket = (AtividadeTicketDTO) sInputObject.readObject();
                        assignTask(atividadeTicket, collab);
                    }
                    if (option == 6) {
                        this.ticket = onGoingTicketsByCollab(collab);
                        sOutputObject.writeObject(ticket);
                        sOutputObject.flush();
                    }
                    if (option == 7) {
                        this.ticket = completedTicketsByCollab(collab);
                        sOutputObject.writeObject(ticket);
                        sOutputObject.flush();
                    }
                    if (option == 8) {
                        ticket = allFinishTicketsWithFeedbackAvailable(collab);
                        sOutputObject.writeObject(ticket);
                        sOutputObject.flush();
                    }
                    if (option == 10) {
                        this.at = showTasksPossibleToBeDone(collab);
                        sOutputObject.writeObject(at);
                        sOutputObject.flush();
                    }
                }
                if (option == 9) {
                    TicketDTO ticketDTO=(TicketDTO) sInputObject.readObject();
                    Integer classificacao=sInputObject.read();
                    saveFeedback(ticketDTO,classificacao);
                }

                if (option == 11) {
                    Ticket ticket = (Ticket) sInputObject.readObject();
                    String tipoAtividade = (String) sInputObject.readObject();
                    Collaborator colab = algoritmosAtribuicaoTarefas.firstTaskAssignmentAlgorithm(ticket);
                    Calendar c = Calendar.getInstance();
                    AtividadeTicket atividadeTicket = new AtividadeTicket(ticket, colab,tipoAtividade,null,null, c);
                    repoAtividadeTicket.save(atividadeTicket);
                }

                if (option == 12) {
                    Ticket ticket = (Ticket) sInputObject.readObject();
                    String tipoAtividade = (String) sInputObject.readObject();
                    Collaborator colab = algoritmosAtribuicaoTarefas.secondTaskAssignmentAlgorithm(ticket);
                    Calendar c = Calendar.getInstance();
                    AtividadeTicket atividadeTicket = new AtividadeTicket(ticket, colab,tipoAtividade,null,null, c);
                    repoAtividadeTicket.save(atividadeTicket);
                }

                if (option==13 || option ==15 ){
                    ctx.beginTransaction();
                    FormularioPreenchido formularioPreenchido=(FormularioPreenchido) sInputObject.readObject();
                    formRepositoryCTX.save(formularioPreenchido);
                    Calendar c=(Calendar) sInputObject.readObject();
                    Calendar c1=(Calendar) sInputObject.readObject();
                    AtividadeTicket at=(AtividadeTicket) sInputObject.readObject();
                    at.changeDataInicio(c);
                    at.changeDataFim(c1);
                    repoAtividadeTicketCTX.save(at);
                    if (option ==13){
                        at.ticket().changeEstado("APROVADO");
                    } else {
                        at.ticket().changeEstado("RESOLVIDO");
                    }
                    ticketRepositoryCTX.save(at.ticket());

                    double difference_In_Time = c1.getTime().getTime() - c.getTime().getTime();
                    double difference_In_Minutes = (difference_In_Time / (1000 * 60)) % 60;

                    if (option ==13){
                        if(difference_In_Minutes <= at.ticket().servico().criticidade().tempoMedioAprovacao().tempo()) {
                            SLA sla = new SLA(at.ticket(), at.ticket().servico(), at.tipoAtividade(), "Cumpre os objetivos temporais", difference_In_Minutes);
                            SLARepositoryCTX.save(sla);
                        } else {
                            SLA sla = new SLA(at.ticket(), at.ticket().servico(), at.tipoAtividade(), "Não cumpre os objetivos temporais", difference_In_Minutes);
                            SLARepositoryCTX.save(sla);
                        }
                    } else {
                        if(difference_In_Minutes <= at.ticket().servico().criticidade().tempoMedioResolucao().tempo()) {
                            SLA sla = new SLA(at.ticket(), at.ticket().servico(), at.tipoAtividade(), "Cumpre os objetivos temporais", difference_In_Minutes);
                            SLARepositoryCTX.save(sla);
                        } else {
                            SLA sla = new SLA(at.ticket(), at.ticket().servico(), at.tipoAtividade(), "Não cumpre os objetivos temporais", difference_In_Minutes);
                            SLARepositoryCTX.save(sla);
                        }
                    }
                    ctx.commit();
                }

                if (option==14){
                    Ticket ticket=(Ticket) sInputObject.readObject();
                    ticket.changeEstado("REJEITADO");
                    ticketRepository.save(ticket);
                }

                if(option==16){
                    sOutputObject.writeObject(c);
                    sOutputObject.flush();
                }

                if(option==17){
                    Iterable<Ticket> tList = ticketRepository.findAll();
                    sOutputObject.writeObject(tList);
                    sOutputObject.flush();
                }

                byte[] clienteMessageEnd = sIn.readNBytes(4);
                if (clienteMessageEnd[1] == 1) {
                    System.out.println("==> Pedido de Fim do Cliente recebido com Sucesso");
                    //Dizer ao cliente que entendeu
                    System.out.println("==> Mandar mensagem ao cliente para fechar socket");
                    byte[] serverMessageEnd = {(byte) 0, (byte) 2, (byte) 0, (byte) 0};
                    sOut.write(serverMessageEnd);
                    sOut.flush();
                    System.out.println("==> Client " + clientIP.getHostAddress() + ", port number: " + this.s.getPort() + " disconnected");

                } else {
                    System.out.println("==> ERROR: Erro no pacote do Cliente");
                }
            } else {
                System.out.println("==> ERROR: Erro no pacote do Cliente");
            }
        } catch (IOException | ClassNotFoundException | ClassificacaoException e) {
            System.out.println("==> ERROR: ");
        } finally {
            try {
                this.s.close();
            } catch (IOException e) {
                System.out.println("ERROR: Erro ao fechar o Socket");
            }
            System.out.println("==> INFO: Socket fechado com Sucesso\n\n");
        }
    }


    public List<AtividadeTicketDTO> allPendingTasks(Collaborator collab) {

        List<AtividadeTicket> allPendingTasks = this.repoAtividadeTicket.allPendingTasks(collab);
        for (AtividadeTicket at : allPendingTasks) {
            at.changeCollaboratorTo(collab);
        }
        List<AtividadeTicketDTO> allPendingTasksDTO = new ArrayList<>();
        allPendingTasks.forEach(atividade -> allPendingTasksDTO.add(atividade.toDTO()));

        return allPendingTasksDTO;
    }

    public List<AtividadeTicketDTO> allPendingAccessTasks(Collaborator collab) {
        List<AtividadeTicket> atividadeTickets = this.repoAtividadeTicket.allPendingAccessTasks(collab);

        List<AtividadeTicketDTO> atividadeTicketDTOList = new ArrayList<>();
        for (AtividadeTicket at : atividadeTickets) {
            at.changeCollaboratorTo(collab);
        }
        atividadeTickets.forEach(atividade -> atividadeTicketDTOList.add(atividade.toDTO()));
        return atividadeTicketDTOList;
    }

    public List<TicketDTO> onGoingTicketsByCollab(Collaborator collab) {
        List<Ticket> tickets = this.ticketRepository.onGoingTicketsByCollab(collab);

        List<TicketDTO> ticketDTOList = new ArrayList<>();

        tickets.forEach(ticket -> ticketDTOList.add(ticket.toDTO()));
        return ticketDTOList;
    }

    public List<TicketDTO> completedTicketsByCollab(Collaborator collab) {
        List<Ticket> tickets = this.ticketRepository.completedTicketsByCollab(collab);

        List<TicketDTO> ticketDTOList = new ArrayList<>();

        tickets.forEach(ticket -> ticketDTOList.add(ticket.toDTO()));
        return ticketDTOList;
    }

    public void assignTask(AtividadeTicketDTO atividadeTicketDTO, Collaborator collab) {
        AtividadeTicket atividadeTicket = atividadeTicketDTOParser.valueOf(atividadeTicketDTO);
        atividadeTicket.updateDataAtribuicao(Calendar.getInstance());
        this.repoAtividadeTicket.assignTask(atividadeTicket, collab);
    }

    public List<AtividadeTicketDTO> showTasksPossibleToBeDone(Collaborator collab) {
        List<AtividadeTicket> allTasksPossibleToBeDone = this.repoAtividadeTicket.showTasksPossibleToBeDone(collab);
        for (AtividadeTicket at : allTasksPossibleToBeDone) {
            at.changeCollaboratorTo(collab);
        }
        List<AtividadeTicketDTO> allTasksDTO = new ArrayList<>();
        allTasksPossibleToBeDone.forEach(atividade -> allTasksDTO.add(atividade.toDTO()));

        return allTasksDTO;
    }

    public List<TicketDTO> allFinishTicketsWithFeedbackAvailable(Collaborator collab) {
        List<Ticket> tickets = this.feedbackRepository.allFinishTicketsWithFeedbackAvailable(collab);

        List<TicketDTO> ticketDTOList = new ArrayList<>();

        tickets.forEach(ticket -> ticketDTOList.add(ticket.toDTO()));
        return ticketDTOList;
    }

    public void saveFeedback(TicketDTO ticketDTO,Integer classificacao) throws ClassificacaoException {
        TicketDTOParser ticketDTOParser=new TicketDTOParser();
        Ticket ticket=ticketDTOParser.valueOf(ticketDTO);
        this.feedbackRepository.saveFeedback(ticket,classificacao);
    }
}

