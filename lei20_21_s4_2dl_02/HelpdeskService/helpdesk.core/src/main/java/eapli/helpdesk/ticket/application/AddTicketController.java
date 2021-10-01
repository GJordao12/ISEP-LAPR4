package eapli.helpdesk.ticket.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.helpdesk.atividadeTicket.domain.AtividadeTicket;
import eapli.helpdesk.atividadeTicket.repositories.AtividadeTicketRepository;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.servico.domain.FormularioPreenchido;
import eapli.helpdesk.servico.domain.Servico;
import eapli.helpdesk.ticket.domain.Ticket;
import eapli.helpdesk.ticket.exceptions.FormularioPreenchidoException;
import eapli.helpdesk.ticket.repositories.FormularioPreenchidoRepository;
import eapli.helpdesk.ticket.repositories.TicketRepository;
import eapli.helpdesk.infraestrutura.PersistenceContext;
import eapli.helpdesk.user.domain.HelpdeskRoles;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Calendar;


public class AddTicketController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final TicketRepository ticketRepository = PersistenceContext.repositories().tickets();
    private final AtividadeTicketRepository atividadeTicketRepository =PersistenceContext.repositories().atividadesTicket();


    public FormularioPreenchido registerFormularioPreenchido(FormularioPreenchido form) throws FormularioPreenchidoException {
        return new FormularioPreenchido(form);
    }

    public Ticket registerTicket(Servico servico, Calendar date, int prioridade, FormularioPreenchido form, Collaborator colab) throws FormularioPreenchidoException {
        authz.ensureAuthenticatedUserHasAnyOf(HelpdeskRoles.SERVICE_MANAGER,HelpdeskRoles.POWER_USER, HelpdeskRoles.HR_MANAGER);
        FormularioPreenchido formulario = registerFormularioPreenchido(form);
        final Ticket ticket = new Ticket(servico,date,prioridade,formulario,colab);
        return this.ticketRepository.save(ticket);
    }

    public void registerTicket1(Servico servico, Calendar date, int prioridade, FormularioPreenchido form, Collaborator colab) throws FormularioPreenchidoException {
        authz.ensureAuthenticatedUserHasAnyOf(HelpdeskRoles.SERVICE_MANAGER,HelpdeskRoles.POWER_USER, HelpdeskRoles.HR_MANAGER);
        FormularioPreenchido formulario = registerFormularioPreenchido(form);
        final Ticket ticket = new Ticket(servico,date,prioridade,formulario,colab);
        ticket.changeEstado("Resolvido");
        this.ticketRepository.save(ticket);
    }
    
    public void executarTarefaAutomatica(AtividadeTicket atividadeAutomatica,Ticket ticket){
        InetAddress serverIP = null;
        Socket s = null;

            try {
                serverIP = InetAddress.getByName("10.9.21.109");
            } catch (UnknownHostException ex) {
                System.out.println("Invalid server specified ");
                System.exit(1);
            }

            try {
                s = new Socket(serverIP, 2020);
            } catch (IOException ex) {
                System.out.println("Failed to establish TCP connection");
                System.exit(1);
            }

            try {

                DataOutputStream sOutData = new DataOutputStream(s.getOutputStream());
                DataInputStream sInData = new DataInputStream(s.getInputStream());

                //Mandar um pedido para o servido -> codigo: 0 (Teste)
                byte[] clienteMessage = {(byte) 0, (byte) 0, (byte) 0, (byte) 0};
                sOutData.write(clienteMessage);
                sOutData.flush();

                //Esperar a resposta do servidor a dizer que entendeu a mensagem
                byte[] serverMessage = sInData.readNBytes(4);
                if (serverMessage[1] == 2) {

                    ObjectOutputStream sOutObject = new ObjectOutputStream(s.getOutputStream());
                    ObjectInputStream sInObject = new ObjectInputStream(s.getInputStream());


                    sOutObject.writeObject(atividadeAutomatica);
                    sOutObject.flush();

                    AtividadeTicket atAtualizado = (AtividadeTicket) sInObject.readObject();

                    atividadeTicketRepository.save(atAtualizado);
                    ticket.changeEstado("Resolvido");
                    ticketRepository.save(ticket);

                    //Mandar um pedido para o servido -> codigo: 0 (Teste)
                    byte[] clienteMessageEnd = {(byte) 0, (byte) 1, (byte) 0, (byte) 0};
                    sOutData.write(clienteMessageEnd);
                    sOutData.flush();

                    byte[] serverMessageEnd = sInData.readNBytes(4);
                    if (serverMessageEnd[1] == 2) {
                        s.close();
                    } else {
                        System.out.println("==> ERROR: Erro no pacote do Servidor");
                    }
                } else {
                    System.out.println("==> ERROR: Erro no pacote do Servidor");
                }
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
                System.out.println("==> ERROR: Falha durante a troca de informação com o server");
            }
        }
    }

