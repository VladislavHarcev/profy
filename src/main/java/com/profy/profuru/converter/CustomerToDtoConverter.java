package com.profy.profuru.converter;

import com.profy.profuru.DTO.CustomerDTO;
import com.profy.profuru.models.Customer;
import org.modelmapper.AbstractConverter;

public class CustomerToDtoConverter extends AbstractConverter<Customer, CustomerDTO> {
    @Override
    protected CustomerDTO convert(Customer source) {
        CustomerDTO destination = new CustomerDTO();
        destination.setId(source.getId());
        destination.setTitle(source.getTitle());
        destination.setCreateDate(source.getCreateDate());
        return destination;
    }
}
