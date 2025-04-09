package com.example.cms.supports.enums;

public enum GroupUserRole {
	NON_ADMIN(0),
    ADMIN(1);

    private final int code;

    GroupUserRole(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static GroupUserRole fromCode(int code) {
        for (GroupUserRole role : values()) {
            if (role.code == code) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid GroupUserRole code: " + code);
    }
}