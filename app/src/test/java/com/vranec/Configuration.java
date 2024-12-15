package com.vranec;

import java.util.List;
import java.util.Properties;

public class Configuration {

    private final List<String> listings;

    public Configuration() {
        var properties = new Properties();

        try {
            properties.load(getClass().getResourceAsStream("/configuration.properties"));
            listings = List.of(properties.getProperty("listings").split(","));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getListings() {
        return listings;
    }
}
