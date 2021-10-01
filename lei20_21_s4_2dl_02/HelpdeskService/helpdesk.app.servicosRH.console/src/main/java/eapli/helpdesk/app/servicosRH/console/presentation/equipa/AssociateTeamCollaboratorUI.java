package eapli.helpdesk.app.servicosRH.console.presentation.equipa;

import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.helpdesk.colaborador.dto.CollaboratorDTO;
import eapli.helpdesk.equipa.application.AssociateTeamCollaboratorController;
import eapli.helpdesk.equipa.dto.TeamDTO;
import eapli.helpdesk.app.servicosRH.console.presentation.colaborador.CollaboratorDTOPrinter;

public class AssociateTeamCollaboratorUI extends AbstractUI {

    private final AssociateTeamCollaboratorController controller = new AssociateTeamCollaboratorController();

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
        final Iterable<CollaboratorDTO> collaborators = this.controller.teamCollaboratorsAllowedToJoinTeam(teamDTO);

        if (!collaborators.iterator().hasNext()) {
            System.out.println("ERROR: Não há colabarodores possíveis de serem adicionados à equipa selecionada!");
            return false;
        }

        final SelectWidget<CollaboratorDTO> selectorColab = new SelectWidget<>("Colaboradores:", collaborators, new CollaboratorDTOPrinter());
        selectorColab.show();

        if(selectorColab.selectedOption() == 0) {
            System.out.println("Operação Interrompida!");
            return false;
        }

        final CollaboratorDTO collaboratorDTO = selectorColab.selectedElement();
        this.controller.associateTeamCollaboraotr(collaboratorDTO);
        return false;
    }

    @Override
    public String headline() {
        return "Associar Colaborador de uma Equipa";
    }
}
