package com.example.jpabasic.updatetest.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;

@Slf4j
public class PayConverter implements AttributeConverter<Pay, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Pay attribute) {
        try {
            log.info("엔티티의 Pay를 String으로 변환한다 = " + attribute);
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @Override
    public Pay convertToEntityAttribute(String dbData) {
        try {
			log.info("DB에서 읽은 데이터를 Pay로 변환한다 = " + dbData);
            return objectMapper.readValue(dbData, Pay.class);
        } catch (JsonProcessingException e) {
            return new Pay();
        }
    }
}