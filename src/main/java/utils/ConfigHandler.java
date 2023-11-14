package utils;

import lombok.Getter;

@Getter
public class ConfigHandler {
    private static ConfigHandler instance = null;
    private PropertiesHandler ph;
    private EnviromentHandler env;
    private final String CONFIG_FILE_DOCKER = "config.docker.properties";
    private final String CONFIG_FILE = "config.properties";
    private final String USE_DOCKER_CONFIGURATION = "USE_DOCKER_CONFIG";

    private ConfigHandler() {
        this.env = EnviromentHandler.getInstance();
        String isUsingDockerConfig = env.get(USE_DOCKER_CONFIGURATION);
        if(isUsingDockerConfig == null || isUsingDockerConfig.equals("false")){
            this.ph = new PropertiesHandler(CONFIG_FILE);
        } else {
            this.ph = new PropertiesHandler(CONFIG_FILE_DOCKER);
        }
    }

    public static ConfigHandler getInstance() {
        if (instance == null) {
            instance = new ConfigHandler();
        }
        return instance;
    }

    public String get(String key) {
        return this.ph.get(key);
    }
}
