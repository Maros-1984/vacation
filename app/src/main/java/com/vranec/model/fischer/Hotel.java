package com.vranec.model.fischer;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@Builder
@Jacksonized
public class Hotel {

    String name;
    Breadcrumbs breadcrumbs;
    Review review;
    List<String> tags;
}
