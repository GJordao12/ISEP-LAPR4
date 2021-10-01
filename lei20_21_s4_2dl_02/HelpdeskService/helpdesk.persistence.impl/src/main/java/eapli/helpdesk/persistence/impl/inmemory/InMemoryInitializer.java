/*
 * Copyright (c) 2013-2020 the original author or authors.
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

import eapli.helpdesk.infraestrutura.bootstrapers.HelpdeskBootstrapper;

/**
 * A static initializer to make sure there is the default bootstrapping data, namely power user, when using the in meory
 * repositories. Each repository must class this static initializer, e.g.:
 *
 * <pre>
 * <code>
 * public class InMemoryMaterialRepository extends InMemoryDomainRepository<String, Material>
 *       implements MaterialRepository {
 *
 *    static {
 *      InMemoryInitializer.init();
 *    }
 * }
 * </code>
 * </pre>
 *
 * @author Paulo Gandra de Sousa
 *
 */
final class InMemoryInitializer {

    private static class LazyHolder {
        private static final InMemoryInitializer INSTANCE = new InMemoryInitializer();

        private LazyHolder() {
        }
    }

    private boolean initialized;

    private InMemoryInitializer() {
        // ensure no public instantiation
    }

    private synchronized void initialize() {
        if (!initialized) {
            // to ensure some default test data is available, specially when using
            // in memory persistence
            new HelpdeskBootstrapper().execute();
            initialized = true;
        }
    }

    public static void init() {
        LazyHolder.INSTANCE.initialize();
    }
}
