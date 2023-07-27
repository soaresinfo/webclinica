package com.soares.webclinica.mapper;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

@Mapper
public interface CommonsMapper {

    CommonsMapper INSTANCE = Mappers.getMapper(CommonsMapper.class);

    String DATE_FORMAT = "uuuu-MM-dd";

    String DATETIME_FORMAT = "uuuu-MM-dd HH:mm:ss";

    DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATETIME_FORMAT);

    DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    default UUID stringToUUID(final String value){
        return StringUtils.isNotBlank(value) ? UUID.fromString(value) : null;
    }

    default String uuidToString(final UUID value){
        return Objects.nonNull(value) ? value.toString() : null;
    }

    default String localdateToString(final LocalDate value){
        return Objects.nonNull(value) ? DATE_FORMATTER.format(value) : null;
    }

    default LocalDate stringToLocaldate(final String value){
        return StringUtils.isNotBlank(value) ? LocalDate.parse(value, DATE_FORMATTER) : null;
    }

    default LocalDateTime stringToLocalDateTime(final String value){
        return StringUtils.isNotBlank(value) ? LocalDateTime.parse(value, DATE_TIME_FORMATTER) : null;
    }

    default String localdatetimeToString(final LocalDateTime value){
        return Objects.nonNull(value) ? DATE_TIME_FORMATTER.format(value) : null;
    }
}
