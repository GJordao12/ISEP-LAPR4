/*
 * Copyright (c) 2013-2021 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.helpdesk.persistence.impl.jpa;

import eapli.helpdesk.Application;
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
import eapli.helpdesk.infraestrutura.RepositoryFactory;
import eapli.helpdesk.funcao.RoleRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.JpaAutoTxUserRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import org.springframework.transaction.reactive.TransactionContext;

/**
 * The repository factory for JPA repositories.
 *
 * <p>
 * This is the concrete factory in the Abstract Factory (GoF) pattern
 * </p>
 *
 * @author nuno 21/03/16
 */
public class JpaRepositoryFactory implements RepositoryFactory {

    @Override
    public UserRepository users(final TransactionalContext autoTx) {
        return new JpaAutoTxUserRepository(autoTx);
    }

    @Override
    public UserRepository users() {
        return new JpaAutoTxUserRepository(Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }


    @Override
    public TransactionalContext newTransactionalContext() {
        return JpaAutoTxRepository.buildTransactionalContext(
                Application.settings().getPersistenceUnitName(),
                Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public CatalogoRepository catalogo(final TransactionalContext autoTx) {
        return new JpaCatalogoRepository(autoTx);
    }

    @Override
    public CatalogoRepository catalogo() {
        return new JpaCatalogoRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public TipoEquipaRepository tipoEquipa(final TransactionalContext autoTx) {
        return new JpaTipoEquipaRepository();
    }

    @Override
    public TipoEquipaRepository tipoEquipa() {
        return new JpaTipoEquipaRepository();
    }

    @Override
    public ServicoRepository servico(final TransactionalContext autoTx) {
        return new JpaServicoRepository(autoTx);
    }

    @Override
    public ServicoRepository servico() {
        return new JpaServicoRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public FormularioRepository formulario(final TransactionalContext autoTx) {
        return new JpaFormularioRepository();
    }

    @Override
    public FormularioRepository formulario() {
        return new JpaFormularioRepository();
    }


    @Override
    public TeamRepository teams(TransactionalContext autoTx) {
        return new JpaTeamRepository();
    }

    @Override
    public TeamRepository teams() {
        return new JpaTeamRepository();
    }

    @Override
    public WorkflowRepository workflow(final TransactionalContext autoTx) {
        return new JpaWorkflowRepository(autoTx);
    }

    @Override
    public WorkflowRepository workflow() {
        return new JpaWorkflowRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public AtividadeManualRepository atividadeManual(final TransactionalContext autoTx) {
        return new JpaAtividadeManualRepository(autoTx);
    }

    @Override
    public AtividadeManualRepository atividadeManual() {
        return new JpaAtividadeManualRepository(Application.settings().getPersistenceUnitName());
    }


    @Override
    public AtividadeAutomaticaRepository atividadeAutomatica(final TransactionalContext autoTx) {
        return new JpaAtividadeAutomaticaRepository(autoTx);
    }

    @Override
    public AtividadeAutomaticaRepository atividadeAutomatica() {
        return new JpaAtividadeAutomaticaRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public CollaboratorRepository collaborators(final TransactionalContext autoTx) {
        return new JpaCollaboratorRepository(autoTx);
    }

    @Override
    public CollaboratorRepository collaborators() {
        return new JpaCollaboratorRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public CriticidadeRepository criticidades() {
        return new JpaCriticidadeRepository();
    }

    @Override
    public RoleRepository roles() {
        return new JpaRolesRepository();
    }

    @Override
    public AtividadeTicketRepository atividadesTicket() {
        return new JpaAtividadeTicketRepository();
    }

    @Override
    public AtividadeTicketRepository atividadesTicket(final TransactionalContext autoTx) {
        return new JpaAtividadeTicketRepository();
    }

    @Override
    public TicketRepository tickets() {
        return new JpaTicketRepository();
    }

    @Override
    public TicketRepository tickets(final TransactionalContext autoTx) {
        return new JpaTicketRepository();
    }

    @Override
    public FormularioPreenchidoRepository formPreenchido() {
        return new JpaFormularioPreenchidoRepository();
    }

    @Override
    public FormularioPreenchidoRepository formPreenchido(TransactionalContext autoTx) {
        return new JpaFormularioPreenchidoRepository();
    }

    @Override
    public FeedbackRepository feedbacks() {
        return new JpaFeedbackRepository();
    }

    @Override
    public SLARepository SLAs(final TransactionalContext autoTx) {
        return new JpaSLARepository(autoTx);
    }

    @Override
    public SLARepository SLAs() {
        return new JpaSLARepository(Application.settings().getPersistenceUnitName());
    }
}
