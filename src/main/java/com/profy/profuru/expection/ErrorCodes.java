package com.profy.profuru.expection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Getter
@AllArgsConstructor
public enum ErrorCodes {
    ENTITY_NOT_FOUND("ENTITY_NOT_FOUND", "Entity not found"),
    NO_MANDATORY_FIELDS_SET("NO_MANDATORY_FIELDS_SET", "Не заданы обязательные поля"),
    CLOSED_ORDER("CLOSED_ORDER", "Order is closed"),
    WEEKEND("WEEKEND", "You cannot create orders on weekends" );
    private final String code;
    private final String message;

}
