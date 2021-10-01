package eapli.helpdesk.motorFluxos.domain;

import eapli.helpdesk.motorFluxos.application.ShowMotorFluxosController;

import javax.net.ssl.SSLSocket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class HttpAjaxVotingRequest extends Thread {

    String baseFolder;
    SSLSocket sock;
    DataInputStream inS;
    DataOutputStream outS;
    ShowMotorFluxosController controller = new ShowMotorFluxosController();

    public HttpAjaxVotingRequest(SSLSocket s, String f) {
        baseFolder = f;
        sock = s;
    }

    @Override
    public void run() {
        try {
            outS = new DataOutputStream(sock.getOutputStream());
            inS = new DataInputStream(sock.getInputStream());
        } catch (IOException ex) {
            System.out.println("Thread error on data streams creation");
        }
        try {
            //HttpServerAjaxVoting x = new HttpServerAjaxVoting(controller.metodo());
            eapli.helpdesk.motorFluxos.domain.HTTPmessage request = new eapli.helpdesk.motorFluxos.domain.HTTPmessage(inS);
            eapli.helpdesk.motorFluxos.domain.HTTPmessage response = new HTTPmessage();

            if (request.getMethod().equals("GET")) {
                if (request.getURI().equals("/data")) {
                    response.setContentFromString(eapli.helpdesk.motorFluxos.domain.HttpServerAjaxVoting.getData(), "text/html");
                    response.setResponseStatus("200 Ok");
                } else if (request.getURI().equals("/Tickets")) {
                    response.setContentFromString(eapli.helpdesk.motorFluxos.domain.HttpServerAjaxVoting.refreshTable(), "text/html");
                    response.setResponseStatus("200 Ok");
                } else if (request.getURI().equals("/Percentagens")) {
                    response.setContentFromString(eapli.helpdesk.motorFluxos.domain.HttpServerAjaxVoting.refreshTable1(), "text/html");
                    response.setResponseStatus("200 ok");
                } else {
                    String fullname = baseFolder + "/";
                    if (request.getURI().equals("/")) {
                        fullname = fullname + "index.html";
                    } else {
                        fullname = fullname + request.getURI();
                    }
                    if (response.setContentFromFile(fullname)) {
                        response.setResponseStatus("200 Ok");
                    } else {
                        response.setContentFromString("<html><body><h1>404 File not found</h1></body></html>", "text/html");
                        response.setResponseStatus("404 Not Found");
                    }
                    response.send(outS);
                }
            }
              // NOT GET
                else if (request.getMethod().equals("PUT") && request.getURI().startsWith("/votes/")) {
                    response.setResponseStatus("200 Ok");
                } else {
                    response.setContentFromString("<html><body><h1>ERROR: 405 Method Not Allowed</h1></body></html>", "text/html");
                    response.setResponseStatus("405 Method Not Allowed");
                }
                response.send(outS);
        } catch (IOException ex) {
            //System.out.println("Thread error when reading request");
        }
        try {
            sock.close();
        } catch (IOException ex) {
            System.out.println("CLOSE IOException");
        }
    }
}
