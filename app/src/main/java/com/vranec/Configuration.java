package com.vranec;

import lombok.Getter;
import lombok.SneakyThrows;

import java.time.LocalTime;
import java.util.List;
import java.util.Properties;

@Getter
public class Configuration {

    private final List<String> listings;
    private final LocalTime minDepartureTime;
    private final LocalTime maxDepartureTime;

    public Configuration() {
        var properties = readConfigurationProperties();
        listings = List.of(properties.getProperty("listings").split(","));
        minDepartureTime = LocalTime.parse(properties.getProperty("min-departure-time"));
        maxDepartureTime = LocalTime.parse(properties.getProperty("max-departure-time"));
    }

    @SneakyThrows
    private Properties readConfigurationProperties() {
        var properties = new Properties();
        properties.load(getClass().getResourceAsStream("/configuration.properties"));
        return properties;
    }
}
