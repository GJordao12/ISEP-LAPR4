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
package eapli.helpdesk;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The application settings.
 *
 * @author Paulo Gandra Sousa
 */
public class AppSettings {
    private static final Logger LOGGER = LogManager.getLogger(AppSettings.class);

    private static final String PROPERTIES_RESOURCE = "application.properties";
    private static final String REPOSITORY_FACTORY_KEY = "persistence.repositoryFactory";
    private static final String UI_MENU_LAYOUT_KEY = "ui.menu.layout";
    private static final String PERSISTENCE_UNIT_KEY = "persistence.persistenceUnit";
    private static final String SERVER_IP_KEY = "serverIP";
    private static final String SERVER_PORT_KEY = "serverPort";
    private static final String KEYS_STORE_PASS_KEY = "keys_store_pass";
    private static final String TRUSTED_STORE_KEY = "trusted_store";
    private static final String SCHEMA_GENERATION_KEY = "javax.persistence.schema-generation.database.action";
    private static final String USE_EVENTFUL_CONTROLLERS = "UseEventfulControllers";
    private static final String SERVER_MOTOR_IP_KEY = "serverIPMotor";
    private static final String SERVER_MOTOR_PORT_KEY = "serverPortMotor";
    private static final String KEYS_STORE_PASS_MOTOR_KEY = "keys_store_pass_motor";
    private static final String AUTOMATIC_TASK_ASSIGNMENT_OPTION = "automatic_task_assignment_option";
    private static final String ALGORITHM_NUMBER_KEY = "algorithm_number";

    private final Properties applicationProperties = new Properties();

    public AppSettings() {
        loadProperties();
    }

    private void loadProperties() {
        try (InputStream propertiesStream = this.getClass().getClassLoader()
                .getResourceAsStream(PROPERTIES_RESOURCE)) {
            if (propertiesStream != null) {
                this.applicationProperties.load(propertiesStream);
            } else {
                throw new FileNotFoundException(
                        "property file '" + PROPERTIES_RESOURCE + "' not found in the classpath");
            }
        } catch (final IOException exio) {
            setDefaultProperties();

            LOGGER.warn("Loading default properties", exio);
        }
    }

    private void setDefaultProperties() {
        this.applicationProperties.setProperty(REPOSITORY_FACTORY_KEY,
                "eapli.helpdesk.persistence.jpa.JpaRepositoryFactory");
        this.applicationProperties.setProperty(UI_MENU_LAYOUT_KEY, "horizontal");
        this.applicationProperties.setProperty(PERSISTENCE_UNIT_KEY, "eapli.HelpdeskPU");
        this.applicationProperties.setProperty(SERVER_IP_KEY, "localhost");
        this.applicationProperties.setProperty(SERVER_PORT_KEY, "22");
        this.applicationProperties.setProperty(KEYS_STORE_PASS_KEY, "SEM_PASSE");
    }

    public boolean isMenuLayoutHorizontal() {
        return "horizontal"
                .equalsIgnoreCase(this.applicationProperties.getProperty(UI_MENU_LAYOUT_KEY));
    }

    public String getPersistenceUnitName() {
        return this.applicationProperties.getProperty(PERSISTENCE_UNIT_KEY);
    }

    public String getRepositoryFactory() {
        return this.applicationProperties.getProperty(REPOSITORY_FACTORY_KEY);
    }

    public String getServerIpKey() {
        return this.applicationProperties.getProperty(SERVER_IP_KEY);
    }

    public int getServerPortKey() {
        return Integer.parseInt(this.applicationProperties.getProperty(SERVER_PORT_KEY));
    }

    public String getKeyStorePass() {
        return this.applicationProperties.getProperty(KEYS_STORE_PASS_KEY);
    }

    public String getTrustedStore() {
        return this.applicationProperties.getProperty(TRUSTED_STORE_KEY);
    }

    public String getServerIpMotorKey() {
        return this.applicationProperties.getProperty(SERVER_MOTOR_IP_KEY);
    }

    public String getKeyStorePassMotor() {
        return this.applicationProperties.getProperty(KEYS_STORE_PASS_MOTOR_KEY);
    }

    public int getServerPortMotorKey() {
        return Integer.parseInt(this.applicationProperties.getProperty(SERVER_MOTOR_PORT_KEY));
    }

    public int getAutomaticTaskAssignmentOption() {
        return Integer.parseInt(this.applicationProperties.getProperty(AUTOMATIC_TASK_ASSIGNMENT_OPTION));
    }

    public int getAlgorithmNumber() {
        return Integer.parseInt(this.applicationProperties.getProperty(ALGORITHM_NUMBER_KEY));
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getExtendedPersistenceProperties() {
        final Map ret = new HashMap();
        ret.put(SCHEMA_GENERATION_KEY,
                this.applicationProperties.getProperty(SCHEMA_GENERATION_KEY));
        return ret;
    }

    public String getProperty(final String prop) {
        return this.applicationProperties.getProperty(prop);
    }

    public boolean getUseEventfulControllers() {
        return Boolean.valueOf(this.applicationProperties.getProperty(USE_EVENTFUL_CONTROLLERS));
    }
}
