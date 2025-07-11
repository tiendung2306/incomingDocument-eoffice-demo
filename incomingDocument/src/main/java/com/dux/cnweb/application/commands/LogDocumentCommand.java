package com.dux.cnweb.application.commands;

import java.util.UUID;

public record LogDocumentCommand(UUID docId, String notebook) {
    
}
