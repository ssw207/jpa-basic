package com.example.jpabasic.domain;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Address;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;

@RequiredArgsConstructor
public enum Role {
	USER("01"),
	ADMIN("02");

	private final String code;

	@Converter
	static class RoleConverter implements AttributeConverter<Role, String> {

		@Override
		public String convertToDatabaseColumn(Role role) {
			return role.code;
		}

		@Override
		public Role convertToEntityAttribute(String code) {
			return Arrays.stream(values())
					.filter(v -> v.code.equals(code))
					.findAny()
					.orElseThrow();
		}
	}
}
