package eapli.helpdesk.app.portal.console.presentation.ticket;


import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.helpdesk.algoritmosAtribuicao.AlgoritmosAtribuicaoTarefas;
import eapli.helpdesk.atividadeTicket.domain.AtividadeTicket;
import eapli.helpdesk.atividadeTicket.repositories.AtividadeTicketRepository;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.colaborador.repositories.CollaboratorRepository;
import eapli.helpdesk.equipa.domain.Team;
import eapli.helpdesk.infraestrutura.PersistenceContext;
import eapli.helpdesk.servico.application.AddServicoController;
import eapli.helpdesk.servico.domain.*;
import eapli.helpdesk.ticket.application.AddTicketController;
import eapli.helpdesk.ticket.domain.Ticket;
import eapli.helpdesk.ticket.exceptions.FormularioPreenchidoException;
import eapli.helpdesk.app.servicosRH.console.presentation.catalogo.CatalogoPrinter;
import eapli.helpdesk.app.servicosRH.console.presentation.servico.ServicoPrinter;
import eapli.helpdesk.catalogo.domain.Catalogo;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class SolicitarServicoUI extends AbstractUI {

    private final AddServicoController theController = new AddServicoController();
    private final AddTicketController ticketController = new AddTicketController();
    private final AtividadeTicketRepository repository = PersistenceContext.repositories().atividadesTicket();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CollaboratorRepository repoColla = PersistenceContext.repositories().collaborators();
    private final String emailColab = authz.session().get().authenticatedUser().email().toString();
    Collaborator collab= repoColla.findByEmail(emailColab);
    private final AlgoritmosAtribuicaoTarefas algoritmosAtribuicaoTarefas=new AlgoritmosAtribuicaoTarefas();

    @Override
    protected boolean doShow() {
        int i = 0;
        Catalogo catalogo = null;
        Servico servico = null;
        Ticket ticket = null;


        while (i == 0) {
            System.out.println("Pretende solicitar um serviço de quais dos seguintes catálogos:");
            final Iterable<Catalogo> catalogos = this.theController.findAllCatalogos();
            final SelectWidget<Catalogo> selector1 = new SelectWidget<>("Catálogos:", catalogos, new CatalogoPrinter());
            selector1.show();
            catalogo = selector1.selectedElement();

            if (selector1.selectedOption() < 0 || selector1.selectedOption() > theController.countCatalogos()) {
                System.out.println("Não foi possivel selecionar este catálogo. Selecione outro catálogo!");
            } else if (selector1.selectedOption() == 0) {
                return false;
            } else {
                i++;
            }
        }
        i--;
        while (i == 0) {
            System.out.println("Qual dos seguintes serviços pretende solicitar:");
            final SelectWidget<Servico> sel = new SelectWidget<>("Serviços:", catalogo.servicos(), new ServicoPrinter());
            sel.show();
            servico = sel.selectedElement();
            if (sel.selectedOption() < 0 || sel.selectedOption() > theController.countServicos(catalogo)) {
                System.out.println("Não foi possivel selecionar este catálogo. Selecione outro catálogo!");
            } else if (sel.selectedOption() == 0) {
                return false;
            }else if (sel.selectedElement().estado().equalsIgnoreCase("incompleto")){
                System.out.println("Este serviço encontra-se temporariamente incompleto. Selecione outro servico!");
            } else {
                i++;
            }
        }

        for (Team team :catalogo.equipas()) {
            if(team.listMembers().contains(collab)) {
                assert servico != null;
                if (servico.workflow().atividadeAutomatica()==null) {
                    System.out.println("Não é possivel a um colaborador desta equipa pedir este ticket!");
                    return false;
                }
            }
        }

        System.out.println("Preencha agora o seguinte formulário:");

        assert servico != null;
        Formulario formularioAPreencher = this.theController.findFormularioByCod(servico.formularioSolicitacao().codigoAlfaNumerico());
        String at;
        List<AtributoPreenchido> listaAtributos = new ArrayList<>();
        for (AtributoFormulario atributo : formularioAPreencher.atributos()) {

            at = Console.readLine("Atributo: " + atributo.toString() + "\nTipo: " + atributo.tipoDados());
            if (atributo.tipoDados().toString().equalsIgnoreCase("integer")) {
                int value;
                try {
                    value = Integer.parseInt(at);
                } catch (NumberFormatException nfe) {
                    value = 0;
                }
                AtributoPreenchido preenchido = new AtributoPreenchido(atributo, value);
                listaAtributos.add(preenchido);

            } else if (atributo.tipoDados().toString().equalsIgnoreCase("boolean")) {
                boolean bool = Boolean.parseBoolean(at);
                AtributoPreenchido preenchido = new AtributoPreenchido(atributo, bool);
                listaAtributos.add(preenchido);
            } else if (atributo.tipoDados().toString().equalsIgnoreCase("date")) {
                Date data = null;
                try {
                    data = new SimpleDateFormat("dd/MM/yyyy").parse(at);
                    AtributoPreenchido preenchido = new AtributoPreenchido(atributo, data);
                    listaAtributos.add(preenchido);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                AtributoPreenchido preenchido = new AtributoPreenchido(atributo, at);
                listaAtributos.add(preenchido);
            }

        }
        FormularioPreenchido form = new FormularioPreenchido(formularioAPreencher, listaAtributos);
        Calendar date = Console.readCalendar("Insira uma data limite para o ticket: ", "dd/MM/yyyy");
        int p = 0;
        while(p==0) {
            if (date.getTime().before(Calendar.getInstance().getTime())) {
                System.out.println("Data anterior à data atual. Escolha uma nova Data!");
                date = Console.readCalendar("Insira uma data limite para o ticket: ", "dd/MM/yyyy");
            }else {
                p++;
            }
        }

        if (servico.criticidade().identity().toString().equalsIgnoreCase("1")) {
            try {
                ticket = ticketController.registerTicket(servico, date, 1, form,collab);
            } catch (FormularioPreenchidoException e) {
                e.printStackTrace();
            }
        } else if (servico.criticidade().identity().toString().equalsIgnoreCase("2") || servico.criticidade().identity().toString().equalsIgnoreCase("3") || servico.criticidade().identity().toString().equalsIgnoreCase("4")) {
            try {
               ticket = ticketController.registerTicket(servico, date, 2, form,collab);
            } catch (FormularioPreenchidoException e) {
                e.printStackTrace();
            }
        } else {
            try {
               ticket = ticketController.registerTicket(servico, date, 3, form,collab);
            } catch (FormularioPreenchidoException e) {
                e.printStackTrace();
            }
        }
        if(servico.workflow().atividadeManualAprovacao() != null) {
            try {
                algoritmosAtribuicaoTarefas.automaticTaskAssignment(ticket, "APROVACAO");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if(servico.workflow().atividadeManualExecucao()!= null){
            try {
                algoritmosAtribuicaoTarefas.automaticTaskAssignment(ticket, "MANUAL");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        AtividadeTicket atividadeAutomatica = null;
        if(servico.workflow().atividadeAutomatica()!= null){
            atividadeAutomatica = new AtividadeTicket(ticket,null,"AUTOMATICA",null,null, null);
            repository.save(atividadeAutomatica);
        }
        System.out.println("Ticket para servico " + servico.titulo() + " criado com sucesso!");
        return false;
    }

    @Override
    public String headline() {
        return "Solicitar um servico";
    }
}
