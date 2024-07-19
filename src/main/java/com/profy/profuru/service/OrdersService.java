package com.profy.profuru.service;

import com.profy.profuru.DTO.OrdersDTO;
import com.profy.profuru.expection.EntityNotFoundException;
import com.profy.profuru.models.*;
import com.profy.profuru.repository.OrdersRepository;
import com.profy.profuru.repository.OrdersStatisticInterface;
import com.profy.profuru.specification.OrdersSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper = new ModelMapper();

    @Transactional(readOnly = true)
    public List<OrdersDTO> findAll(OrdersDTO ordersDTO) {
        log.info("OrdersService start findAll() ORDERS");
        Iterable<Orders> orderses = ordersRepository.findAll(OrdersSpecification.byCriteria(ordersDTO));
        List<OrdersDTO> ordersDTOS = StreamSupport.stream(orderses.spliterator(), false)
                .map(order -> {
                    return modelMapper.map(order, OrdersDTO.class);
                })
                .collect(Collectors.toList());
        log.info("OrdersService findAll() ORDERS suc Count: {}", ordersDTOS.size());
        return ordersDTOS;
    }


    @Transactional(readOnly = true)
    public OrdersDTO findById(UUID id) {
        log.info("OrdersService start findDTOById() ORDERS ID: {}", id);
        Orders orders = ordersRepository.findById(id).orElseThrow(() -> {
            log.warn("OrdersService findDTOById() ORDER n/f ID: {}", id);
            return new EntityNotFoundException("Order not found");
        });
        OrdersDTO orderDTO = modelMapper.map(orders, OrdersDTO.class);
        log.info("OrdersService findDTOById() ORDER suc ID: {}", id);
        return orderDTO;
    }


    @Transactional
    public OrdersDTO save(OrdersDTO ordersDTO) {
        log.info("OrdersService start saveDTO() ORDER");
        Orders orders = modelMapper.map(ordersDTO, Orders.class);
        Orders savedOrders = ordersRepository.save(orders);
        log.info("OrdersService saveDTO() ORDER suc ID: {}", savedOrders.getId());
        return modelMapper.map(savedOrders, OrdersDTO.class);
    }

    @Transactional
    public OrdersDTO update(OrdersDTO ordersDTO) {
        log.info("OrdersService start updateDTO() ORDER ID: {}", ordersDTO.getId());
        Orders updatedOrder = ordersRepository.save(modelMapper.map(ordersDTO, Orders.class));
        log.info("OrdersService updateDTO() ORDER suc ID: {}", ordersDTO.getId());
        return modelMapper.map(updatedOrder, OrdersDTO.class);
    }

    @Transactional
    public void delete(UUID id) {
        log.info("OrdersService start deleteOrder() ORDER ID: {}", id);
        ordersRepository.deleteById(id);
        log.info("OrdersService deleteOrder() ORDER suc ID: {}", id);
    }

    public List<OrdersStatisticInterface> findStatistic(){
        return ordersRepository.findStatistic();
    }

}
