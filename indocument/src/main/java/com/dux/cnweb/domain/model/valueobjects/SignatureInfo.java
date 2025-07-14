package com.dux.cnweb.domain.model.valueobjects;

import java.util.Date;

import lombok.Value;

@Value(staticConstructor = "of")
public class SignatureInfo {
    String signerName;
    String signerPosition;
    Date signatureDate;
}
