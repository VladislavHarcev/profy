package com.profy.profuru.converter;

import com.profy.profuru.DTO.ExecutorDTO;
import com.profy.profuru.models.Executor;
import org.modelmapper.AbstractConverter;


public class ExecutorToDtoConverter extends AbstractConverter<Executor, ExecutorDTO> {
    @Override
    protected ExecutorDTO convert(Executor source) {
        ExecutorDTO destination = new ExecutorDTO();
        destination.setId(source.getId());
        destination.setTitle(source.getTitle());
        destination.setCreateDate(source.getCreateDate());
        return destination;
    }
}
