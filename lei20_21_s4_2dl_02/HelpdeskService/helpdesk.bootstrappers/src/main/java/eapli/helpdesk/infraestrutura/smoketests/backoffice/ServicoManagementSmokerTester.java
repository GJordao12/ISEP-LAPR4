package eapli.helpdesk.infraestrutura.smoketests.backoffice;

import eapli.framework.actions.Action;
import eapli.framework.validations.Invariants;
import eapli.helpdesk.atividade.AtividadeAutomatica;
import eapli.helpdesk.atividade.AtividadeManual;
import eapli.helpdesk.servico.domain.*;
import eapli.helpdesk.servico.repositories.ServicoRepository;
import eapli.helpdesk.tipoEquipa.domain.*;
import eapli.helpdesk.catalogo.domain.DescricaoBreve;
import eapli.helpdesk.catalogo.domain.Titulo;
import eapli.helpdesk.catalogo.exceptions.DescricaoBreveException;
import eapli.helpdesk.catalogo.exceptions.TituloException;
import eapli.helpdesk.criticidade.domain.Criticidade;
import eapli.helpdesk.criticidade.domain.Tempo;
import eapli.helpdesk.criticidade.domain.Valor;
import eapli.helpdesk.criticidade.exception.TempoException;
import eapli.helpdesk.criticidade.exception.ValorException;
import eapli.helpdesk.infraestrutura.PersistenceContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ServicoManagementSmokerTester implements Action {

    private static final Logger LOGGER = LogManager.getLogger(ServicoManagementSmokerTester.class);

    private final ServicoRepository repo = PersistenceContext.repositories().servico();

    @Override
    public boolean execute() {

        try {
            registarServicos();
        } catch (DescricaoCompletaServicoException | CodigoAlfaNumericoException | DescricaoBreveException | TituloException | DescricaoException | TempoException | CorException | ValorException | ParseException | TipoDadosException descricaoCompletaServicoException) {
            descricaoCompletaServicoException.printStackTrace();
        }
        return true;
    }

    private void registarServicos() throws CodigoAlfaNumericoException, DescricaoBreveException, DescricaoCompletaServicoException, TituloException, DescricaoException, TempoException, CorException, ValorException, ParseException, TipoDadosException {
        Set<String> keywords=new HashSet<>();
        keywords.add("servico de resolucao");
        Set<String> keywords1=new HashSet<>();
        keywords1.add("servico de erros");
        List<AtributoFormulario> atributos=new ArrayList<>();
        AtributoFormulario atributo1=new AtributoFormulario(new Nome("Especificacao"),new Descricao("a"),"0*",new TipoDados("int"), new Etiqueta("etiqueta1"));
        atributos.add(atributo1);
        Formulario formulario=new Formulario(new CodigoAlfaNumerico("3737ldk")
                ,new Nome("Formulario de solicitacao"),new Etiqueta("Form1"),null,atributos);
        Formulario formulario1=new Formulario(new CodigoAlfaNumerico("370ldk")
                ,new Nome("Formulario de solicitacao"),new Etiqueta("Form2"),null,atributos);
        Criticidade criticidade=new Criticidade(new Valor(100), new Descricao("Alta"), new Cor("#FFFFFF"), "Alta", new Tempo(10), new Tempo(10), new Tempo(10), new Tempo(10));

        String dataMudanca="22/03/2012";
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(formatoData.parse(dataMudanca));

        Workflow workflow=new Workflow(c,new AtividadeManual(null),new AtividadeManual(null),
                new AtividadeAutomatica(null));

        repo.save(new Servico(new CodigoAlfaNumerico("2323pol"), new DescricaoBreve("Servico1"),
                new DescricaoCompletaServico("Servico de resolucao de bugs"), "Incompleto",
                "S",4, new Titulo("Servico de resolucao"),null,
        keywords,formulario,criticidade,workflow));
        repo.save(new Servico(new CodigoAlfaNumerico("123efd"),new DescricaoBreve("Servico2"),
                new DescricaoCompletaServico("Servico de resolucao de erros"),
                "Incompleto","N",null,new Titulo("Servico de resolução")
                ,null,
                keywords1,formulario1,criticidade,workflow));
        LOGGER.info("»»» definidos serviços");

        //findAll
        final Iterable<Servico> l = repo.findAll();
        Invariants.nonNull(l);
        Invariants.nonNull(l.iterator());
        Invariants.ensure(l.iterator().hasNext());
        LOGGER.info("»»» find all serviços");

        // count
        final long n = repo.count();
        LOGGER.info("»»» # serviços = {}", n);

        // ofIdentity
        final Servico s1 = repo.ofIdentity(new CodigoAlfaNumerico("3737ldk")).orElseThrow(IllegalStateException::new);
        final Servico s2 = repo.ofIdentity(new CodigoAlfaNumerico("2323pol")).orElseThrow(IllegalStateException::new);
        LOGGER.info("»»» found servicos of identity");


        // containsOfIdentity
        final boolean hasId = repo.containsOfIdentity(s1.identity());
        Invariants.ensure(hasId);
        LOGGER.info("»»» contains servico of identity");

        // contains
        final boolean has = repo.contains(s1);
        Invariants.ensure(has);
        LOGGER.info("»»» contains servico");

        // delete
        repo.delete(s1);
        LOGGER.info("»»» delete servico");

        // deleteOfIdentity
        repo.deleteOfIdentity(s2.identity());
        LOGGER.info("»»» delete servico of identity");

        // size
        final long n2 = repo.size();
        Invariants.ensure(n2 == n - 2);
        LOGGER.info("»»» # servicos = {}", n2);
    }
}
