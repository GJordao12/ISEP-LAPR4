package eapli.helpdesk.catalogo.application;

import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.helpdesk.colaborador.domain.Collaborator;
import eapli.helpdesk.colaborador.repositories.CollaboratorRepository;
import eapli.helpdesk.equipa.domain.Team;
import eapli.helpdesk.equipa.repositories.TeamRepository;
import eapli.helpdesk.catalogo.domain.*;
import eapli.helpdesk.catalogo.exceptions.DescricaoBreveException;
import eapli.helpdesk.catalogo.exceptions.DescricaoCompletaException;
import eapli.helpdesk.catalogo.exceptions.IdException;
import eapli.helpdesk.catalogo.exceptions.TituloException;
import eapli.helpdesk.catalogo.repositories.CatalogoRepository;
import eapli.helpdesk.criticidade.domain.Criticidade;
import eapli.helpdesk.criticidade.repositories.CriticidadeRepository;
import eapli.helpdesk.infraestrutura.PersistenceContext;
import eapli.helpdesk.servico.domain.Servico;
import eapli.helpdesk.user.domain.HelpdeskRoles;
import org.apache.commons.io.IOUtils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@UseCaseController
public class AddCatalogoController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final CollaboratorRepository collaboratorRep = PersistenceContext.repositories().collaborators();
    private final TeamRepository teamRep = PersistenceContext.repositories().teams();
    private final CriticidadeRepository criticidadeRep = PersistenceContext.repositories().criticidades();
    private final CatalogoRepository catalogoRepository = PersistenceContext.repositories().catalogo();

        private final CatalogoRepository repository = PersistenceContext.repositories().catalogo();

        public Id registerId(int id) throws IdException {
        return new Id(id);
        }

        public DescricaoBreve registerDescBreve(String descBreve) throws DescricaoBreveException {
            return new DescricaoBreve(descBreve);
        }

        public DescricaoCompleta registerDescCompleta(String descCompleta) throws DescricaoCompletaException {
        return new DescricaoCompleta(descCompleta);
        }

        public Titulo registerTitulo(String titulo) throws TituloException {
            return new Titulo(titulo);
        }

    public byte[] registerIcone(String imageFilename) throws IOException {
        final InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(imageFilename);
        if (inputStream == null) {
            throw new FileNotFoundException("Imagem " + imageFilename + " não encontrada!");
        }
        return IOUtils.toByteArray(inputStream);
    }

    public Iterable<Collaborator> allCollaborators() {
        return collaboratorRep.findAll();
    }

    public Iterable<Team> allTeams() { return teamRep.findAll(); }

    public Iterable<Criticidade> allCriticidades() { return criticidadeRep.findAll(); }

    public List<Catalogo> allCatalogos(){
        List<Catalogo> lista = new ArrayList<>();
        for (Catalogo catalogo:catalogoRepository.findAll()) {
            lista.add(catalogo);}
        return lista;
    }

        public Catalogo registerCatalogo(final int id, final String descBreve,final String descCompleta, final String titulo, String inputStream, Collaborator colabRes, Criticidade criticidade, List<Team> equipas,List<Servico>servicos) throws IdException, DescricaoBreveException, DescricaoCompletaException, TituloException, IOException {
            authz.ensureAuthenticatedUserHasAnyOf(HelpdeskRoles.SERVICE_MANAGER,HelpdeskRoles.POWER_USER);
            Id id1 = registerId(id);
            DescricaoBreve descB=registerDescBreve(descBreve);
            DescricaoCompleta descC=registerDescCompleta(descCompleta);
            Titulo tit1 = registerTitulo(titulo);
            final Catalogo cat = new Catalogo(id1,descB,descC,tit1,registerIcone(inputStream),colabRes,criticidade, equipas,servicos);
            return this.repository.save(cat);
        }

}
