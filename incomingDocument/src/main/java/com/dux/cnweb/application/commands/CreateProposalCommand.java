package com.dux.cnweb.application.commands;

import java.util.UUID;
import java.util.Map;

public record CreateProposalCommand(UUID userId, UUID documentId, Map<UUID, String> assignments) {
    
}
