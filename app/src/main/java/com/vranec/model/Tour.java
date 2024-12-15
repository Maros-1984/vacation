package com.vranec.model;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class Tour {

    String detailUrl;
    Hotel hotel;
}
