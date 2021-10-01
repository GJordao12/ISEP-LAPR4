package eapli.helpdesk.criticidade.application;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.helpdesk.tipoEquipa.domain.*;
import eapli.helpdesk.criticidade.domain.Criticidade;
import eapli.helpdesk.criticidade.domain.Tempo;
import eapli.helpdesk.criticidade.domain.Valor;
import eapli.helpdesk.criticidade.exception.TempoException;
import eapli.helpdesk.criticidade.exception.ValorException;
import eapli.helpdesk.criticidade.repositories.CriticidadeRepository;
import eapli.helpdesk.infraestrutura.PersistenceContext;
import eapli.helpdesk.user.domain.HelpdeskRoles;

public class DefinirCriticidadeController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CriticidadeRepository repository = PersistenceContext.repositories().criticidades();

    public Descricao registarDesignacao(String designacao) throws DescricaoException {
        return new Descricao(designacao);
    }

    public Cor registarCor(String cor) throws CorException {
        return new Cor(cor);
    }

    public Valor registarValor(Integer valor) throws ValorException {
        return new Valor(valor);
    }

    public Tempo registarTempo(Integer tempo) throws TempoException {
        return new Tempo(tempo);
    }

    public Criticidade definirCriticidade(final Integer valor, final String designacao, final String cor, final String etiqueta, final Integer tempoMedAprovacao, final Integer tempoMaxAprovacao, final Integer tempoMedResolucao, final Integer tempoMaxResolucao) {
        authz.ensureAuthenticatedUserHasAnyOf(HelpdeskRoles.POWER_USER,HelpdeskRoles.SERVICE_MANAGER);
        try {
            Criticidade criticidade = new Criticidade(this.registarValor(valor), this.registarDesignacao(designacao), this.registarCor(cor), etiqueta, this.registarTempo(tempoMedAprovacao), this.registarTempo(tempoMaxAprovacao), this.registarTempo(tempoMedResolucao), this.registarTempo(tempoMaxResolucao));
            return this.repository.save(criticidade);
        } catch (ValorException | DescricaoException | CorException | TempoException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
