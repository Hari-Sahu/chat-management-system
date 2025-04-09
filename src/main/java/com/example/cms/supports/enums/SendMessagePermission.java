package com.example.cms.supports.enums;

public enum SendMessagePermission {
    ADMIN_ONLY(0),
    ALL_USERS(1);

    private final int code;

    SendMessagePermission(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static SendMessagePermission fromCode(int code) {
        for (SendMessagePermission permission : values()) {
            if (permission.code == code) {
                return permission;
            }
        }
        throw new IllegalArgumentException("Invalid code for SendMessagePermission: " + code);
    }
}