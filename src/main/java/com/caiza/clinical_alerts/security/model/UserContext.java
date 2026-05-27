package com.caiza.clinical_alerts.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserContext {

    private Long userId;
    private List<String> roles;
    private String tenantId;

    public boolean hasRole(String role) {
        return roles != null && roles.contains(role);
    }
}
