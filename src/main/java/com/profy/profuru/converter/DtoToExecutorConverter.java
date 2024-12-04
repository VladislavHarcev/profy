package com.profy.profuru.converter;

import com.profy.profuru.DTO.ExecutorDTO;
import com.profy.profuru.models.Executor;
import org.modelmapper.AbstractConverter;

public class DtoToExecutorConverter extends AbstractConverter<ExecutorDTO, Executor> {
    @Override
    protected Executor convert(ExecutorDTO source) {
        Executor destination = new Executor();
        destination.setId(source.getId());
        destination.setTitle(source.getTitle());
        destination.setCreateDate(source.getCreateDate());
        return destination;
    }
}
