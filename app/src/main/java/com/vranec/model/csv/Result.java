package com.vranec.model.csv;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Comparator;

@Value
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Result implements Comparable<Result> {

    public static final Comparator<Result> COMPARATOR = Comparator.comparing(Result::getTripAdvisorRating)
            .thenComparing(Result::getPriceCzk)
            .thenComparing(Result::getName);

    @EqualsAndHashCode.Include
    String name;
    int priceCzk;
    BigDecimal tripAdvisorRating;
    int tripAdvisorReviewCount;
    int flightDurationMinutes;
    LocalTime departureTime;
    LocalTime arrivalTime;
    String country;
    String county;
    String city;
    boolean hasTobogan;
    String link;

    @Override
    public int compareTo(@NonNull Result other) {
        return COMPARATOR.compare(this, other);
    }
}
