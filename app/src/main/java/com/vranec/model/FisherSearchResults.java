package com.vranec.model;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@Builder
@Jacksonized
public class FisherSearchResults {

    List<Tour> tours;
}
