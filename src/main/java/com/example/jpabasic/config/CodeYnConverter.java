package com.example.jpabasic.config;

import com.example.jpabasic.domain.CodeYn;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class CodeYnConverter implements AttributeConverter<CodeYn, String> {

    @Override
    public String convertToDatabaseColumn(CodeYn attribute) {
        return (CodeYn.Y.equals(attribute)) ? CodeYn.Y.getCode() : CodeYn.N.getCode();
    }

    @Override
    public CodeYn convertToEntityAttribute(String dbData) {
        return CodeYn.Y.getCode().equals(dbData) ? CodeYn.Y : CodeYn.N;
    }
}
