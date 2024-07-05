package com.profy.profuru.expection;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}