package com.dux.cnweb.application.commands;

import lombok.Value;

@Value
public class TransferLeadCommand {
    String documentId;
    String newLeadUnitId;
    String reason;
}
