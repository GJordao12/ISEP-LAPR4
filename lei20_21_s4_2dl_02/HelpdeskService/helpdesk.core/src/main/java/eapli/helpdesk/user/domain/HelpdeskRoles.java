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
package eapli.helpdesk.user.domain;

import eapli.framework.infrastructure.authz.domain.model.Role;

/**
 * @author Paulo Gandra Sousa
 *
 */
public final class HelpdeskRoles {
    /**
     * System poweruser (this is a technical role).
     */
    public static final Role POWER_USER = Role.valueOf("POWER_USER");

    /**
     * Utente.
     */
    public static final Role CAFETERIA_USER = Role.valueOf("CAFETERIA_USER");

    /**
     * System Administrator.
     */
    public static final Role ADMIN = Role.valueOf("ADMIN");

    /**
     * Kitchen manager.
     */
    public static final Role KITCHEN_MANAGER = Role.valueOf("KITCHEN_MANAGER");

    /**
     * Responsible for planning menus.
     */
    public static final Role MENU_MANAGER = Role.valueOf("MENU_MANAGER");

    /**
     * Responsible for delivering meals and charging user cards.
     */
    public static final Role CASHIER = Role.valueOf("CASHIER");

    /**
     * Human Resources Manager
     */
    public static final Role HR_MANAGER = Role.valueOf("HR_MANAGER");

    /**
     * Service Manager
     */
    public static final Role SERVICE_MANAGER = Role.valueOf("SERVICE_MANAGER");

    /**
     * Collaborator (neither Human Resources Manager nor Service Manager)
     */
    public static final Role COLLABORATOR = Role.valueOf("COLLABORATOR");

    /**
     * Get available role types for cafeteria employees.
     *
     * @return employee roles
     */
    public static Role[] nonUserValues() {
        return new Role[] { ADMIN, KITCHEN_MANAGER, MENU_MANAGER, CASHIER };
    }

    /**
     * Get available role types for cafeteria employees.
     *
     * @return employee roles
     */
    public static Role[] collaboratorValues() {
        return new Role[] { HR_MANAGER, SERVICE_MANAGER, COLLABORATOR };
    }

    /**
     * Checks if a role is one of the employee roles.
     *
     * @param role
     * @return {@code true} if a role is one of the employee roles
     */
    public boolean isCollaborator(final Role role) {
        return role != CAFETERIA_USER;
    }
}
