package com.vranec.model.csv;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalTime;

@Value
@Builder
public class Result {

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
    String link;
}
