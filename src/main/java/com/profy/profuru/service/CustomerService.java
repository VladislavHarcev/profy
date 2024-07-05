package com.profy.profuru.service;

import com.profy.profuru.DTO.BalanceDTO;
import com.profy.profuru.DTO.CustomerDTO;
import com.profy.profuru.DTO.OrdersDTO;
import com.profy.profuru.expection.EntityNotFoundException;
import com.profy.profuru.models.Customer;
import com.profy.profuru.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
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
    private final ModelMapper modelMapper;
    private final BalanceCreation balanceCreation;

    @Transactional
    public List<CustomerDTO> findAll() {
        log.info("CustomerService start findAll() CUSTOMERS");
        Iterable<Customer> customers = customerRepository.findAll();
        List<CustomerDTO> customerDTOs = StreamSupport.stream(customers.spliterator(), false)
                .map(customer -> {
                    CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
                    BalanceDTO balanceDTO = modelMapper.map(customer.getBalance(), BalanceDTO.class);
                    balanceDTO.setCustomer(customerDTO);
                    customerDTO.setBalance(balanceDTO);
                    return customerDTO;
                })
                .collect(Collectors.toList());
        log.info("CustomerService findAll() CUSTOMERS suc Count: {}", customerDTOs.size());
        return customerDTOs;
    }


    @Transactional(readOnly = true)
    public CustomerDTO findDTOById(UUID id) {
        log.info("CustomerService start findDTOById() CUSTOMERS ID: {}", id);
        Customer customer = customerRepository.findById(id).orElseThrow(() -> {
            log.warn("CustomerService findDTOById() CUSTOMER n/f ID: {}", id);
            return new EntityNotFoundException("Customer not found");
        });
        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
        BalanceDTO balanceDTO = modelMapper.map(customer.getBalance(), BalanceDTO.class);
        balanceDTO.setCustomer(customerDTO);
        customerDTO.setBalance(balanceDTO);
        if (customer.getOrders() != null) {
            Hibernate.initialize(customer.getOrders());
            List<OrdersDTO> ordersDtoList = customer.getOrders().stream()
                    .map(order -> modelMapper.map(order, OrdersDTO.class))
                    .collect(Collectors.toList());
            customerDTO.setOrders(ordersDtoList);
        }

        log.info("CustomerService findDTOById() CUSTOMER suc ID: {}", id);
        return customerDTO;
    }

    @Transactional
    public CustomerDTO saveDTO(CustomerDTO customerDTO) {
        log.info("CustomerService start saveDTO() CUSTOMER");
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        Customer savedCustomer = customerRepository.save(customer);
        balanceCreation.onCustomerSaved(savedCustomer);
        log.info("CustomerService saveDTO() CUSTOMER suc ID: {}", savedCustomer.getId());
        return modelMapper.map(savedCustomer, CustomerDTO.class);
    }


    @Transactional
    public CustomerDTO updateDTO(CustomerDTO customerDTO) {
        log.info("CustomerService start updateDTO() CUSTOMER ID: {}", customerDTO.getId());
        Customer existingCustomer = customerRepository.findById(customerDTO.getId()).orElseThrow(() -> {
            log.warn("CustomerService updateDTO() CUSTOMER n/f ID: {}", customerDTO.getId());
            return new EntityNotFoundException("Customer not found");
        });

        BeanUtils.copyProperties(customerDTO, existingCustomer, "createDate");
        Customer updatedCustomer = customerRepository.save(existingCustomer);
        log.info("CustomerService updateDTO() CUSTOMER suc ID: {}", updatedCustomer.getId());
        return modelMapper.map(updatedCustomer, CustomerDTO.class);
    }

    @Transactional
    public void delete(UUID id) {
        log.info("CustomerService start delete() CUSTOMER ID: {}", id);
        customerRepository.deleteById(id);
        log.info("CustomerService delete() CUSTOMER suc ID: {}", id);
    }

    @Transactional(readOnly = true)
    public List<CustomerDTO> findByTitle(String titlePart){
        log.info("CustomerService start findByNamePart() CUSTOMERS NamePart: {}", titlePart);
        List<Customer> customers = customerRepository.findByTitleContainingIgnoreCase(titlePart);
        List<CustomerDTO> customerDTOs = customers.stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class)).toList();
        log.info("CustomerService findByNamePart() CUSTOMERS suc Count: {}", customerDTOs.size());
        return customerDTOs;
    }

}
