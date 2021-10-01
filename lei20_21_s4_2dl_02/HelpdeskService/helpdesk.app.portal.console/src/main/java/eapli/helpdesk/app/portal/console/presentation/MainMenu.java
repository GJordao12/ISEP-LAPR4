package eapli.helpdesk.app.portal.console.presentation;

import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;
import eapli.helpdesk.Application;
import eapli.helpdesk.app.common.console.presentation.authz.MyUserMenu;
import eapli.helpdesk.app.portal.console.presentation.atividadeTicket.AssignarTarefaPendenteUI;
import eapli.helpdesk.app.portal.console.presentation.atividadeTicket.ConsultarTarefasPendentesUI;
import eapli.helpdesk.app.portal.console.presentation.atividadeTicket.RealizarTarefaPendenteUI;
import eapli.helpdesk.app.portal.console.presentation.feedback.DarFeedbackUI;
import eapli.helpdesk.app.portal.console.presentation.pesquisa.catalogo.PesquisarCatalogoColabUI;
import eapli.helpdesk.app.portal.console.presentation.pesquisa.catalogo.PesquisarCatalogoCritUI;
import eapli.helpdesk.app.portal.console.presentation.pesquisa.catalogo.PesquisarCatalogoIdUI;
import eapli.helpdesk.app.portal.console.presentation.pesquisa.catalogo.PesquisarCatalogoTituloUI;
import eapli.helpdesk.app.portal.console.presentation.pesquisa.servico.PesquisarServicoCodigoUI;
import eapli.helpdesk.app.portal.console.presentation.pesquisa.servico.PesquisarServicoTempoFeedbackUI;
import eapli.helpdesk.app.portal.console.presentation.pesquisa.servico.PesquisarServicoTituloUI;
import eapli.helpdesk.app.portal.console.presentation.ticket.ConsultarEstadoPedidosUI;
import eapli.helpdesk.app.portal.console.presentation.ticket.SolicitarServicoUI;
import eapli.helpdesk.user.domain.HelpdeskRoles;

public class MainMenu extends AbstractUI {

    private static final String RETURN_LABEL = "Voltar ";

    private static final int EXIT_OPTION = 0;

    //Menu Geral
    private static final int PESQUISA_CATALOGO = 2;
    private static final int PESQUISA_SERVICO = 3;
    private static final int TAREFAS_OPTION = 4;
    private static final int TICKETS_OPTION = 5;

    //TAREFAS
    private static final int CONSULT_TASKS_OPTION = 1;
    private static final int REIVINDICAR_TAREFA_OPTION = 2;
    private static final int REALIZAR_TAREFA_OPTION = 3;

    //PESQUISA_CATALOGO
    private static final int PESQUISA_CAT_ID = 1;
    private static final int PESQUISA_CAT_TITULO = 2;
    private static final int PESQUISA_CAT_COLAB = 3;
    private static final int PESQUISA_CAT_CRIT = 4;

    //PESQUISA_SERVICO
    private static final int PESQUISA_SER_TITULO = 1;
    private static final int PESQUISA_SER_CODALF = 2;
    private static final int PESQUISA_SER_TEMPOFEEDBACK = 3;

    //TICKETS
    private static final int ADD_TICKET = 1;
    private static final int CONSULT_TICKETS_OPTION = 2;
    private static final int FEEDBACK_TICKETS_OPTION = 3;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;

    private static final String SEPARATOR_LABEL = "--------------";

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    protected boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            renderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        } else {
            renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        }
        return renderer.render();
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        if (authz.isAuthenticatedUserAuthorizedTo(HelpdeskRoles.HR_MANAGER) || authz.isAuthenticatedUserAuthorizedTo(HelpdeskRoles.SERVICE_MANAGER) || authz.isAuthenticatedUserAuthorizedTo(HelpdeskRoles.COLLABORATOR)) {
            final Menu pesquisaCatMenu = buildPesquisaCatMenu();
            mainMenu.addSubMenu(PESQUISA_CATALOGO, pesquisaCatMenu);

            final Menu pesquisaSerMenu = buildPesquisaSerMenu();
            mainMenu.addSubMenu(PESQUISA_SERVICO, pesquisaSerMenu);

            final Menu tarefasMenu = buildTarefasMenu();
            mainMenu.addSubMenu(TAREFAS_OPTION, tarefasMenu);

            final Menu showTicketsMenu = buildTicketsMenu();
            mainMenu.addSubMenu(TICKETS_OPTION, showTicketsMenu);
        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        mainMenu.addItem(EXIT_OPTION, "Sair", new ExitWithMessageAction("Volte Sempre :))"));

        return mainMenu;
    }

    private Menu buildTicketsMenu() {
        final Menu menu = new Menu("Tickets >");

        menu.addItem(ADD_TICKET, "Criar um Ticket", new SolicitarServicoUI()::show);
        menu.addItem(CONSULT_TICKETS_OPTION, "Consultar Estado dos Tickets e respetivos Detalhes", new ConsultarEstadoPedidosUI()::show);
        menu.addItem(FEEDBACK_TICKETS_OPTION, "Dar Feedback da resolução de um Ticket", new DarFeedbackUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildPesquisaSerMenu() {
        final Menu menu = new Menu("Pesquisa por Serviços >");

        menu.addItem(PESQUISA_SER_TITULO, "Pesquisar Serviço por Título", new PesquisarServicoTituloUI()::show);
        menu.addItem(PESQUISA_SER_CODALF, "Pesquisar Serviço por Código Alfanumérico", new PesquisarServicoCodigoUI()::show);
        menu.addItem(PESQUISA_SER_TEMPOFEEDBACK, "Pesquisar Serviço por Tempo de Feedback", new PesquisarServicoTempoFeedbackUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildPesquisaCatMenu() {
        final Menu menu = new Menu("Pesquisa por Catálogo >");

        menu.addItem(PESQUISA_CAT_ID, "Pesquisar Catálogo por ID", new PesquisarCatalogoIdUI()::show);
        menu.addItem(PESQUISA_CAT_TITULO, "Pesquisar Catálogo por Titulo", new PesquisarCatalogoTituloUI()::show);
        menu.addItem(PESQUISA_CAT_COLAB, "Pesquisar Catálogo por Colaborador Responsável", new PesquisarCatalogoColabUI()::show);
        menu.addItem(PESQUISA_CAT_CRIT, "Pesquisar Catálogo por Nível de Criticidade", new PesquisarCatalogoCritUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildTarefasMenu() {
        final Menu menu = new Menu("Tarefas >");

        menu.addItem(CONSULT_TASKS_OPTION, "Consultar Tarefas Pendentes Reivindicadas", new ConsultarTarefasPendentesUI()::show);
        menu.addItem(REIVINDICAR_TAREFA_OPTION, "Reivindicar Tarefa Pendente", new AssignarTarefaPendenteUI()::show);
        menu.addItem(REALIZAR_TAREFA_OPTION, "Realizar Tarefa Pendente", new RealizarTarefaPendenteUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    @Override
    public String headline() {
        return authz.session().map(s -> "HelpDesk [ @" + s.authenticatedUser().identity() + " ]")
                .orElse("HelpDesk [ ==Anonymous== ]");
    }
}
