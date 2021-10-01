package eapli.helpdesk.atividadeTicket.service;

import eapli.helpdesk.AppSettings;
import eapli.helpdesk.Application;
import eapli.helpdesk.atividadeTicket.domain.AtividadeTicket;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.atividadeTicket.dto.AtividadeTicketDTO;
import eapli.helpdesk.servico.domain.FormularioPreenchido;
import eapli.helpdesk.ticket.domain.Ticket;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

public class ListAtividadeTicketService {

    static final AppSettings app = Application.settings();
    static final String serverIPProperties = app.getServerIpKey();
    static final int serverPortProperties = app.getServerPortKey();
    static final String keyStorePassProperties = app.getKeyStorePass();
    static InetAddress serverIP;
    static SSLSocket s;

    public List<AtividadeTicketDTO> createClientAndRequestTasks(Collaborator collab, int option) throws IOException {

        boolean flag = true;
        List<AtividadeTicketDTO> atvList;

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
                    ObjectInputStream sInObject = new ObjectInputStream(s.getInputStream());

                    sOutObject.writeObject(collab);
                    sOutObject.flush();

                    atvList = (List<AtividadeTicketDTO>) sInObject.readObject();

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
        } catch (IOException | ClassNotFoundException ex) {
            throw new IllegalArgumentException("[ERROR] Falha durante a busca de informação, tente novamente mais tarde!");
        } finally {
            if (flag) {
                s.close();
            }
        }
        return atvList;
    }

    public void createClientAndRequestAssignTask(Collaborator collab, int option, AtividadeTicketDTO atividadeTicketDTO) throws IOException {

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

                    sOutObject.writeObject(collab);
                    sOutObject.flush();

                    sOutObject.writeObject(atividadeTicketDTO);
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
                    throw new IllegalArgumentException("[ERROR] Falha a Reivindicar a Tarefa, tente mais tarde!");
                }
            } else {
                throw new IllegalArgumentException("[ERROR] Falha a Reivindicar a Tarefa, tente mais tarde!");
            }
        } catch (IOException ex) {
            throw new IllegalArgumentException("[ERROR] Falha a Reivindicar a Tarefa, tente mais tarde!");
        } finally {
            if (flag) {
                s.close();
            }
        }
    }

    public void createClientAndSaveTaskInfo(int option, FormularioPreenchido formulario, Calendar c, Calendar c1, AtividadeTicket at) throws IOException {
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

                    sOutObject.writeObject(formulario);
                    sOutObject.flush();

                    sOutObject.writeObject(c);
                    sOutObject.flush();

                    sOutObject.writeObject(c1);
                    sOutObject.flush();

                    sOutObject.writeObject(at);
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
                    throw new IllegalArgumentException("[ERROR] Falha a Reivindicar a Tarefa, tente mais tarde!");
                }
            } else {
                throw new IllegalArgumentException("[ERROR] Falha a Reivindicar a Tarefa, tente mais tarde!");
            }
        } catch (IOException ex) {
            throw new IllegalArgumentException("[ERROR] Falha a Reivindicar a Tarefa, tente mais tarde!");
        } finally {
            if (flag) {
                s.close();
            }
        }
    }

    public void createClientAndSaveTaskInfo2(int option , Ticket t) throws IOException {
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

                    sOutObject.writeObject(t);
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
                    throw new IllegalArgumentException("[ERROR] Falha a Reivindicar a Tarefa, tente mais tarde!");
                }
            } else {
                throw new IllegalArgumentException("[ERROR] Falha a Reivindicar a Tarefa, tente mais tarde!");
            }
        } catch (IOException ex) {
            throw new IllegalArgumentException("[ERROR] Falha a Reivindicar a Tarefa, tente mais tarde!");
        } finally {
            if (flag) {
                s.close();
            }
        }
    }

    public List<AtividadeTicketDTO> orderByPriority(List<AtividadeTicketDTO> tasks) {
        List<AtividadeTicketDTO> list = new ArrayList<>(tasks);
        list.sort(Comparator.comparing((AtividadeTicketDTO x) -> x.ticketDTO.prioridade));
        return list;
    }

    public List<AtividadeTicketDTO> orderByCriticality(List<AtividadeTicketDTO> tasks) {
        List<AtividadeTicketDTO> list = new ArrayList<>(tasks);
        list.sort(Comparator.comparing((AtividadeTicketDTO x) -> x.ticketDTO.serviceDTO.critDTO.valor));
        return list;
    }

    public List<AtividadeTicketDTO> orderByDeadline(List<AtividadeTicketDTO> tasks) {
        List<AtividadeTicketDTO> list = new ArrayList<>(tasks);
        list.sort(Comparator.comparing((AtividadeTicketDTO x) -> x.ticketDTO.dataLimite));
        return list;
    }

    public Iterable<AtividadeTicketDTO> filterByPriority(List<AtividadeTicketDTO> tasks, int aux) {
        List<AtividadeTicketDTO> tasksAux = new ArrayList<>();
        for (AtividadeTicketDTO t : tasks) {
            if (t.ticketDTO.prioridade == aux) {
                tasksAux.add(t);
            }
        }
        return tasksAux;
    }

    public Iterable<AtividadeTicketDTO> filterByCriticality(Iterable<AtividadeTicketDTO> tasks, int criticality) {
        List<AtividadeTicketDTO> tasksAux = new ArrayList<>();
        for (AtividadeTicketDTO t : tasks) {
            if (Integer.parseInt(String.valueOf(t.ticketDTO.serviceDTO.critDTO.valor)) == criticality) {
                tasksAux.add(t);
            }
        }
        return tasksAux;
    }

    public Iterable<AtividadeTicketDTO> filterByDeadline(Iterable<AtividadeTicketDTO> tasks, Calendar deadline) {
        List<AtividadeTicketDTO> tasksAux = new ArrayList<>();
        for (AtividadeTicketDTO t : tasks) {
            if (t.ticketDTO.dataLimite == deadline) {
                tasksAux.add(t);
            }
        }
        return tasksAux;
    }
}
