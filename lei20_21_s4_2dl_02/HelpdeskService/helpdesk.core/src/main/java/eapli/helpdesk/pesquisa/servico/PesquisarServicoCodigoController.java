package eapli.helpdesk.pesquisa.servico;

import eapli.helpdesk.servico.domain.Servico;
import eapli.helpdesk.servico.repositories.ServicoRepository;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumerico;
import eapli.helpdesk.infraestrutura.PersistenceContext;

public class PesquisarServicoCodigoController {
    private final ServicoRepository servicoRepository = PersistenceContext.repositories().servico();
    public Iterable<Servico> findServicoByCodigo(CodigoAlfaNumerico cod) {

        return this.servicoRepository.findServicoByCodigo(cod);
    }
}
