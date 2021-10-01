package eapli.helpdesk.tipoEquipa.application;/*
 * Copyright (c) 2013-2020 the original author or authors.
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


import eapli.helpdesk.tipoEquipa.domain.*;
import eapli.helpdesk.tipoEquipa.repositories.TipoEquipaRepository;
import eapli.helpdesk.infraestrutura.PersistenceContext;
import eapli.helpdesk.user.domain.HelpdeskRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

/**
 *
 * @author mcn
 */
@UseCaseController
public class AddTipoEquipaController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final TipoEquipaRepository repository = PersistenceContext.repositories().tipoEquipa();

    public Cor registerCor(String cor) throws CorException {
        return new Cor(cor);
    }

    public CodigoAlfaNumerico registerCodigoAlfaNumerico(String codAlfa) throws CodigoAlfaNumericoException {
        return new CodigoAlfaNumerico(codAlfa);
    }

    public Descricao registerDescricao(String descricao) throws DescricaoException {
        return new Descricao(descricao);
    }

    public TipoEquipa registerTipoEquipa(final String cor, final String codAlfaNum, final String descricao) throws CorException, DescricaoException, CodigoAlfaNumericoException {
        authz.ensureAuthenticatedUserHasAnyOf(HelpdeskRoles.HR_MANAGER,HelpdeskRoles.POWER_USER);
        Cor cor1=registerCor(cor);
        CodigoAlfaNumerico cod=registerCodigoAlfaNumerico(codAlfaNum);
        Descricao descricao1=registerDescricao(descricao);
        TipoEquipa tipoEquipa=new TipoEquipa(cor1,cod,descricao1);
        return this.repository.save(tipoEquipa);
    }
}

