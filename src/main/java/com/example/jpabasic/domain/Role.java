package com.example.jpabasic.domain;

import java.util.Arrays;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
	USER("01"),
	ADMIN("02");

	private final String code;

	@Converter
	static class RoleConverter implements AttributeConverter<Role, String> {

		@Override
		public String convertToDatabaseColumn(Role role) {
			if (role == null) {
				return Role.USER.code;
			}

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
