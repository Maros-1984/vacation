package com.vranec.model.csv;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Result {

    String name;
    int priceCzk;
    int flightDurationMinutes;
}
