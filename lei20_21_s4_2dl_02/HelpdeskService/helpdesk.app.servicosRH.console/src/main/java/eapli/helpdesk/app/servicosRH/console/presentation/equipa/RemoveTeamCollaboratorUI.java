package eapli.helpdesk.app.servicosRH.console.presentation.equipa;

import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.helpdesk.colaborador.dto.CollaboratorDTO;
import eapli.helpdesk.equipa.application.RemoveTeamCollaboratorController;
import eapli.helpdesk.equipa.dto.TeamDTO;
import eapli.helpdesk.app.servicosRH.console.presentation.colaborador.CollaboratorDTOPrinter;

public class RemoveTeamCollaboratorUI extends AbstractUI {

    private final RemoveTeamCollaboratorController controller = new RemoveTeamCollaboratorController();

    @Override
    protected boolean doShow() {

        final Iterable<TeamDTO> teams = this.controller.allTeams();
        if (!teams.iterator().hasNext()) {
            System.out.println("ERROR: Lista de Equipas Vazia!");
            return false;
        }

        final SelectWidget<TeamDTO> selectorTeam = new SelectWidget<>("Equipas:", teams, new TeamDTOPrinter());
        selectorTeam.show();

        if(selectorTeam.selectedOption() == 0) {
            System.out.println("Operação Interrompida!");
            return false;
        }

        final TeamDTO teamDTO = selectorTeam.selectedElement();
        final Iterable<CollaboratorDTO> collaborators = this.controller.teamCollaborators(teamDTO);

        if (!collaborators.iterator().hasNext()) {
            System.out.println("ERROR: Lista de Membros da Equipa Vazia!");
            return false;
        }

        final SelectWidget<CollaboratorDTO> selectorCollaborator = new SelectWidget<>("Colaboradores:", collaborators, new CollaboratorDTOPrinter());
        selectorCollaborator.show();

        if(selectorCollaborator.selectedOption() == 0) {
            System.out.println("Operação Interrompida!");
            return false;
        }

        final CollaboratorDTO collaboratorDTO = selectorCollaborator.selectedElement();
        this.controller.removeTeamCollaboraotr(collaboratorDTO);
        return false;
    }

    @Override
    public String headline() {
        return "Remover Colaborador de uma Equipa";
    }
}
