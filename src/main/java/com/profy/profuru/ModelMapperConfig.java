package com.profy.profuru;


import com.profy.profuru.converter.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(new OrderToDtoConverter());
        modelMapper.addConverter(new DtoToOrderConverter());
        modelMapper.addConverter(new ExecutorToDtoConverter());
        modelMapper.addConverter(new DtoToExecutorConverter());
        modelMapper.addConverter(new CustomerToDtoConverter());
        modelMapper.addConverter(new DtoToCustomerConverter());
        modelMapper.addConverter(new BalanceToDtoConverter());
        modelMapper.addConverter(new DtoToBalanceConverter());
        return modelMapper;
    }
}
