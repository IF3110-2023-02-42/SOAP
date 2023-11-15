package utils;

import io.github.cdimascio.dotenv.Dotenv;

public class EnviromentHandler {
    private static EnviromentHandler instance = null;
    private final Dotenv env;
    public EnviromentHandler(){
        this.env = Dotenv.load();
    }
    public static EnviromentHandler getInstance(){
        if (instance == null) {
            instance = new EnviromentHandler();
        }
        return instance;
    }
    public String get(String key) {
        return this.env.get(key);
    }
}
