package com.vranec.model.fischer;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Value
@Builder
@Jacksonized
public class FlightDate {

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    LocalDateTime from;
}
