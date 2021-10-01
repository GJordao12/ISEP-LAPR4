/*
 * Copyright (c) 2013-2020 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package eapli.helpdesk.app.servicosRH.console.presentation.colaborador;

import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;
import eapli.helpdesk.app.servicosRH.console.presentation.equipa.TeamPrinter;
import eapli.helpdesk.colaborador.application.AddCollaboratorController;
import eapli.helpdesk.colaborador.application.ListCollaboratorService;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.colaborador.domain.PasswordGenerator;
import eapli.helpdesk.colaborador.exception.LongNameException;
import eapli.helpdesk.colaborador.exception.MecanographicNumberException;
import eapli.helpdesk.colaborador.exception.PhoneNumberException;
import eapli.helpdesk.colaborador.exception.ShortNameException;
import eapli.helpdesk.equipa.application.ListTeamService;
import eapli.helpdesk.equipa.domain.Team;
import eapli.helpdesk.equipa.exception.DesignationException;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * UI for adding a user to the application.
 *
 * Created by nuno on 22/03/16.
 */

@SuppressWarnings("java:S106")
public class AddCollaboratorUI extends AbstractUI {

    private final AddCollaboratorController theController = new AddCollaboratorController();
    private final ListTeamService listTeamService = new ListTeamService();
    private final ListCollaboratorService colabService = new ListCollaboratorService();

    @Override
    protected boolean doShow() {

        boolean flag = true;
        final String username = Console.readLine("Username:");
        String password = PasswordGenerator.generateStrongPassword();

        final String email = Console.readLine("E-Mail:");
        String nomeCurto = "";
        while (flag) {
            try {
                nomeCurto = Console.readLine("Nome Curto (Primeiro e último nome):");
                this.theController.registerShortName(nomeCurto);
                flag = false;
            } catch (ShortNameException e) {
                System.out.println("Nome Curto Inválido. Por favor insira um Nome Curto válido.");
            }
        }
        flag=true;
        String nomeCompleto = "";
        while (flag) {
            try {
                nomeCompleto = Console.readLine("Nome Completo:");
                this.theController.registerLongName(nomeCompleto);
                flag = false;
            } catch (LongNameException e) {
                System.out.println("Nome Completo Inválido. Por favor insira um Nome Completo válido.");
            }
        }
        flag=true;
        String mechanographicNumber = "";
        while (flag) {
            try {
                mechanographicNumber = Console.readLine("Número Mecanográfico:");
                this.theController.registerMecanographicNumber(mechanographicNumber);
                flag = false;
            } catch (MecanographicNumberException e) {
                System.out.println("Número Mecanográfico Inválido. Por favor insira um Número Mecanográfico válido.");
            }
        }
        flag=true;
        String phoneNumber = "";
        while (flag) {
            try {
                phoneNumber = Console.readLine("Número de Contacto:");
                this.theController.registerPhoneNumber(phoneNumber);
                flag = false;
            } catch (PhoneNumberException e) {
                System.out.println("Número de Contacto Inválido. Por favor insira um Número de Contacto válido.");
            }
        }
        final String birthDate = Console.readLine("Data de Nascimento:");

        System.out.println("\nLocal de Residência:");
        final String distrito = Console.readLine("Distrito:");
        final String conselho = Console.readLine("Concelho:");

        final Set<Role> roleTypes = new HashSet<>();
        boolean show;
        do {
            show = showRoles(roleTypes);
        } while (!show);

        final Iterable<Collaborator> collaborators = this.colabService.allCollaborators();
        List<Collaborator> aux = new ArrayList<>();
        collaborators.forEach(aux::add);
        Collaborator responsible = null;
        if(!aux.isEmpty()) {
            final SelectWidget<Collaborator> selector = new SelectWidget<>("Collaborators:", collaborators, new CollaboratorPrinter());
            selector.show();
            responsible = selector.selectedElement();
        } else {
            System.out.println("Não existem Colaboradores no sistema, pelo que não poderá selecionar um responsável...\n");
        }

        final Iterable<Team> teamsAux = this.listTeamService.allTeams();
        List<Team> teams = new ArrayList<>();
        teamsAux.forEach(teams::add);

        ArrayList<Team> teamsSelected = new ArrayList<>();

        if(!teams.isEmpty()) {
            while (Console.readInteger("Pretende selecionar uma Equipa ?\nPressione 1 para prosseguir ou 0 para parar...") != 0) {
                final SelectWidget<Team> selector1 = new SelectWidget<>("Equipas:", teams, new TeamPrinter());
                selector1.show();
                final Team theTeam = selector1.selectedElement();
                for (Team t : teams) {
                    if (t.teamType() == theTeam.teamType()) {
                        teams.remove(t);
                    }
                }
                teamsSelected.add(theTeam);
            }
        } else {
            System.out.println("Não existem Equipas no sistema, pelo que não poderá associar o Colaborador a nenhuma equipa por agora...");
        }

        try {
            this.theController.registerCollaborator(username, password, email, mechanographicNumber, nomeCurto,
                    nomeCompleto, phoneNumber, birthDate, distrito, conselho, roleTypes, responsible, teamsSelected);
        } catch (final IntegrityViolationException | ParseException | ShortNameException | MecanographicNumberException | LongNameException | PhoneNumberException | IOException | DesignationException e) {
            System.out.println("Erro na Especificação de Colaborador...");
        }

        return false;
    }

    private boolean showRoles(final Set<Role> roleTypes) {
        // TODO we could also use the "widget" classes from the framework...
        final Menu rolesMenu = buildRolesMenu(roleTypes);
        final MenuRenderer renderer = new VerticalMenuRenderer(rolesMenu, MenuItemRenderer.DEFAULT);
        return renderer.render();
    }

    private Menu buildRolesMenu(final Set<Role> roleTypes) {
        final Menu rolesMenu = new Menu();
        int counter = 0;
        rolesMenu.addItem(MenuItem.of(counter++, "No RoleCollaborator", Actions.SUCCESS));
        for (final Role roleType : theController.getRoleTypes()) {
            rolesMenu.addItem(
                    MenuItem.of(counter++, roleType.toString(), () -> roleTypes.add(roleType)));
        }
        return rolesMenu;
    }

    @Override
    public String headline() {
        return "Especificar Colaborador";
    }
}

