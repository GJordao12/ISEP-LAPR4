/*
 * Copyright (c) 2013-2021 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.helpdesk.app.servicosRH.console.presentation;

import eapli.helpdesk.app.servicosRH.console.presentation.catalogo.AddCatalogoUI;
import eapli.helpdesk.app.servicosRH.console.presentation.servico.AddServicoAction;
import eapli.helpdesk.app.servicosRH.console.presentation.servico.ChangeServicoAction;
import eapli.helpdesk.app.servicosRH.console.presentation.equipa.AddTeamUI;
import eapli.helpdesk.app.servicosRH.console.presentation.equipa.AssociateTeamCollaboratorUI;
import eapli.helpdesk.app.servicosRH.console.presentation.equipa.RemoveTeamCollaboratorUI;
import eapli.helpdesk.app.servicosRH.console.presentation.tipoEquipa.AddTipoEquipaAction;
import eapli.helpdesk.app.servicosRH.console.presentation.colaborador.AddCollaboratorUI;
import eapli.helpdesk.app.servicosRH.console.presentation.criticidade.DefinirCriticidadeUI;
import eapli.helpdesk.app.common.console.presentation.authz.MyUserMenu;
import eapli.helpdesk.Application;
import eapli.helpdesk.app.servicosRH.console.presentation.authz.ListUsersAction;
import eapli.helpdesk.user.domain.HelpdeskRoles;
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

/**
 * TODO split this class in more specialized classes for each menu
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {

    private static final String RETURN_LABEL = "Voltar ";

    private static final int EXIT_OPTION = 0;

    //Menu HRManager
    private static final int UTILIZADOR_OPTION = 2;
    private static final int COLABORADOR_OPTION = 3;
    private static final int TEAMS_OPTION = 4;

    // Menu GSH
    private static final int CATALOGO_OPTION = 2;
    private static final int SERVICO_OPTION = 3;
    private static final int CRITICIDADE_OPTION = 4;

    //USERS
    private static final int LIST_USERS_OPTION = 1;

    //SERVICO
    private static final int ADD_SERVICO = 1;
    private static final int CHANGE_SERVICO = 2;

    //EQUIPA
    private static final int ADD_TEAM_OPTION = 1;
    private static final int ADD_TIPO_EQUIPA = 2;
    private static final int ADD_TEAM_COLLABORATOR = 3;
    private static final int REMOVE_TEAM_COLLABORATOR = 4;

    //COLABORADOR
    private static final int COLLABORATOR_REGISTER_OPTION = 1;

    //CRITICIDADE
    private static final int DEFINIR_CRITICIDADE_OPTION = 1;

    //CATALOGO
    private static final int CATALOGO_REGISTER_OPTION = 1;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;

    private static final String SEPARATOR_LABEL = "--------------";

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    /**
     * @return true if the user selected the exit option
     */
    @Override
    public boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            renderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        } else {
            renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        }
        return renderer.render();
    }

    @Override
    public String headline() {

        return authz.session().map(s -> "HelpDesk [ @" + s.authenticatedUser().identity() + " ]")
                .orElse("HelpDesk [ ==Anonymous== ]");
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        if (authz.isAuthenticatedUserAuthorizedTo(HelpdeskRoles.HR_MANAGER)) {
            final Menu usersMenu = buildUsersMenu();
            mainMenu.addSubMenu(UTILIZADOR_OPTION, usersMenu);

            final Menu collaboratorsMenu = buildCollaboratorsMenu();
            mainMenu.addSubMenu(COLABORADOR_OPTION, collaboratorsMenu);

            final Menu teamsMenu = buildTeamsMenu();
            mainMenu.addSubMenu(TEAMS_OPTION, teamsMenu);
        }

        if (authz.isAuthenticatedUserAuthorizedTo(HelpdeskRoles.SERVICE_MANAGER)) {
            final Menu catalogoMenu = buildCatalogoMenu();
            mainMenu.addSubMenu(CATALOGO_OPTION, catalogoMenu);

            final Menu servicoMenu = buildServicoMenu();
            mainMenu.addSubMenu(SERVICO_OPTION, servicoMenu);

            final Menu criticidadeMenu = buildCriticidadeMenu();
            mainMenu.addSubMenu(CRITICIDADE_OPTION, criticidadeMenu);
        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

            mainMenu.addItem(EXIT_OPTION, "Sair", new ExitWithMessageAction("Volte Sempre :))"));

        return mainMenu;
    }

    private Menu buildCriticidadeMenu() {
        final Menu menu = new Menu("Criticidade >");

        menu.addItem(DEFINIR_CRITICIDADE_OPTION, "Definir Criticidade", new DefinirCriticidadeUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildCatalogoMenu() {
        final Menu menu = new Menu("Catálogo >");

        menu.addItem(CATALOGO_REGISTER_OPTION, "Criar Catálogo", new AddCatalogoUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildServicoMenu() {
        final Menu menu = new Menu("Serviço >");

        menu.addItem(ADD_SERVICO, "Especificar Serviço", new AddServicoAction());
        menu.addItem(CHANGE_SERVICO, "Retomar/Alterar Serviço", new ChangeServicoAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildCollaboratorsMenu() {
        final Menu menu = new Menu("Colaboradores >");

        menu.addItem(COLLABORATOR_REGISTER_OPTION, "Criar Colaborador", new AddCollaboratorUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildTeamsMenu() {
        final Menu menu = new Menu("Equipas >");

        menu.addItem(ADD_TEAM_OPTION, "Adicionar Equipa", new AddTeamUI()::show);
        menu.addItem(ADD_TIPO_EQUIPA, "Adicionar Tipo Equipa", new AddTipoEquipaAction());
        menu.addItem(ADD_TEAM_COLLABORATOR, "Adicionar Colaborador a Equipa", new AssociateTeamCollaboratorUI()::show);
        menu.addItem(REMOVE_TEAM_COLLABORATOR, "Remover Colaborador de uma Equipa", new RemoveTeamCollaboratorUI()::show);
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildUsersMenu() {
        final Menu menu = new Menu("Utilizadores >");

        menu.addItem(LIST_USERS_OPTION, "Listar os Utilizadores", new ListUsersAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }
}
