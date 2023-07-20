package com.models;

public enum RolesModel {
    PROJECT_ADMIN("Project administrator"),
    AGENT_MANAGER("Agent manager"),
    PROJECT_DEVELOPER("Project developer"),
    PROJECT_VIEWER("Project viewer");

    private final String value;

    RolesModel(String value) {
        this.value = value;
    }

    public String getRoleValue() {
        return value;
    }
}

