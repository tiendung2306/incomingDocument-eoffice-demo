package com.dux.cnweb.domain.model.valueObjects;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter @EqualsAndHashCode
public class SignerInfo {
    private final String signer;
    private final String signerPosition;
    
    public SignerInfo(String signer, String signerPosition) {
        this.signer = signer;
        this.signerPosition = signerPosition;
    }

    public boolean signed() {
        return (signer != null);
    }
}
