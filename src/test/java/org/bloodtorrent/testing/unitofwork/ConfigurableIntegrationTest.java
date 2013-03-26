package org.bloodtorrent.testing.unitofwork;

import com.yammer.dropwizard.config.ConfigurationException;
import com.yammer.dropwizard.config.ConfigurationFactory;
import com.yammer.dropwizard.json.ObjectMapperFactory;
import com.yammer.dropwizard.validation.Validator;
import org.bloodtorrent.bootstrap.SimpleConfiguration;
import org.junit.BeforeClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class ConfigurableIntegrationTest {

    protected static SimpleConfiguration configuration;

    private static String configFileName = "src/main/resources/configurations.yml";

    @BeforeClass
    public static void configureFromFile() throws IOException, ConfigurationException {
        configuration = parseConfiguration(getConfigFileFromProperty(configFileName));
    }

    //this method copied/inspired from DropWizard's ConfiguredCommand.parseConfiguration() private method
    private static SimpleConfiguration parseConfiguration(File configFile) throws IOException, ConfigurationException {
        final ConfigurationFactory<SimpleConfiguration> configurationFactory =
                ConfigurationFactory.forClass(SimpleConfiguration.class, new Validator(), new ObjectMapperFactory());

        return configurationFactory.build(configFile);
    }

    private static File getConfigFileFromProperty(String configFileProperty) throws FileNotFoundException {
        String configFileBySystemProperties = System.getProperty("configFile");
        if(System.getProperty("configFile") != null){
            configFileName = configFileBySystemProperties;
        }
        if (configFileName == null) {
            throw new RuntimeException("must pass DropWizard configuration configFileBySystemProperties as parameter");
        }
        final File configFile = new File(configFileName);
        if (!configFile.exists()) {
            throw new FileNotFoundException("File " + configFile + " not found");
        }
        return configFile;
    }
}
