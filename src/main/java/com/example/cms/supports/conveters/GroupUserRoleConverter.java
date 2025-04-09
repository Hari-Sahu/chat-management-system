package com.example.cms.supports.conveters;

import com.example.cms.supports.enums.GroupUserRole;

import jakarta.persistence.AttributeConverter;

public class GroupUserRoleConverter implements AttributeConverter<GroupUserRole, Integer> {

	@Override
    public Integer convertToDatabaseColumn(GroupUserRole attribute) {
        return attribute != null ? attribute.getCode() : null;
    }

    @Override
    public GroupUserRole convertToEntityAttribute(Integer dbData) {
        return dbData != null ? GroupUserRole.fromCode(dbData) : null;
    }
}
