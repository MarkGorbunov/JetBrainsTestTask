package com;

import lombok.SneakyThrows;

import java.util.Properties;


public class Config {
    private static Properties envProperties;

    @SneakyThrows
    private static void readEnv() {
        String env = System.getProperty("env").isEmpty() ? "local" : System.getProperty("env");
        envProperties = new Properties();
        envProperties.load(Config.class.getClassLoader().getResourceAsStream(env + ".properties"));
    }

    public static Properties getEnv() {
        if (envProperties == null) {
            readEnv();
        }
        return envProperties;
    }
}
