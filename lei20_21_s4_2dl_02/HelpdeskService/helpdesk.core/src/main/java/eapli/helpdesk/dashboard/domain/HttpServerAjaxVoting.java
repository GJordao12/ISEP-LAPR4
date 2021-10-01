package eapli.helpdesk.dashboard.domain;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.helpdesk.AppSettings;
import eapli.helpdesk.Application;
import eapli.helpdesk.dashboard.application.ShowDashboardController;
import eapli.helpdesk.atividadeTicket.dto.AtividadeTicketDTO;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HttpServerAjaxVoting extends Thread {

    static private final String BASE_FOLDER = "helpdesk.core/src/main/java/eapli/helpdesk/dashboard/domain/www";

    private final static AuthorizationService authz = AuthzRegistry.authorizationService();
    private final static String username = authz.session().get().authenticatedUser().username().toString();
    private final static String email = authz.session().get().authenticatedUser().email().toString();

    private static ShowDashboardController controller;
    static private List<AtividadeTicketDTO> atividadeTicketDTOList;

    static final int PORT = 55090;
    static final String TRUSTED_STORE = "serverHTTP.jks";
    static AppSettings app = Application.settings();
    static final String keyStorePassProperties = app.getKeyStorePass();
    static private SSLServerSocket sock;

    public HttpServerAjaxVoting(List<AtividadeTicketDTO> atv){
        atividadeTicketDTOList = atv;
    }

    public void changeController(ShowDashboardController controller){
        this.controller = controller;
    }

    @Override
    public void run() {

        SSLSocket cliSock = null;

        // Use this certificate and private key as server certificate

        // TRUSTED_STORE -> "serverHTTP.jks"
        System.setProperty("javax.net.ssl.keyStore", TRUSTED_STORE);

        // KEYSTORE_PASS -> "forgotten"
        System.setProperty("javax.net.ssl.keyStorePassword", keyStorePassProperties);

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

    public static synchronized String getPersonalInfo() {
        //controller.metodo();
        return " <div class=\"topnav\" id=\"personalInformation\">\n" +
                "    <a class=\"active\" href=\"#home\">Personal Information</a>\n" +
                "    <a href=> Name: " + username + "</a>\n" +
                "    <a href=> Email: " + email + "</a>\n" +
                "    <a href=> Number of Taks: " + atividadeTicketDTOList.size() + "</a>\n" +
                "</div> ";
    }


    public static synchronized String refreshTable() {
        //controller.metodo();
        try {
            if (atividadeTicketDTOList != null) {
                StringBuilder s = new StringBuilder();
                for (AtividadeTicketDTO atv : controller.orderByCriticality(atividadeTicketDTOList)) {
                    s.append("<tr class=\"active-row\">" +
                            "<td>" + atv.tipoAtividade + "</td>" +
                            "<td>" + atv.ticketDTO.serviceDTO.code + "</td>" +
                            "<td>" + atv.ticketDTO.serviceDTO.briefDescription + "</td>" +
                            "<td>" + atv.ticketDTO.id + "</td>" +
                            "<td>" + atv.ticketDTO.prioridade + "</td>" +
                            "<td>" + atv.ticketDTO.serviceDTO.critDTO.valor + "</td>" +
                            "<td>" + atv.ticketDTO.dataLimite.getTime() + "</td>" +
                            "</tr>");
                }
                return s.toString();
            } else {
                return " ";
            }
        } catch (NullPointerException ne) {
            return " ";
        }
    }

    public static synchronized String refreshTable1() {
        //controller.metodo();
        try {
            if (atividadeTicketDTOList != null) {
                StringBuilder s = new StringBuilder();

                for (AtividadeTicketDTO atv : controller.orderByPriority(atividadeTicketDTOList)) {
                    s.append("<tr class=\"active-row\">" +
                            "<td>" + atv.tipoAtividade + "</td>" +
                            "<td>" + atv.ticketDTO.serviceDTO.code + "</td>" +
                            "<td>" + atv.ticketDTO.serviceDTO.briefDescription + "</td>" +
                            "<td>" + atv.ticketDTO.id + "</td>" +
                            "<td>" + atv.ticketDTO.prioridade + "</td>" +
                            "<td>" + atv.ticketDTO.serviceDTO.critDTO.valor + "</td>" +
                            "<td>" + atv.ticketDTO.dataLimite.getTime() + "</td>" +
                            "</tr>");
                }
                return s.toString();
            } else {
                return " ";
            }
        } catch (NullPointerException ne) {
            return " ";
        }
    }

    public static synchronized String refreshTable2() {
        //controller.metodo();
        try {
            if (atividadeTicketDTOList != null) {
                StringBuilder s = new StringBuilder();

                for (AtividadeTicketDTO atv : controller.orderByDeadline(atividadeTicketDTOList)) {
                    s.append("<tr class=\"active-row\">" +
                            "<td>" + atv.tipoAtividade + "</td>" +
                            "<td>" + atv.ticketDTO.serviceDTO.code + "</td>" +
                            "<td>" + atv.ticketDTO.serviceDTO.briefDescription + "</td>" +
                            "<td>" + atv.ticketDTO.id + "</td>" +
                            "<td>" + atv.ticketDTO.prioridade + "</td>" +
                            "<td>" + atv.ticketDTO.serviceDTO.critDTO.valor + "</td>" +
                            "<td>" + atv.ticketDTO.dataLimite.getTime() + "</td>" +
                            "</tr>");
                }
                return s.toString();
            } else {
                return " ";
            }
        } catch (NullPointerException ne) {
            return " ";
        }
    }
}
