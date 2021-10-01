package eapli.helpdesk.infraestrutura.bootstrapers;

import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.colaborador.repositories.CollaboratorRepository;
import eapli.helpdesk.criticidade.repositories.CriticidadeRepository;
import eapli.helpdesk.equipa.domain.Team;
import eapli.helpdesk.equipa.repositories.TeamRepository;
import eapli.helpdesk.servico.domain.Servico;
import eapli.helpdesk.catalogo.application.AddCatalogoController;
import eapli.helpdesk.catalogo.exceptions.DescricaoBreveException;
import eapli.helpdesk.catalogo.exceptions.DescricaoCompletaException;
import eapli.helpdesk.catalogo.exceptions.IdException;
import eapli.helpdesk.catalogo.exceptions.TituloException;
import eapli.helpdesk.criticidade.domain.Criticidade;
import eapli.helpdesk.infraestrutura.PersistenceContext;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumerico;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumericoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CatalogoBootstrapper implements Action {
    private static final Logger LOGGER = LogManager.getLogger(CatalogoBootstrapper.class);
    final AddCatalogoController controller = new AddCatalogoController();
    private final CollaboratorRepository CollaboratorRepo = PersistenceContext.repositories().collaborators();
    private final CriticidadeRepository criticidadeRepository=PersistenceContext.repositories().criticidades();
    private final TeamRepository teamRepository=PersistenceContext.repositories().teams();

    @Override
    public boolean execute() {

        Collaborator colab1 = CollaboratorRepo.findAll().iterator().next();
        Criticidade criticidade=criticidadeRepository.findAll().iterator().next();
        Optional<Team> team = null;
        try {
            team = teamRepository.ofIdentity(new CodigoAlfaNumerico("equipaRH1"));
        } catch (CodigoAlfaNumericoException e) {
            e.printStackTrace();
        }

        ArrayList<Team> teamList=new ArrayList<>();
        teamList.add(team.stream().iterator().next());


        Optional<Team> team1 = null;
        try {
            team1 = teamRepository.ofIdentity(new CodigoAlfaNumerico("equipaDV1"));
        } catch (CodigoAlfaNumericoException e) {
            e.printStackTrace();
        }

        ArrayList<Team> teamList1=new ArrayList<>();
        teamList.add(team1.stream().iterator().next());

        try {
            registerCatalogo(1, "Pedidos de ausencia", "Pedidos de ausência(e.g. ferias)", "Pedido de ausência por diversos motivos", "images/Servico1.jpg", colab1, criticidade,teamList,new ArrayList<>());
            registerCatalogo(2, "Alteração de dados", "Alteração de dados", "Alteração de dados", "images/Servico1.jpg", colab1, criticidade,teamList1,new ArrayList<>());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void registerCatalogo(int id, String titulo, String descBreve, String descCompleta, String icone, Collaborator responsible, Criticidade criticidade, ArrayList<Team> teams, ArrayList<Servico>servicos) throws ParseException {
        try {
            controller.registerCatalogo(id, descBreve, descCompleta,titulo, icone, responsible, criticidade, teams,servicos);
        } catch (final IntegrityViolationException | ConcurrencyException ex) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", id);
            LOGGER.trace("Assuming existing record", ex);
        } catch (DescricaoCompletaException | DescricaoBreveException | IOException | IdException | TituloException e) {
            e.printStackTrace();
        }
    }
}
