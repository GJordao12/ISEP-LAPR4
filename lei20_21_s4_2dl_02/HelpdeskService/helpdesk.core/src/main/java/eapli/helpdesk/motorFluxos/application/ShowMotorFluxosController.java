package eapli.helpdesk.motorFluxos.application;

import eapli.helpdesk.AppSettings;
import eapli.helpdesk.Application;

import eapli.helpdesk.motorFluxos.domain.HttpServerAjaxVoting;
import eapli.helpdesk.ticket.domain.Ticket;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ShowMotorFluxosController {


    private Iterable<Ticket> tList;

    static final AppSettings app = Application.settings();
    static final String serverIPProperties = app.getServerIpKey();
    static final int serverPortProperties = app.getServerPortKey();
    static final String keyStorePassProperties = app.getKeyStorePass();
    static InetAddress serverIP;
    static SSLSocket s;
    Calendar c;

    public Calendar createClientAndRequestTime(int option) throws IOException {

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
                    ObjectInputStream sInObject = new ObjectInputStream(s.getInputStream());

                    c = (Calendar) sInObject.readObject();

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
        return c;
    }

    public Iterable<Ticket> createClientAndRequestTickets(int option) throws IOException {

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
                    ObjectInputStream sInObject = new ObjectInputStream(s.getInputStream());

                    tList = (Iterable<Ticket>) sInObject.readObject();

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
        return tList;
    }

    public void showMotorFluxos() {
        HttpServerAjaxVoting server = new HttpServerAjaxVoting(c,tList);
        server.changeController(this);
        server.start();

    }

    public List<Integer> createdTickets(List<Ticket> tList) {
        List<Integer> list = new ArrayList<>();
        int cont = 0;
        for (Ticket t:tList) {
            if(t.dataSolicitacao().after(c)){
                cont++;
            }
        }
        confirmList(cont,list,tList);
            return list;
    }

    public List<Integer> createdCompletedTickets(List<Ticket> tList) {
        List<Integer> list = new ArrayList<>();
        int cont = 0;
        for (Ticket t:tList) {
            if(t.dataSolicitacao().after(c)&& t.estado().equalsIgnoreCase("resolvido")){
                cont++;
            }
        }
        confirmList(cont,list,tList);
        return list;
    }

    public List<Integer> createdApprovalTickets(List<Ticket> tList) {
        List<Integer> list = new ArrayList<>();
        int cont = 0;
        for (Ticket t:tList) {
            if(t.dataSolicitacao().after(c)&& t.estado().equalsIgnoreCase("submetido") && t.servico().workflow().atividadeManualAprovacao()!=null){
                cont++;
            }
        }
        confirmList(cont,list,tList);
        return list;
    }

    public List<Integer> createdTicketsForRealization(List<Ticket> tList) {
        List<Integer> list = new ArrayList<>();
        int cont = 0;
        for (Ticket t:tList) {
            if((t.dataSolicitacao().after(c)&& t.estado().equalsIgnoreCase("submetido") && t.servico().workflow().atividadeManualAprovacao()==null)
                    ||(t.dataSolicitacao().after(c)&& t.estado().equalsIgnoreCase("aprovado") && t.servico().workflow().atividadeManualAprovacao()!=null)){
                cont++;
            }
        }
        confirmList(cont,list,tList);
        return list;
    }

    private List<Integer> confirmList(int cont,List<Integer> list,List<Ticket> tList){
        if(cont!=0&&tList.size()>0) {
            int perc =(int)( ((double)cont / tList.size()) * 100);
            list.add(cont);
            list.add(perc);

        }else{
            list.add(0);
            list.add(0);
        }
        return list;
    }

    public Calendar metodo1() throws IOException {
        this.c = createClientAndRequestTime(16);

        return this.c;
    }

    public Iterable<Ticket> metodo2() throws IOException {

            this.tList = createClientAndRequestTickets(17);

        return this.tList;
    }

}
