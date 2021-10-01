/*
 * Copyright (c) 2013-2021 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eapli.helpdesk.persistence.impl.inmemory;

import eapli.helpdesk.colaborador.repositories.CollaboratorRepository;
import eapli.helpdesk.atividadeTicket.repositories.AtividadeTicketRepository;
import eapli.helpdesk.feedback.repository.FeedbackRepository;
import eapli.helpdesk.servico.repositories.*;
import eapli.helpdesk.equipa.repositories.TeamRepository;
import eapli.helpdesk.sla.repositories.SLARepository;
import eapli.helpdesk.ticket.repositories.FormularioPreenchidoRepository;
import eapli.helpdesk.ticket.repositories.TicketRepository;
import eapli.helpdesk.tipoEquipa.repositories.TipoEquipaRepository;
import eapli.helpdesk.catalogo.repositories.CatalogoRepository;
import eapli.helpdesk.criticidade.repositories.CriticidadeRepository;
import eapli.helpdesk.infraestrutura.bootstrapers.HelpdeskBootstrapper;
import eapli.helpdesk.infraestrutura.RepositoryFactory;
import eapli.helpdesk.funcao.RoleRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.InMemoryUserRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryTransactionalContext;

/**
 * @author nuno on 20/03/16.
 */
public class InMemoryRepositoryFactory implements RepositoryFactory {

    /**
     * Initialize a power user so that we can login.
     */
    @Override
    public UserRepository users(final TransactionalContext tx) {
        final InMemoryUserRepository repo = new InMemoryUserRepository();
        HelpdeskBootstrapper.registerPowerUser(repo);
        return repo;
    }

    @Override
    public UserRepository users() {
        return users(null);
    }

    @Override
    public TipoEquipaRepository tipoEquipa(TransactionalContext autoTx) {
        return new InMemoryTipoEquipaRepository();
    }

    @Override
    public TipoEquipaRepository tipoEquipa() {
        return new InMemoryTipoEquipaRepository();
    }

    @Override
    public ServicoRepository servico(TransactionalContext autoTx) {
        return new InMemoryServicoRepository();
    }

    @Override
    public ServicoRepository servico() {
        return new InMemoryServicoRepository();
    }

    @Override
    public FormularioRepository formulario(TransactionalContext autoTx) {
        return new InMemoryFormularioRepository();
    }

    @Override
    public FormularioRepository formulario() {
        return new InMemoryFormularioRepository();
    }


    @Override
    public TransactionalContext newTransactionalContext() {
        return new InMemoryTransactionalContext();
    }


    @Override
    public CatalogoRepository catalogo(final TransactionalContext tx) {
        return new InMemoryCatalogoRepository();
    }

    @Override
    public CatalogoRepository catalogo() {
        return new InMemoryCatalogoRepository();
    }


    @Override
    public WorkflowRepository workflow(final TransactionalContext tx) {
        return new InMemoryWorkflowRepository();
    }

    @Override
    public WorkflowRepository workflow() {
        return new InMemoryWorkflowRepository();
    }

    @Override
    public AtividadeManualRepository atividadeManual(final TransactionalContext tx) {
        return new InMemoryAtividadeManualRepository();
    }

    @Override
    public AtividadeManualRepository atividadeManual() {
        return new InMemoryAtividadeManualRepository();
    }

    @Override
    public AtividadeAutomaticaRepository atividadeAutomatica(final TransactionalContext tx) {
        return new InMemoryAtividadeAutomaticaRepository();
    }

    @Override
    public AtividadeAutomaticaRepository atividadeAutomatica() {
        return new InMemoryAtividadeAutomaticaRepository();
    }

    @Override
    public CollaboratorRepository collaborators(TransactionalContext autoTx) {
        return collaborators(null);
    }

    @Override
    public CollaboratorRepository collaborators() {
        return null;
    }

    @Override
    public TeamRepository teams(TransactionalContext autoTx) {
        return null;
    }

    @Override
    public TeamRepository teams() {
        return null;
    }

    @Override
    public CriticidadeRepository criticidades() {
        return new InMemoryCriticidadeRepository();
    }

    @Override
    public RoleRepository roles() {
        return new InMemoryRolesRepository();
    }

    @Override
    public AtividadeTicketRepository atividadesTicket() {
        return new InMemoryAtividadeTicketRepository();
    }

    @Override
    public AtividadeTicketRepository atividadesTicket(TransactionalContext autoTx) {
        return new InMemoryAtividadeTicketRepository();
    }

    @Override
    public TicketRepository tickets() {
        return new InMemoryTicketRepository();
    }

    @Override
    public TicketRepository tickets(TransactionalContext autoTx) {
        return new InMemoryTicketRepository();
    }

    @Override
    public FormularioPreenchidoRepository formPreenchido() {
        return new InMemoryFormularioPreenchidoRepository();
    }

    @Override
    public FormularioPreenchidoRepository formPreenchido(TransactionalContext autoTx) {
        return new InMemoryFormularioPreenchidoRepository();
    }

    @Override
    public FeedbackRepository feedbacks() {
        return new InMemoryFeedbackRepository();
    }

    @Override
    public SLARepository SLAs() {
        return new InMemorySLARepository();
    }

    @Override
    public SLARepository SLAs(TransactionalContext autoTx) {
        return new InMemorySLARepository();
    }
}
