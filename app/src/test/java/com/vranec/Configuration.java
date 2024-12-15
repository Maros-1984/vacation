package com.vranec;

import lombok.Getter;
import lombok.SneakyThrows;

import java.util.List;
import java.util.Properties;

@Getter
public class Configuration {

    private final List<String> listings;

    public Configuration() {
        var properties = readConfigurationProperties();
        listings = List.of(properties.getProperty("listings").split(","));
    }

    @SneakyThrows
    private Properties readConfigurationProperties() {
        var properties = new Properties();
        properties.load(getClass().getResourceAsStream("/configuration.properties"));
        return properties;
    }
}
