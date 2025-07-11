package com.dux.cnweb.domain.model.valueObjects;

import java.util.Set;

public enum Role {
    LEADER(Set.of(Capability.DELEGATE,
            Capability.ASSIGN_JOB,
            Capability.RETURN,
            Capability.END_PROCESS,
            Capability.FINISH_JOB,
            Capability.REPORT,
            Capability.COMMENT)),
    COLLABORATOR(Set.of(Capability.DELEGATE,
            Capability.ASSIGN_JOB,
            Capability.RETURN,
            Capability.FINISH_JOB,
            Capability.REPORT)),
    VIEWER(Set.of());

    private final Set<Capability> capabilities;
    
    private Role(Set<Capability> capabilities) {
        this.capabilities = capabilities;
    }

    public static Role fromValue(String name) {
        for (Role role : values()) {
            if (role.name().equalsIgnoreCase(name)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Unknown role: " + name);
    }
}
