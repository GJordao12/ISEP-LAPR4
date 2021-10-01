package eapli.helpdesk.pesquisa.servico;

import eapli.helpdesk.servico.domain.Servico;
import eapli.helpdesk.servico.repositories.ServicoRepository;
import eapli.helpdesk.catalogo.domain.Titulo;
import eapli.helpdesk.infraestrutura.PersistenceContext;

public class PesquisarServicoTituloController {
    private final ServicoRepository servicoRepository = PersistenceContext.repositories().servico();
    public Iterable<Servico> findServicoByTitulo(Titulo titulo) {

        return this.servicoRepository.findServicoByTitulo(titulo);
    }
}
