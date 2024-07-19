package com.profy.profuru.service;

import com.profy.profuru.DTO.CustomerDTO;
import com.profy.profuru.expection.EntityNotFoundException;
import com.profy.profuru.models.Customer;
import com.profy.profuru.repository.CustomerRepository;
import com.profy.profuru.specification.CustomerSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Transactional
    public List<CustomerDTO> findAll(CustomerDTO customerDTO) {
        log.info("CustomerService start findAll() CUSTOMERS");
        Iterable<Customer> customers = customerRepository.findAll(CustomerSpecification.byCriteria(customerDTO));
        List<CustomerDTO> customerDTOs = StreamSupport.stream(customers.spliterator(), false)
                .map(customer -> {
                    return modelMapper.map(customer, CustomerDTO.class);
                })
                .collect(Collectors.toList());
        log.info("CustomerService findAll() CUSTOMERS suc Count: {}", customerDTOs.size());
        return customerDTOs;
    }


    @Transactional(readOnly = true)
    public CustomerDTO findById(UUID id) {
        log.info("CustomerService start findDTOById() CUSTOMERS ID: {}", id);
        Customer customer = customerRepository.findById(id).orElseThrow(() -> {
            log.warn("CustomerService findDTOById() CUSTOMER n/f ID: {}", id);
            return new EntityNotFoundException("Customer not found");
        });
        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
        log.info("CustomerService findDTOById() CUSTOMER suc ID: {}", id);
        return customerDTO;
    }

    @Transactional
    public CustomerDTO save(CustomerDTO customerDTO) {
        log.info("CustomerService start saveDTO() CUSTOMER");
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        customerRepository.save(customer);
        log.info("CustomerService saveDTO() CUSTOMER suc ID: {}", customer.getId());
        return modelMapper.map(customer, CustomerDTO.class);
    }

    @Transactional
    public CustomerDTO update(CustomerDTO customerDTO) {
        log.info("CustomerService start updateDTO() CUSTOMER ID: {}", customerDTO.getId());
        Customer updatedCustomer = customerRepository.save(modelMapper.map(customerDTO, Customer.class));
        log.info("CustomerService updateDTO() CUSTOMER suc ID: {}", updatedCustomer.getId());
        return modelMapper.map(updatedCustomer, CustomerDTO.class);
    }

    @Transactional
    public void delete(UUID id) {
        log.info("CustomerService start delete() CUSTOMER ID: {}", id);
        customerRepository.deleteById(id);
        log.info("CustomerService delete() CUSTOMER suc ID: {}", id);
    }

}
