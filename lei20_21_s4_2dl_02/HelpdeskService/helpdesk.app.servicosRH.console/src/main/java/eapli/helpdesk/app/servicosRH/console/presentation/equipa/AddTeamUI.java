package eapli.helpdesk.app.servicosRH.console.presentation.equipa;

import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.helpdesk.colaborador.application.ListCollaboratorService;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.equipa.application.AddTeamController;
import eapli.helpdesk.equipa.exception.AcronymException;
import eapli.helpdesk.equipa.exception.DesignationException;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumericoException;
import eapli.helpdesk.tipoEquipa.domain.TipoEquipa;
import eapli.helpdesk.app.servicosRH.console.presentation.tipoEquipa.TeamTypePrinter;
import eapli.helpdesk.app.servicosRH.console.presentation.colaborador.CollaboratorPrinter;

import java.util.ArrayList;
import java.util.List;

public class AddTeamUI extends AbstractUI {

    private final AddTeamController theController = new AddTeamController();
    private final ListCollaboratorService colabService = new ListCollaboratorService();

    @Override
    protected boolean doShow() {

        boolean flag = true;
        String alphanumericCode = "";
        String acronym = "";
        String designation = "";
        while (flag) {
            try {
                alphanumericCode = Console.readLine("Código Alfanumérico:");
                this.theController.registerAlphanumericCode(alphanumericCode);
                flag = false;
            } catch (CodigoAlfaNumericoException e) {
                System.out.println("Código Alfanumérico Inválido. Por favor insira um Código Alfanumérico válido.");
            }
        }
        flag=true;
        while (flag) {
            try {
                acronym = Console.readLine("Acrónimo:");
                this.theController.registerAcronym(acronym);
                flag = false;
            } catch (AcronymException e) {
                System.out.println("Acrónimo Inválido. Por favor insira um Acrónimo válido.");
            }
        }
        flag=true;
        while (flag) {
            try {
                designation = Console.readLine("Designação:");
                this.theController.registerDesignation(designation);
                flag = false;
            } catch (DesignationException e) {
                System.out.println("Designação Inválida. Por favor insira uma Designação válida.");
            }
        }

        final Iterable<TipoEquipa> teamTypes = this.theController.allTeamTypes();
        final SelectWidget<TipoEquipa> selector1 = new SelectWidget<>("Tipos de Equipa:", teamTypes, new TeamTypePrinter());
        selector1.show();
        final TipoEquipa teamType = selector1.selectedElement();

        final Iterable<Collaborator> collaboratorsAux = this.colabService.allCollaborators();
        List<Collaborator> collaborators = new ArrayList<>();
        collaboratorsAux.forEach(collaborators::add);

        List<Collaborator> collaboratorL = new ArrayList<>();
        List<Collaborator> collaboratorM = new ArrayList<>();

        if(!collaborators.isEmpty()) {
            while (Console.readInteger("Pretende Selecionar um Colaborador Reponsável?\n1 para prosseguir; 0 para parar...") != 0) {
                final SelectWidget<Collaborator> selector = new SelectWidget<>("Collaborators:", collaborators,
                        new CollaboratorPrinter());
                selector.show();
                final Collaborator theCollaborator = selector.selectedElement();
                collaboratorL.add(theCollaborator);
                collaborators.remove(theCollaborator);
            }
        } else {
            System.out.println("Não poderá ainda indicar reponsáveis por esta Equipa, uma vez que não existem ainda Colaboradores registados no sistema...");
        }

        try {
            this.theController.addTeam(alphanumericCode, acronym, designation, teamType, collaboratorL, collaboratorM);
        } catch (final IntegrityViolationException | CodigoAlfaNumericoException | AcronymException | DesignationException e) {
            System.out.println("Erro na Criação de uma nova Equipa...");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Registar Equipa";
    }
}
