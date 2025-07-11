package com.dux.cnweb.domain.model.valueObjects;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter @EqualsAndHashCode
public class SignerInfo {
    private final String signer;
    private final String signerPosition;
    
    private SignerInfo(String signer, String signerPosition) {
        this.signer = signer;
        this.signerPosition = signerPosition;
    }

    static public SignerInfo of(String signer, String signerPosition) {
        if (signer == null && signerPosition == null) return null;
        else return new SignerInfo(signer, signerPosition);
    }
}
