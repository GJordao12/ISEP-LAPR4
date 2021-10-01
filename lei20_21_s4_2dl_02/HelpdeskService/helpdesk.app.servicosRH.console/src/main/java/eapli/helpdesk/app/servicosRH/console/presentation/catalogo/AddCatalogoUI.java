package eapli.helpdesk.app.servicosRH.console.presentation.catalogo;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.helpdesk.app.servicosRH.console.presentation.equipa.TeamPrinter;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.equipa.domain.Team;
import eapli.helpdesk.app.servicosRH.console.presentation.colaborador.CollaboratorPrinter;
import eapli.helpdesk.app.servicosRH.console.presentation.criticidade.CriticidadePrinter;
import eapli.helpdesk.catalogo.application.AddCatalogoController;
import eapli.helpdesk.catalogo.exceptions.DescricaoBreveException;
import eapli.helpdesk.catalogo.exceptions.DescricaoCompletaException;
import eapli.helpdesk.catalogo.exceptions.IdException;
import eapli.helpdesk.catalogo.exceptions.TituloException;
import eapli.helpdesk.criticidade.domain.Criticidade;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddCatalogoUI extends AbstractUI {

    private final AddCatalogoController theController = new AddCatalogoController();

    @Override
    protected boolean doShow() {
        int i=0, t=0;
        int id = 0;
        String descBreve = "";
        String descCompleta="";
        String titulo="";
        String imageFilename ="";
        List<Team> equipasRes = new ArrayList<>();

        while(i==0) {
            try {
                id = Console.readInteger("Id do Catálogo:");
                this.theController.registerId(id);
                i++;
            } catch (IdException idException) {
                System.out.println("Id inválido. Por favor insira um Id válido.");
                i=0;
            }
        }
        i=0;

        while(i==0) {
            try {
                titulo = Console.readLine("Titulo do Catálogo:");
                this.theController.registerTitulo(titulo);
                i++;
            } catch (TituloException tituloException) {
                System.out.println("Titulo inválido. Por favor insira um titulo válido.");
                i=0;
            }
        }
        i=0;
        while(i==0) {
            try {
                descBreve = Console.readLine("Descrição Breve:");
                this.theController.registerDescBreve(descBreve);
                i++;
            } catch (DescricaoBreveException descricaoBreveException) {
                System.out.println("Descrição Breve inválida. Por favor insira uma descrição válida!");
                i=0;
            }
        }
        i=0;

        while(i==0) {
            try {
                descCompleta = Console.readLine("Descrição Completa:");
                this.theController.registerDescCompleta(descCompleta);
                i++;
            } catch (DescricaoCompletaException descricaoCompletaException) {
                System.out.println("Descrição Completa inválida. Por favor insira uma descrição válida!");
                i=0;
            }
        }
        i=0;
        while(i==0) {
            imageFilename = Console.readLine("Nome do Icone:");
            try {
                this.theController.registerIcone(imageFilename);
                i++;
            } catch (IOException e) {
                System.out.println(e.getMessage());
                i=0;
            }
        }

            System.out.println("Eliga um Colaborador Responsável:");
            final Iterable<Collaborator> colabs = this.theController.allCollaborators();
            final SelectWidget<Collaborator> selector1 = new SelectWidget<>("Colaboradores:", colabs, new CollaboratorPrinter());
            selector1.show();
            Collaborator colabRes = selector1.selectedElement();

          if(selector1.selectedOption() == 0) {
              System.out.println("Não foi possivel criar catálogo. Colaborador Responsável em falta!");
              return false;
          }

        System.out.println("Eliga um Nivel de Criticidade:");
        final Iterable<Criticidade> crit = this.theController.allCriticidades();
        final SelectWidget<Criticidade> criticidadeSelectWidget = new SelectWidget<>("Niveis de Criticidade:", crit, new CriticidadePrinter());
        criticidadeSelectWidget.show();
        Criticidade nivelCriticidade = criticidadeSelectWidget.selectedElement();

        if(selector1.selectedOption() == 0) {
            System.out.println("Não foi possivel criar catálogo. Nivel de Criticidade em falta!");
            return false;
        }

        System.out.println("Eliga a(s) equipa(s) responsáveis:");
          while(t==0) {
              final Iterable<Team> equipas = this.theController.allTeams();
              final SelectWidget<Team> teamSelectWidget = new SelectWidget<>("Teams:", equipas, new TeamPrinter());
              teamSelectWidget.show();
              if(teamSelectWidget.selectedOption() != 0) {
                  Team equipa = teamSelectWidget.selectedElement();
                  if (!equipasRes.contains(equipa)) {
                      equipasRes.add(equipa);
                  } else {
                      System.out.println("Esta equipa já se encontra na lista de equipas responsáveis!");
                  }
              }
              if (teamSelectWidget.selectedOption() == 0 && equipasRes.isEmpty()) {
                  System.out.println("Não foi possivel criar catálogo. Equipas em falta!");
                  return false;
              } else {
                  if (teamSelectWidget.selectedOption() == 0 && !equipasRes.isEmpty()) {
                      t++;
                  }
              }
          }

        try {
            this.theController.registerCatalogo(id,titulo,descBreve,descCompleta,imageFilename,colabRes,nivelCriticidade,equipasRes,new ArrayList<>());
        } catch (@SuppressWarnings("unused") final IntegrityViolationException | IdException | DescricaoBreveException | DescricaoCompletaException | TituloException | IOException e) {
            System.out.println("Catálogo já existente!");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Registar Catálogo";
    }
}
