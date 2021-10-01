package eapli.helpdesk.motorFluxos.domain;
import eapli.helpdesk.motorFluxos.application.ShowMotorFluxosController;
import eapli.helpdesk.ticket.domain.Ticket;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.util.*;

public class HttpServerAjaxVoting extends Thread {

    static private final String BASE_FOLDER = "helpdesk.core/src/main/java/eapli/helpdesk/motorFluxos/domain/www";

    private static ShowMotorFluxosController controller;


    static final int PORT = 55034;
    static final String TRUSTED_STORE = "serverHTTP.jks";
    static final String KEYSTORE_PASS = "forgotten";
    static private SSLServerSocket sock;
    static private Iterable<Ticket> ticketList;
    static private Calendar d;

    public HttpServerAjaxVoting(Calendar c , Iterable<Ticket> tList){
    ticketList = tList;
    d = c;

    }

    public void changeController(ShowMotorFluxosController controller){
        this.controller = controller;
    }

    @Override
    public void run() {

        SSLSocket cliSock = null;

        // Use this certificate and private key as server certificate

        // TRUSTED_STORE -> "serverHTTP.jks"
        System.setProperty("javax.net.ssl.keyStore", TRUSTED_STORE);

        // KEYSTORE_PASS -> "forgotten"
        System.setProperty("javax.net.ssl.keyStorePassword", KEYSTORE_PASS);

        try {
            SSLServerSocketFactory sslF = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            sock = (SSLServerSocket) sslF.createServerSocket(PORT);
        } catch (IOException ex) {
            System.out.println("Server failed to open local port " + PORT);
            System.exit(1);
        }

        while (true) {
            try {
                cliSock = (SSLSocket) sock.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            HttpAjaxVotingRequest req = new HttpAjaxVotingRequest(cliSock, BASE_FOLDER);
            req.start();
        }
    }

    // DATA ACCESSED BY THREADS - LOCKING REQUIRED

    public static synchronized String refreshTable() {
        try {
            if (ticketList != null) {
                StringBuilder s = new StringBuilder();
                    s.append("<tr class=\"active-row\">" +
                            "<td>" + controller.createdTickets((List<Ticket>) ticketList).get(0) + "</td>" +
                            "<td>" + controller.createdCompletedTickets((List<Ticket>) ticketList).get(0) + "</td>" +
                            "<td>" + controller.createdApprovalTickets((List<Ticket>) ticketList).get(0)   + "</td>" +
                            "<td>" + controller.createdTicketsForRealization((List<Ticket>) ticketList).get(0)  + "</td>" +
                            "</tr>");

                return s.toString();
            } else {
                return " ";
            }
        } catch (NullPointerException ne) {
            return " ";
        }
    }

    public static synchronized String refreshTable1() {
        try {
            if (ticketList != null) {
                StringBuilder s = new StringBuilder();
                s.append("<tr class=\"active-row\">" +
                        "<td>" + controller.createdTickets((List<Ticket>) ticketList).get(1) + "</td>" +
                        "<td>" + controller.createdCompletedTickets((List<Ticket>) ticketList).get(1) + "</td>" +
                        "<td>" + controller.createdApprovalTickets((List<Ticket>) ticketList).get(1)  + "</td>" +
                        "<td>" + controller.createdTicketsForRealization((List<Ticket>) ticketList).get(1)  + "</td>" +
                        "</tr>");
                return s.toString();
            } else {
                return " ";
            }
        } catch (NullPointerException ne) {
            return " ";
        }
    }

    public static synchronized String getData() {
        return " <div class=\"topnav\" id=\"data\">\n" +
                "    <a class=\"active\" href=\"#home\">Motor de Fluxos aberto desde:</a>\n" +
                "    <a href=> " + d.getTime() + "</a>\n" +
                "</div> ";
    }
}