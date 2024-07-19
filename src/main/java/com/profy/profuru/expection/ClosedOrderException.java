package com.profy.profuru.expection;

public class ClosedOrderException extends RuntimeException{
    public ClosedOrderException(String message) {
        super(message);
    }
}
