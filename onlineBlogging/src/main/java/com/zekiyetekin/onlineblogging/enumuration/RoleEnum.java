package com.zekiyetekin.onlineblogging.enumuration;

public enum RoleEnum {
    USER("user"),
    ADMIN("admin");

    private final String roleName;

    RoleEnum(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
