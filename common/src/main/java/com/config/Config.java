package com.config;

import com.models.User;
import lombok.SneakyThrows;

import java.util.Properties;


public class Config {
    private static Properties envProperties;
    private static User adminUser;


    @SneakyThrows
    private static void readEnv() {
        String env = System.getProperty("env").isEmpty() ? "local" : System.getProperty("env");
        envProperties = new Properties();
        envProperties.load(Config.class.getClassLoader().getResourceAsStream(env + ".properties"));
        envProperties.load(Config.class.getClassLoader().getResourceAsStream("admin_credentials.properties"));
    }

    private static void readAdminUser() {
        adminUser = new User();
        adminUser.setUsername(envProperties.getProperty("username"));
        adminUser.setPassword(envProperties.getProperty("password"));
        adminUser.setToken(envProperties.getProperty("token"));
    }

    public static Properties getEnv() {
        if (envProperties == null) {
            readEnv();
        }
        return envProperties;
    }

    public static User getAdminUser() {
        if (adminUser == null) {
            readEnv();
            readAdminUser();
        }
        return adminUser;
    }
}
