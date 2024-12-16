package com.vranec.model.fischer;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@Builder
@Jacksonized
public class FischerSearchResults {

    List<Tour> tours;
    int toursCount;
}
