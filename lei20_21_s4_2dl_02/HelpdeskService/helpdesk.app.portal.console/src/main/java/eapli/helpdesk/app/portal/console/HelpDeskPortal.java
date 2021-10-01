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
package eapli.helpdesk.app.portal.console;

import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.eventpubsub.EventDispatcher;
import eapli.helpdesk.app.portal.console.presentation.dashboard.ShowDashboardUI;
import eapli.helpdesk.app.portal.console.presentation.MainMenu;
import eapli.helpdesk.app.common.console.HelpDeskBaseApplication;
import eapli.helpdesk.app.common.console.presentation.authz.LoginUI;
import eapli.helpdesk.colaborador.domain.EmailHandler;
import eapli.helpdesk.infraestrutura.PersistenceContext;
import eapli.helpdesk.user.domain.HelpdeskPasswordPolicy;

import java.io.IOException;

/**
 * @author Paulo Gandra Sousa
 */
@SuppressWarnings("squid:S106")
public final class HelpDeskPortal extends HelpDeskBaseApplication {

    /**
     * avoid instantiation of this class.
     */
    private HelpDeskPortal() {
    }

    /**
     * @param args the command line arguments
     */
    public static void main(final String[] args) throws IOException {

        AuthzRegistry.configure(PersistenceContext.repositories().users(),
                new HelpdeskPasswordPolicy(), new PlainTextEncoder());

        new HelpDeskPortal().run(args);
    }

    @Override
    protected void doMain(final String[] args) {
        // login and go to main menu
        if (new LoginUI().show()) {
            final ShowDashboardUI dashboardUI = new ShowDashboardUI();
            dashboardUI.show();
            // go to main menu
            final var menu = new MainMenu();
            menu.mainLoop();
        }
    }

    @Override
    protected String appTitle() {
        return "Helpdesk Service Portal";
    }

    @Override
    protected String appGoodbye() {
        return "Helpdesk Service Portal";
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doSetupEventHandlers(final EventDispatcher dispatcher) {

    }
}
