/*
 * Copyright (c) 2013-2020 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and
 * associated documentation files (the "Software"), to deal in the Software
 * without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish,
 * distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom
 * the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.helpdesk.app.servicosRH.console.presentation.tipoEquipa;

import eapli.helpdesk.tipoEquipa.application.AddTipoEquipaController;
import eapli.helpdesk.tipoEquipa.domain.CodigoAlfaNumericoException;
import eapli.helpdesk.tipoEquipa.domain.CorException;
import eapli.helpdesk.tipoEquipa.domain.DescricaoException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

/**
 *
 * @author mcn
 */
@SuppressWarnings("squid:S106")
public class AddTipoEquipaUI extends AbstractUI {

    private final AddTipoEquipaController theController = new AddTipoEquipaController();

    @Override
    protected boolean doShow() {
        int i=0;
        String cor = "";
        String descricao="";
        String codigoAlfaNumerico="";
        while(i==0) {
            try {
                cor = Console.readLine("Cor do tipo de equipa:");
                this.theController.registerCor(cor);
                i++;
            } catch (CorException corException) {
                System.out.println("Cor inválida. Por favor insira uma cor válida.");
                i=0;
            }
        }
        i=0;
        while(i==0) {
            try {
                codigoAlfaNumerico = Console.readLine("Código alfanumérico:");
                this.theController.registerCodigoAlfaNumerico(codigoAlfaNumerico);
                i++;
            } catch (CodigoAlfaNumericoException codigoAlfaNumericoException) {
                System.out.println("Codigo Alfa Numerico invalido. Por favor insira um codigo valido.");
                i=0;
            }
        }
        i=0;

        while(i==0) {
            try {
                descricao = Console.readLine("Descricao do tipo de equipa:");
                this.theController.registerDescricao(descricao);
                i++;
            } catch (DescricaoException descricaoException) {
                System.out.println("Descrição inválida. Por favor insira uma descrição válida.");
                i=0;
            }
        }

        try {
            this.theController.registerTipoEquipa(cor,codigoAlfaNumerico,descricao);
        } catch (@SuppressWarnings("unused") final IntegrityViolationException | CorException | DescricaoException | CodigoAlfaNumericoException e) {
            System.out.println("Esse código alfanumérico já está a ser usado");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Registar tipo de equipa";
    }
}
