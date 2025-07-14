package com.dux.cnweb.application.commands;

import com.dux.cnweb.domain.model.valueobjects.DocumentPriority;
import com.dux.cnweb.domain.model.valueobjects.Notebook;
import lombok.Value;

@Value
public class UpdateProductCommand {
    String productId;
    Notebook name;
    DocumentPriority price;
}
