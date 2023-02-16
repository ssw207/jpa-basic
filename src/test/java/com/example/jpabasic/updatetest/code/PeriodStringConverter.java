package com.example.jpabasic.updatetest.code;

import java.time.Period;

import javax.persistence.AttributeConverter;

public class PeriodStringConverter implements AttributeConverter<Period, String> {

	@Override
	public String convertToDatabaseColumn(Period attribute) {
		return attribute.toString();
	}

	@Override
	public Period convertToEntityAttribute(String dbData) {
		return Period.parse(dbData);
	}
}