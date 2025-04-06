package com.example.cms.supports.conveters;

import com.example.cms.supports.enums.AccountActivityType;

import jakarta.persistence.AttributeConverter;

public class AccountActivityTypeConverter implements AttributeConverter<AccountActivityType, Integer> {

	@Override
    public Integer convertToDatabaseColumn(AccountActivityType attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public AccountActivityType convertToEntityAttribute(Integer dbData) {
        return dbData != null ? AccountActivityType.fromCode(dbData) : null;
    }
}
