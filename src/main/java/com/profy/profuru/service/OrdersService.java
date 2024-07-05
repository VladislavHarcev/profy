package com.profy.profuru.service;

import com.profy.profuru.DTO.CustomerDTO;
import com.profy.profuru.DTO.ExecutorDTO;
import com.profy.profuru.DTO.OrdersDTO;
import com.profy.profuru.expection.EntityNotFoundException;
import com.profy.profuru.models.*;
import com.profy.profuru.repository.CustomerRepository;
import com.profy.profuru.repository.ExecutorRepository;
import com.profy.profuru.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final CustomerRepository customerRepository;
    private final ExecutorRepository executorRepository;
    private final ModelMapper modelMapper;
    private final BalanceService balanceService;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional(readOnly = true)
    public List<OrdersDTO> findAll() {
        log.info("OrdersService start findAll() ORDERS");
        Iterable<Orders> orderses = ordersRepository.findAll();
        List<OrdersDTO> ordersDTOS = StreamSupport.stream(orderses.spliterator(), false)
                .map(order -> {
                    OrdersDTO dto = modelMapper.map(order, OrdersDTO.class);
                    ExecutorDTO executorDto = new ExecutorDTO();
                    CustomerDTO customerDto = new CustomerDTO();

                    if (order.getExecutor() != null) {
                        Executor executor = executorRepository.findById(order.getExecutor().getId()).orElseThrow(() ->
                                new EntityNotFoundException("Executor not found"));
                        executorDto.setId(executor.getId());
                        executorDto.setTitle(executor.getTitle());
                        dto.setExecutor(executorDto);
                    }
                    if (order.getCustomer() != null) {
                        Customer customer = customerRepository.findById(order.getCustomer().getId()).orElseThrow(() ->
                                new EntityNotFoundException("Customer not found"));
                        customerDto.setId(customer.getId());
                        customerDto.setTitle(customer.getTitle());
                        dto.setCustomer(customerDto);
                    }
                    return dto;
                })
                .collect(Collectors.toList());
        log.info("OrdersService findAll() ORDERS suc Count: {}", ordersDTOS.size());
        return ordersDTOS;
    }


    @Transactional(readOnly = true)
    public OrdersDTO findDTOById(UUID id) {
        log.info("OrdersService start findDTOById() ORDERS ID: {}", id);
        Orders orders = ordersRepository.findById(id).orElseThrow(() -> {
            log.warn("OrdersService findDTOById() ORDER n/f ID: {}", id);
            return new EntityNotFoundException("Order not found");
        });
        OrdersDTO dto = modelMapper.map(orders, OrdersDTO.class);

        ExecutorDTO executorDto = new ExecutorDTO();
        CustomerDTO customerDto = new CustomerDTO();

        if (orders.getExecutor() != null) {
            Executor executor = executorRepository.findById(orders.getExecutor().getId()).orElseThrow(() ->
                    new EntityNotFoundException("Executor not found"));
            executorDto.setId(executor.getId());
            executorDto.setTitle(executor.getTitle());
        }
        if (orders.getCustomer() != null) {
            Customer customer = customerRepository.findById(orders.getCustomer().getId()).orElseThrow(() ->
                    new EntityNotFoundException("Customer not found"));
            customerDto.setId(customer.getId());
            customerDto.setTitle(customer.getTitle());
        }
        dto.setExecutor(executorDto);
        dto.setCustomer(customerDto);

        log.info("OrdersService findDTOById() ORDER suc ID: {}", id);
        return dto;
    }


    @Transactional
    public OrdersDTO saveDTO(OrdersDTO ordersDTO) {
        log.info("OrdersService start saveDTO() ORDER");
        Orders orders = modelMapper.map(ordersDTO, Orders.class);
        orders.setStatus(OrderStatus.CREATED);
        CustomerDTO customerDTO = null;
        ExecutorDTO executorDTO = null;
        UUID customerId = ordersDTO.getCustomer().getId();
        if (customerId != null) {
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
            customerDTO = new CustomerDTO();
            customerDTO.setId(customer.getId());
            customerDTO.setTitle(customer.getTitle());
            customerDTO.setCreateDate(customer.getCreateDate());
            orders.setCustomer(customer);
        }
        UUID executorId = ordersDTO.getExecutor().getId();
        if (executorId != null) {
            Executor executor = executorRepository.findById(executorId)
                    .orElseThrow(() -> new EntityNotFoundException("Executor not found"));
            executorDTO = new ExecutorDTO();
            executorDTO.setId(executor.getId());
            executorDTO.setTitle(executor.getTitle());
            executorDTO.setCreateDate(executor.getCreateDate());
            orders.setExecutor(executor);
        }
        Orders savedOrders = ordersRepository.save(orders);
        log.info("OrdersService saveDTO() ORDER suc ID: {}", savedOrders.getId());
        OrdersDTO resultDTO = modelMapper.map(savedOrders, OrdersDTO.class);
        resultDTO.setCustomer(customerDTO);
        resultDTO.setExecutor(executorDTO);

        return resultDTO;
    }

    @Transactional
    public OrdersDTO updateDTO(OrdersDTO ordersDTO) {
        log.info("OrdersService start updateDTO() ORDER ID: {}", ordersDTO.getId());
        Orders existingOrders = ordersRepository.findById(ordersDTO.getId())
                .orElseThrow(() -> {
                    log.warn("OrdersService updateDTO() ORDER n/f ID: {}", ordersDTO.getId());
                    return new EntityNotFoundException("Order not found");
                });

        if (existingOrders.getStatus() == OrderStatus.CLOSED) {
            throw new IllegalStateException("Cannot change the status of a closed order.");
        }
        existingOrders.setTitle(ordersDTO.getTitle());
        existingOrders.setDescript(ordersDTO.getDescript());
        existingOrders.setPrice(ordersDTO.getPrice());
        UUID customerId = ordersDTO.getCustomer() != null ? ordersDTO.getCustomer().getId() : null;
        if (customerId != null) {
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
            existingOrders.setCustomer(customer);
        }

        UUID executorId = ordersDTO.getExecutor() != null ? ordersDTO.getExecutor().getId() : null;
        if (executorId != null) {
            Executor executor = executorRepository.findById(executorId)
                    .orElseThrow(() -> new EntityNotFoundException("Executor not found"));
            existingOrders.setExecutor(executor);
        }
        if (ordersDTO.getStatus() != null) {
                existingOrders.setStatus(ordersDTO.getStatus());
        }
        Orders updatedOrders = ordersRepository.save(existingOrders);
        log.info("OrdersService updateDTO() ORDER suc ID: {}", updatedOrders.getId());
        OrdersDTO resultDTO = modelMapper.map(updatedOrders, OrdersDTO.class);
        if (customerId != null) {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setId(customerId);
            resultDTO.setCustomer(customerDTO);
        }
        if (executorId != null) {
            ExecutorDTO executorDTO = new ExecutorDTO();
            executorDTO.setId(executorId);
            resultDTO.setExecutor(executorDTO);
        }
        return resultDTO;
    }

    @Transactional
    public void deleteOrder(UUID id) {
        log.info("OrdersService start deleteOrder() ORDER ID: {}", id);
        ordersRepository.deleteById(id);
        log.info("OrdersService deleteOrder() ORDER suc ID: {}", id);
    }

    @Transactional
    public void updateOrderStatus(UUID id, OrderStatus newStatus) {
        log.info("OrdersService start updateOrderStatus() ORDER ID: {}", id);
        Orders order = ordersRepository.findById(id).orElseThrow(() -> {
            log.warn("OrdersService updateOrderStatus() ORDER n/f ID: {}", id);
            return new EntityNotFoundException("Order not found");
        });
        if (order.getStatus() == OrderStatus.CLOSED) {
            throw new IllegalStateException("Cannot change a closed order.");
        } else {
            order.setStatus(newStatus);
            ordersRepository.save(order);
            log.info("OrdersService updateOrderStatus() ORDER suc ID: {}", order.getId());
        }
    }

}
