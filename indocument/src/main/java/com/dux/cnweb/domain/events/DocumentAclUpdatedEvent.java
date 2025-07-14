package com.dux.cnweb.domain.events;

import java.util.List;

import com.dux.cnweb.domain.model.valueobjects.AccessControlList;
import com.dux.cnweb.shared.domain.events.DomainEvent;

import lombok.Getter;

@Getter
public class DocumentAclUpdatedEvent extends DomainEvent {
    List<AccessControlList> acl;

    public DocumentAclUpdatedEvent(List<AccessControlList> acl) {
        this.acl = acl;
    }
}
