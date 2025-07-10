package com.dux.cnweb.domain.model.valueObjects;

import lombok.ToString;

@ToString
public enum Capability {
    DELEGATE,
    ASSIGN_JOB,
    RETURN,
    END_PROCESS,
    FINISH_JOB,
    REPORT,
    COMMENT
}
