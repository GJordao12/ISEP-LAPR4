package eapli.helpdesk.atividadeTicket.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.helpdesk.AppSettings;
import eapli.helpdesk.Application;
import eapli.helpdesk.atividadeTicket.domain.AtividadeTicket;
import eapli.helpdesk.atividadeTicket.dto.AtividadeTicketDTO;
import eapli.helpdesk.atividadeTicket.dto.AtividadeTicketDTOParser;
import eapli.helpdesk.atividadeTicket.service.ListAtividadeTicketService;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.colaborador.repositories.CollaboratorRepository;
import eapli.helpdesk.infraestrutura.PersistenceContext;
import eapli.helpdesk.servico.domain.AtributoFormulario;
import eapli.helpdesk.servico.domain.AtributoPreenchido;
import eapli.helpdesk.servico.domain.Formulario;
import eapli.helpdesk.servico.domain.FormularioPreenchido;
import eapli.helpdesk.ticket.domain.Ticket;
import eapli.helpdesk.ticket.repositories.FormularioPreenchidoRepository;
import eapli.helpdesk.user.domain.HelpdeskRoles;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class RealizarTarefaPendenteController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CollaboratorRepository collaboratorRepository = PersistenceContext.repositories().collaborators();
    private final String emailColab = authz.session().get().authenticatedUser().email().toString();
    private final Collaborator collab = collaboratorRepository.findByEmail(emailColab);
    private final ListAtividadeTicketService service = new ListAtividadeTicketService();
    private final AtividadeTicketDTOParser parser = new AtividadeTicketDTOParser();

    public List<AtividadeTicketDTO> showTasksPossibleToBeDone() {
        try {
            return this.service.createClientAndRequestTasks(collab, 10);
        } catch (IllegalArgumentException | IOException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    public void processamentoTarefaAprovacao(AtividadeTicket selected, List<String> respostas, boolean flag,Calendar c) throws ParseException, IOException {
        if(flag) {
            List<AtributoPreenchido> atrs = new ArrayList<>();
            Formulario formulario_preencher = selected.ticket().servico().workflow().atividadeManualAprovacao().formulario();
            List<AtributoFormulario> atributoFormularioSet = new ArrayList<>(formulario_preencher.atributos());
            for (int i=0; i<respostas.size(); i++) {
                if(atributoFormularioSet.get(i).tipoDados().toString().equalsIgnoreCase("boolean")) {
                    AtributoPreenchido atributoPreenchido = new AtributoPreenchido(atributoFormularioSet.get(i), Boolean.parseBoolean(respostas.get(i)));
                    atrs.add(atributoPreenchido);
                } else
                if(atributoFormularioSet.get(i).tipoDados().toString().equalsIgnoreCase("integer")) {
                    int x;
                    try {
                        x = Integer.parseInt(respostas.get(i));
                    } catch (NumberFormatException nfe) {
                        x = 0;
                    }
                    AtributoPreenchido atributoPreenchido = new AtributoPreenchido(atributoFormularioSet.get(i), x);
                    atrs.add(atributoPreenchido);
                } else
                if(atributoFormularioSet.get(i).tipoDados().toString().equalsIgnoreCase("date")) {
                    Date data;
                    try {
                        data = new SimpleDateFormat("dd/MM/yyyy").parse(respostas.get(i));
                    } catch (ParseException pe) {
                        data = null;
                    }
                    AtributoPreenchido atributoPreenchido = new AtributoPreenchido(atributoFormularioSet.get(i), data);
                    atrs.add(atributoPreenchido);
                } else {
                    AtributoPreenchido atributoPreenchido = new AtributoPreenchido(atributoFormularioSet.get(i), respostas.get(i));
                    atrs.add(atributoPreenchido);
                }
            }
            FormularioPreenchido formularioPreenchido = new FormularioPreenchido(formulario_preencher, atrs);
            Calendar c1 = Calendar.getInstance();
            saveTaskInfo(13, formularioPreenchido, c, c1, selected);
        } else {
            selected.ticket().changeEstado("REJEITADO");
            saveTicketInfo(14, selected.ticket());
        }
        System.out.println("\n[SUCCESS] Processamento da informação efetuado com sucesso!\n");
    }

    public void processamentoTarefaExecucao(AtividadeTicket selected, List<String> respostas,Calendar c) throws ParseException, IOException {
        List<AtributoPreenchido> atrs = new ArrayList<>();
        Formulario formulario_preencher = selected.ticket().servico().workflow().atividadeManualExecucao().formulario();
        List<AtributoFormulario> atributoFormularioSet = new ArrayList<>(formulario_preencher.atributos());
        for (int i=0; i<respostas.size(); i++) {
            if(atributoFormularioSet.get(i).tipoDados().toString().equalsIgnoreCase("boolean")) {
                AtributoPreenchido atributoPreenchido = new AtributoPreenchido(atributoFormularioSet.get(i), Boolean.parseBoolean(respostas.get(i)));
                atrs.add(atributoPreenchido);
            } else
            if(atributoFormularioSet.get(i).tipoDados().toString().equalsIgnoreCase("integer")) {
                int x;
                try {
                    x = Integer.parseInt(respostas.get(i));
                } catch (NumberFormatException nfe) {
                    x = 0;
                }
                AtributoPreenchido atributoPreenchido = new AtributoPreenchido(atributoFormularioSet.get(i), x);
                atrs.add(atributoPreenchido);
            } else
            if(atributoFormularioSet.get(i).tipoDados().toString().equalsIgnoreCase("date")) {
                Date data;
                try {
                    data = new SimpleDateFormat("dd/MM/yyyy").parse(respostas.get(i));
                } catch (ParseException pe) {
                    data = null;
                }
                AtributoPreenchido atributoPreenchido = new AtributoPreenchido(atributoFormularioSet.get(i), data);
                atrs.add(atributoPreenchido);
            } else {
                AtributoPreenchido atributoPreenchido = new AtributoPreenchido(atributoFormularioSet.get(i), respostas.get(i));
                atrs.add(atributoPreenchido);
            }
        }
        FormularioPreenchido formularioPreenchido = new FormularioPreenchido(formulario_preencher, atrs);
        Calendar c1 = Calendar.getInstance();
        saveTaskInfo(15, formularioPreenchido, c, c1, selected);
        System.out.println("[SUCCESS] Processamento da informação efetuado com sucesso!\n");
    }

    public AtividadeTicket showTicketInformation(AtividadeTicketDTO selectedDTO) {

        System.out.println("Dados relativos ao ticket associado à tarefa selecionada:\n" + selectedDTO.ticketDTO + "\n");

        return parser.valueOf(selectedDTO);
    }

    public void saveTaskInfo(int option, FormularioPreenchido formulario, Calendar c, Calendar c1, AtividadeTicket at) throws IOException {
        this.service.createClientAndSaveTaskInfo(option, formulario, c, c1, at);
    }

    public void saveTicketInfo(int option, Ticket ticket) throws IOException {
        this.service.createClientAndSaveTaskInfo2(option, ticket);
    }

}
