/*
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
package eapli.helpdesk.infraestrutura.smoketests;

import eapli.helpdesk.infraestrutura.smoketests.backoffice.*;
import eapli.framework.actions.Action;

/**
 * Execute simple smoke tests on the application layer. We are assuming that the
 * bootstrappers mainly test the "register" use cases and some of the "finders"
 * to support those "register", so these smoke tests will focus mainly on executing the
 * other application methods.
 *
 * @author Paulo Gandra de Sousa
 */
@SuppressWarnings({ "squid:S1126", "java:S106" })
public class HelpdeskDemoSmokeTester implements Action {

    @Override
    public boolean execute() {
        System.out.println("\n\n------- SPECIFIC FEATURES -------");

        new CriticidadeManagementSmokerTester().execute();

        new ServicoManagementSmokerTester().execute();

        return true;
    }
}
