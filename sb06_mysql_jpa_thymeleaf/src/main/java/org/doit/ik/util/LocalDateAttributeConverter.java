package org.doit.ik.util;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.AttributeConverter;

// Oracle DATE ↔ Java LocalDate 변환하는 기능이 구현된 클래스
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date>{

	@Override
    public Date convertToDatabaseColumn(LocalDate locDate) {
        return (locDate == null ? null : Date.valueOf(locDate));
    }

    @Override
    public LocalDate convertToEntityAttribute(Date sqlDate) {
        return (sqlDate == null ? null : sqlDate.toLocalDate());
    }

}
