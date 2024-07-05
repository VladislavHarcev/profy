package com.profy.profuru.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
    CREATED, IN_PROGRESS, CLOSED;
}

