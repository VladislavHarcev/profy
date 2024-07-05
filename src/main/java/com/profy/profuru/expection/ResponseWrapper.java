package com.profy.profuru.expection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ResponseWrapper<T> {
    private T content;
    private String errCode;
}
