package com.profy.profuru.controllers;

import com.profy.profuru.expection.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseWrapper<?>> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        log.warn("EntityNotFoundException: {}", ex.getMessage());
        String errorCode = ErrorCodes.ENTITY_NOT_FOUND.getCode();
        ResponseWrapper<?> response = new ResponseWrapper<>(null, errorCode);
        log.info("EntityNotFoundException handled with code: {}", errorCode);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(NoMandatoryFieldsSet.class)
    public ResponseEntity<ResponseWrapper<?>> handleBadRequestException(NoMandatoryFieldsSet ex, WebRequest request) {
        log.warn("BadRequestException: {}", ex.getMessage());
        String errorCode = ErrorCodes.NO_MANDATORY_FIELDS_SET.getCode();
        ResponseWrapper<?> response = new ResponseWrapper<>(null, errorCode);
        log.info("BadRequestException handled with code: {}", errorCode);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(ClosedOrderException.class)
    public ResponseEntity<ResponseWrapper<?>> handleClosedOrderException(ClosedOrderException ex, WebRequest request){
        log.warn("ClosedOrderException {}", ex.getMessage());
        String errorCode = ErrorCodes.CLOSED_ORDER.getCode();
        ResponseWrapper<?> response = new ResponseWrapper<>(null ,errorCode);
        log.info("ClosedOrderException {}",errorCode);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(WeekendExeption.class)
    public ResponseEntity<ResponseWrapper<?>> handleClosedOrderException(WeekendExeption ex, WebRequest request){
        log.warn("You cannot create orders on weekends {}", ex.getMessage());
        String errorCode = ErrorCodes.WEEKEND.getCode();
        ResponseWrapper<?> response = new ResponseWrapper<>(null ,errorCode);
        log.info("You cannot create orders on weekends {}",errorCode);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
