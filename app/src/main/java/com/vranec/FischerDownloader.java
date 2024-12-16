package com.vranec;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vranec.model.csv.Result;
import com.vranec.model.csv.ResultProvider;
import com.vranec.model.fischer.FischerSearchResults;
import com.vranec.model.fischer.InnerFlight;
import com.vranec.model.fischer.Tour;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URI;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class FischerDownloader implements ResultProvider {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final Configuration configuration;

    static {
        OBJECT_MAPPER.findAndRegisterModules();
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private List<Tour> getTours() {
        List<Tour> result = new ArrayList<>();
        for (String listing : configuration.getListings()) {
            result.addAll(getTours(listing));
        }
        return result;
    }

    @SneakyThrows
    private Collection<Tour> getTours(String firstPage) {
        var results = getResults(firstPage);
        List<Tour> result = results.getTours();
        var pages = 1 + (results.getToursCount() - 1) / 20;
        for (int i = 2; i <= pages; i++) {
            result.addAll(getResults(firstPage + "&pitg=" + i * 20).getTours());
        }
        return result;
    }

    private static FischerSearchResults getResults(String url) throws IOException {
        return OBJECT_MAPPER.readValue(URI.create(url).toURL(), FischerSearchResults.class);
    }

    @Override
    public List<Result> getResults() {
        return getTours().stream()
                .map(this::toResult)
                .filter(Objects::nonNull)
                .toList();
    }

    private Result toResult(Tour tour) {
        LocalTime departureTime = tour.getTour().getFlight().getDeparture().getSegments().getFirst().getDate().getFrom().toLocalTime();
        LocalTime arrivalTime = tour.getTour().getFlight().getArrival().getSegments().getFirst().getDate().getFrom().toLocalTime();
        if (isNotValid(departureTime) || isNotValid(arrivalTime)) {
            return null;
        }
        return Result.builder()
                .name(tour.getHotel().getName())
                .priceCzk(tour.getTour().getPrice().getTotal().intValue())
                .tripAdvisorRating(tour.getHotel().getReview().getTripAdvisor().getResult())
                .tripAdvisorReviewCount(tour.getHotel().getReview().getTripAdvisor().getReviewersCount())
                .flightDurationMinutes(getFlightDurationMinutes(tour))
                .departureTime(departureTime)
                .arrivalTime(arrivalTime)
                .country(tour.getHotel().getBreadcrumbs().getCountry())
                .county(tour.getHotel().getBreadcrumbs().getDestination())
                .city(tour.getHotel().getBreadcrumbs().getArea())
                .hasTobogan(tour.getHotel().getTags().contains("Skluzavky a tobogány"))
                .link("https://www.fischer.cz" + tour.getDetailUrl())
                .build();
    }

    private boolean isNotValid(LocalTime time) {
        return time.isBefore(configuration.getMinDepartureTime()) || time.isAfter(configuration.getMaxDepartureTime());
    }

    private static int getFlightDurationMinutes(Tour tour) {
        InnerFlight departure = tour.getTour().getFlight().getDeparture();
        InnerFlight arrival = tour.getTour().getFlight().getArrival();
        return (departure.getDuration() + arrival.getDuration()) / 2;
    }
}
