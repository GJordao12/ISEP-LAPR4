package eapli.helpdesk.pesquisa.servico;

import eapli.helpdesk.servico.domain.Servico;
import eapli.helpdesk.servico.repositories.ServicoRepository;
import eapli.helpdesk.criticidade.domain.Tempo;
import eapli.helpdesk.infraestrutura.PersistenceContext;

public class PesquisarServicoFeedbackController {
    private final ServicoRepository servicoRepository = PersistenceContext.repositories().servico();

    public Iterable<Servico> findServicoByFeedback(Tempo feed) {

        return this.servicoRepository.findServicoByFeedback(feed);
    }
}
