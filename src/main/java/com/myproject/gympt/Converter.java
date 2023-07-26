package com.myproject.gympt;

public interface Converter<DTO,ENTITY> {
    DTO toDto(ENTITY entity);
    ENTITY toEntity(DTO dto);
}
