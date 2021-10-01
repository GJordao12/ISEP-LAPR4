package eapli.helpdesk.ticket.service;

import eapli.helpdesk.AppSettings;
import eapli.helpdesk.Application;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.ticket.dto.TicketDTO;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListTicketsService {

    static final AppSettings app = Application.settings();
    static final String serverIPProperties = app.getServerIpKey();
    static final int serverPortProperties = app.getServerPortKey();
    static final String keyStorePassProperties = app.getKeyStorePass();
    static InetAddress serverIP;
    static SSLSocket s;

    public List<TicketDTO> createClientAndRequestTickets(Collaborator collab, int option) throws IOException {

        boolean flag = true;
        List<TicketDTO> ticketsList;

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

                    ticketsList = (List<TicketDTO>) sInObject.readObject();

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
        return ticketsList;
    }

    public void createClientAndRequestFeedbackTicket(TicketDTO ticketDTO, int classification, int option) throws IOException {

        boolean flag = true;

        String clientCertificate = "client" + (option - 2) + "Motor";
        // Trust these certificates provided by servers
        System.setProperty("javax.net.ssl.trustStore", clientCertificate + ".jks");
        System.setProperty("javax.net.ssl.trustStorePassword", keyStorePassProperties);

        // Use this certificate and private key for client certificate when requested by the server
        System.setProperty("javax.net.ssl.keyStore", clientCertificate + ".jks");
        System.setProperty("javax.net.ssl.keyStorePassword", keyStorePassProperties);

        SSLSocketFactory sf = (SSLSocketFactory) SSLSocketFactory.getDefault();

        try {
            serverIP = InetAddress.getByName(serverIPProperties);
        } catch (UnknownHostException ex) {
            throw new IllegalArgumentException("[ERROR] Problemas no servidor, tente mais tarde!");
        }

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

                    sOutObject.writeObject(ticketDTO);
                    sOutObject.flush();

                    sOutObject.write(classification);
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
                    throw new IllegalArgumentException("[ERROR] Falha a guardar o seu Feedback, tente mais tarde!");
                }
            } else {
                throw new IllegalArgumentException("[ERROR] Falha a guardar o seu Feedback, tente mais tarde!");
            }
        } catch (IOException ex) {
            throw new IllegalArgumentException("[ERROR] Falha a guardar o seu Feedback, tente mais tarde!");
        } finally {
            if (flag) {
                s.close();
            }
        }
    }

    public List<TicketDTO> orderByRequestDate(List<TicketDTO> tickets) {
        List<TicketDTO> list = new ArrayList<>(tickets);
        list.sort(Comparator.comparing((TicketDTO x) -> x.dataSolicitacao));
        return list;
    }
}