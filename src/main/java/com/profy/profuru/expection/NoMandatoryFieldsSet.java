package com.profy.profuru.expection;

public class NoMandatoryFieldsSet extends RuntimeException {
    public NoMandatoryFieldsSet(String message) {
        super(message);
    }
}
