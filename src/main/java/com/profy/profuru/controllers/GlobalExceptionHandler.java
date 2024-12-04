package com.profy.profuru.controllers;

import com.profy.profuru.expection.EntityNotFoundException;
import com.profy.profuru.expection.BadRequestException;
import com.profy.profuru.expection.ResponseWrapper;
import com.profy.profuru.expection.ErrorCodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseWrapper<?>> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        LOGGER.warn("EntityNotFoundException: {}", ex.getMessage());
        String errorCode = ErrorCodes.ENTITY_NOT_FOUND.getCode();
        ResponseWrapper<?> response = new ResponseWrapper<>(null, errorCode);
        LOGGER.info("EntityNotFoundException handled with code: {}", errorCode);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseWrapper<?>> handleBadRequestException(BadRequestException ex, WebRequest request) {
        LOGGER.warn("BadRequestException: {}", ex.getMessage());
        String errorCode = ErrorCodes.BAD_REQUEST.getCode();
        ResponseWrapper<?> response = new ResponseWrapper<>(null, errorCode);
        LOGGER.info("BadRequestException handled with code: {}", errorCode);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
