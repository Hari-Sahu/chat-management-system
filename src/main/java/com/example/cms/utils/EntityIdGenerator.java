package com.example.cms.utils;

import org.bson.types.ObjectId;

public class EntityIdGenerator {

	private EntityIdGenerator() {
		throw new IllegalStateException("PrimaryKeyGenerator class");
	}
	
	public static String generateId() {
		return new ObjectId().toHexString();
	}
}
