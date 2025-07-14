package com.dux.cnweb.application.commands;

import java.util.Date;
import java.util.List;

import com.dux.cnweb.domain.model.valueobjects.DocumentPriority;

import lombok.Value;

@Value
public class DistributeDocumentCommand {
    String documentId;
    String leadUnitId;
    List<String> CollaborativeUnitIds;
    List<String> informeeUnitIds;
    Date newDueDate;
    DocumentPriority newDocumentPriority;
}
