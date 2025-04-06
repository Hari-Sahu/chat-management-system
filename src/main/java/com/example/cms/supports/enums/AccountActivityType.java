package com.example.cms.supports.enums;

public enum AccountActivityType {

	LOGIN(1),
	FORGOT_PASSWORD(2),
	UPDATE_PASSWORD(3);

	private final int code;

	AccountActivityType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static AccountActivityType fromCode(int code) {
		for (AccountActivityType type : values()) {
			if (type.code == code) {
				return type;
			}
		}
		throw new IllegalArgumentException("Invalid activity type code: " + code);
	}

}
