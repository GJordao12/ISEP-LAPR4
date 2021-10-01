package eapli.helpdesk.persistence.impl.inmemory;

import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import eapli.helpdesk.servico.domain.Servico;
import eapli.helpdesk.servico.repositories.ServicoRepository;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumerico;
import eapli.helpdesk.catalogo.domain.Titulo;
import eapli.helpdesk.criticidade.domain.Tempo;


public class InMemoryServicoRepository extends InMemoryDomainRepository<Servico, CodigoAlfaNumerico>
            implements ServicoRepository {

        static {
            InMemoryInitializer.init();
        }

        @Override
        public Iterable<Servico> findServicoByCodigo(CodigoAlfaNumerico cod) {
            return null;
        }

        @Override
        public Iterable<Servico> findServicoByFeedback(Tempo feed) {
            return null;
        }

        @Override
        public Iterable<Servico> findServicoByTitulo(Titulo titulo) { return null; }
}
