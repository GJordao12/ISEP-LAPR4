package eapli.helpdesk.app.executorTarefasAutomaticas;

import eapli.helpdesk.app.executorTarefasAutomaticas.automaticsTaskScript.AutomaticTaskScriptGrammar;
import eapli.helpdesk.AppSettings;
import eapli.helpdesk.Application;
import eapli.helpdesk.atividadeTicket.domain.AtividadeTicket;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.*;
import java.net.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Stack;

class TcpSrvAutomaticTask {

    static AppSettings app = Application.settings();
    static final int serverPortProperties = app.getServerPortKey();
    static final String trustedStoreProperties = app.getTrustedStore();
    static final String keyStorePassProperties = app.getKeyStorePass();

    public static void main(String[] args) throws IOException {

        SSLServerSocket sock = null;
        Socket cliSock;

        //Trust these certificates provided by authorized clients
        System.setProperty("javax.net.ssl.trustStore", trustedStoreProperties);
        System.setProperty("javax.net.ssl.trustStorePassword", keyStorePassProperties);

        //Use this certificate and private key as server certificate
        System.setProperty("javax.net.ssl.keyStore", trustedStoreProperties);
        System.setProperty("javax.net.ssl.keyStorePassword", keyStorePassProperties);

        SSLServerSocketFactory sslF = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        try {
            sock = (SSLServerSocket) sslF.createServerSocket(serverPortProperties);
            sock.setNeedClientAuth(true);
        } catch (IOException ex) {
            System.out.println("Server failed to open local port " + serverPortProperties);
            System.exit(1);
        }

        while (true) {
            cliSock = sock.accept();
            new Thread(new TcpSrvAutomaticTaskThread(cliSock)).start();
        }
    }
}

class TcpSrvAutomaticTaskThread implements Runnable {

    private final Socket s;
    private AtividadeTicket at;

    public TcpSrvAutomaticTaskThread(Socket cli_s) {
        this.s = cli_s;
    }

    public void run() {
        InetAddress clientIP;

        try {
            clientIP = this.s.getInetAddress();
            System.out.println("[INFO] Nova conexao de cliente: " + clientIP.getHostAddress() + ", porta: " + this.s.getPort() + ".");

            DataInputStream sIn = new DataInputStream(this.s.getInputStream());
            DataOutputStream sOut = new DataOutputStream(this.s.getOutputStream());

            byte[] clientMessage = sIn.readNBytes(4);

            if (clientMessage[1] == 0) {
                System.out.println("[SUCCESS] Codigo de Teste (0) do Cliente recebido.");

                byte[] serverMessage = {(byte) 0, (byte) 2, (byte) 0, (byte) 0};
                System.out.println("[INFO] A Mandar Codigo de Entendido (2) ao Cliente.");
                sOut.write(serverMessage);
                sOut.flush();

                ObjectInputStream sInputObject = new ObjectInputStream(this.s.getInputStream());
                ObjectOutputStream sOutputObject = new ObjectOutputStream(this.s.getOutputStream());

                this.at = (AtividadeTicket) sInputObject.readObject();
                System.out.println("[SUCCESS] Tarefa Automatica recebida.");
                System.out.println("[INFO] ID da Tarefa Automatica recebida: " + this.at.identity() + ".");

                Date dataInicio = new Date();
                Calendar calInicio = Calendar.getInstance();
                calInicio.setTime(dataInicio);

                this.at.defineDataInicio(calInicio);

                System.out.println("[INFO] Data de Inicio Atualizada da Atividade Automatica com ID: " + this.at.identity() + " para " + calInicio.getTime() + ".");

                System.out.println("[INFO] A Comecar a Execucao da Tarefa Automatica com ID: " + this.at.identity() + ".");
                String script = this.at.ticket().servico().workflow().atividadeAutomatica().script().script().getPath();
                System.out.println("[INFO] A Executar o Script: " + script + ".");

                if (AutomaticTaskScriptGrammar.parseWithListener(script, this.at.ticket())) {
                    System.out.println("[SUCCESS] Script executado.");

                    System.out.println("[SUCCESS] Execucao Terminada da Tarefa Automatica com ID: " + this.at.identity() + ".");

                    byte[] serverMessageSuccess = {(byte) 0, (byte) 3, (byte) 0, (byte) 0};
                    System.out.println("[INFO] A Mandar Codigo de Sucesso (3) ao Cliente.");
                    sOut.write(serverMessageSuccess);
                    sOut.flush();

                    Date dataFim = new Date();
                    Calendar calFim = Calendar.getInstance();
                    calFim.setTime(dataFim);

                    this.at.defineDataFim(calFim);
                    System.out.println("[INFO] Data de Fim Atualizada da Atividade Automatica com ID: " + this.at.identity() + " para " + calFim.getTime() + ".");

                    System.out.println("[INFO] " + this.at.toString() + ".");

                    System.out.println("[INFO] A Mandar a Informacao ao Cliente da Tarefa Automatica com ID: " + this.at.identity() + ".");
                    sOutputObject.writeObject(this.at);
                    sOutputObject.flush();
                } else {
                    System.out.println("[ERROR] Falha na execucao do Script.");

                    System.out.println("[ERROR] Execucao nao completa da Tarefa Automatica com ID: " + this.at.identity() + ".");

                    byte[] serverMessageError = {(byte) 0, (byte) 4, (byte) 0, (byte) 0};
                    System.out.println("[INFO] A Mandar Codigo de Erro (4) ao Cliente.");
                    sOut.write(serverMessageError);
                    sOut.flush();
                }

                byte[] clientMessageEnd = sIn.readNBytes(4);
                if (clientMessageEnd[1] == 1) {
                    System.out.println("[SUCCESS] Codigo de Fim (1) do Cliente recebido.");
                    byte[] serverMessageEnd = {(byte) 0, (byte) 2, (byte) 0, (byte) 0};
                    System.out.println("[INFO] A Mandar Codigo de Entendido (2) ao Cliente.");
                    sOut.write(serverMessageEnd);
                    sOut.flush();
                    System.out.println("[INFO] Cliente " + clientIP.getHostAddress() + ", porta: " + this.s.getPort() + " desconectado.");
                } else {
                    System.out.println("[ERROR] Pacote do Cliente invalido.");
                }
            } else {
                System.out.println("[ERROR] Pacote do Cliente invalido.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("[ERROR] Atividade Automatica com ID: " + this.at.identity() + " nao concluida.");
        } finally {
            try {
                this.s.close();
            } catch (IOException e) {
                System.out.println("[ERROR] Problemas a Fechar o Socket.\n\n");
            }
            System.out.println("[SUCCESS] Socket Fechado.\n\n");
        }
    }
}