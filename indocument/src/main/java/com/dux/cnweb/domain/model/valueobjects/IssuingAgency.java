package com.dux.cnweb.domain.model.valueobjects;

import lombok.Value;

@Value(staticConstructor = "of")
public class IssuingAgency {
    String agencyId;
}
