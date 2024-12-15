package com.vranec;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vranec.model.csv.Result;
import com.vranec.model.csv.ResultProvider;
import com.vranec.model.fischer.FisherSearchResults;
import com.vranec.model.fischer.Tour;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
public class FischerDownloader implements ResultProvider {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final String startingUrl;

    static {
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @SneakyThrows
    public List<Tour> getTours() {
        return OBJECT_MAPPER.readValue(URI.create(startingUrl).toURL(), FisherSearchResults.class).getTours();
    }

    @Override
    public List<Result> getResults() {
        return getTours().stream()
                .map(this::toResult)
                .toList();
    }

    private Result toResult(Tour tour) {
        return Result.builder()
                .name(tour.getHotel().getName())
                .price(tour.getTour().getPrice().getTotal().intValue())
                .build();
    }
}
