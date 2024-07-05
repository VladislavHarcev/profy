package com.profy.profuru.expection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Getter
@AllArgsConstructor
public enum ErrorCodes {
    ENTITY_NOT_FOUND("ENTITY_NOT_FOUND", "Entity not found"),
    BAD_REQUEST("BAD_REQUEST", "Bad request");
    private final String code;
    private final String message;

}
