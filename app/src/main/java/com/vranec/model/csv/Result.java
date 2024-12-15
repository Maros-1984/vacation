package com.vranec.model.csv;

import lombok.Builder;
import lombok.Value;

import java.time.LocalTime;

@Value
@Builder
public class Result {

    String name;
    int priceCzk;
    int flightDurationMinutes;
    LocalTime departureTime;
    LocalTime arrivalTime;
}
