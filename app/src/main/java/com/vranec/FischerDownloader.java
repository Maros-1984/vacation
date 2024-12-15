package com.vranec;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vranec.model.FisherSearchResults;
import com.vranec.model.Tour;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
public class FischerDownloader {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final String startingUrl;

    static {
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @SneakyThrows
    public List<Tour> getTours() {
        return OBJECT_MAPPER.readValue(URI.create(startingUrl).toURL(), FisherSearchResults.class).getTours();
    }
}
