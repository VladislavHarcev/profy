package com.profy.profuru.converter;

import com.profy.profuru.DTO.CustomerDTO;
import com.profy.profuru.models.Customer;
import org.modelmapper.AbstractConverter;

public class DtoToCustomerConverter extends AbstractConverter<CustomerDTO, Customer> {
    @Override
    protected Customer convert(CustomerDTO source) {
        Customer destination = new Customer();
        destination.setId(source.getId());
        destination.setTitle(source.getTitle());
        destination.setCreateDate(source.getCreateDate());
        return destination;
    }
}
