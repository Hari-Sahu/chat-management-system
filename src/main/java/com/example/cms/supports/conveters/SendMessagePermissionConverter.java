package com.example.cms.supports.conveters;

import com.example.cms.supports.enums.SendMessagePermission;

import jakarta.persistence.AttributeConverter;

public class SendMessagePermissionConverter implements AttributeConverter<SendMessagePermission, Integer> {

	@Override
    public Integer convertToDatabaseColumn(SendMessagePermission attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public SendMessagePermission convertToEntityAttribute(Integer dbData) {
        return dbData != null ? SendMessagePermission.fromCode(dbData) : null;
    }
}
