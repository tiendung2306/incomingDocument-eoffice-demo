package com.dux.cnweb.domain.model.valueObjects;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter @EqualsAndHashCode
public class DocContent {
    private final String mainContent;
    private final String note;

    private DocContent(String mainContent, String note) {
        this.mainContent = mainContent;
        this.note = note;
    }

    public static DocContent of(String mainContent, String note) {
        if (mainContent == null) return null;
        else return new DocContent(mainContent, note);
    }
}
