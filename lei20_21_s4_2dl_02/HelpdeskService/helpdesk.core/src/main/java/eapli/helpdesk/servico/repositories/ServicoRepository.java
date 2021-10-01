package eapli.helpdesk.servico.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.helpdesk.servico.domain.Servico;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumerico;
import eapli.helpdesk.catalogo.domain.Titulo;
import eapli.helpdesk.criticidade.domain.Tempo;

public interface ServicoRepository extends DomainRepository<CodigoAlfaNumerico, Servico>
{

    Iterable<Servico> findServicoByCodigo(CodigoAlfaNumerico cod);

    Iterable<Servico> findServicoByFeedback(Tempo feed);

    Iterable<Servico> findServicoByTitulo(Titulo titulo);
}

