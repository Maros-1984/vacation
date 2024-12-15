package com.vranec.model.fischer;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class Hotel {

    String name;
    Breadcrumbs breadcrumbs;
}
