package com.dux.cnweb.domain.model.exceptions;

public class InvalidProposalState extends RuntimeException{
    public InvalidProposalState(String message) {
        super(message);
    }
}
