package com.profy.profuru.models;

import com.profy.profuru.DTO.OrdersDTO;
import com.profy.profuru.repository.BalanceHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Slf4j
public class HistoryService {
    private final BalanceHistoryRepository balanceHistoryRepository;
    ModelMapper modelMapper = new ModelMapper();
    @Transactional(readOnly = true)
    public List<BalanceHistory> findAll() {
        log.info("OrdersService start findAll() ORDERS");
        Iterable<BalanceHistory> orderses = balanceHistoryRepository.findAll();
        List<BalanceHistory> balanceHistories = StreamSupport.stream(orderses.spliterator(), false)
                .map(order -> {
                    return modelMapper.map(order, BalanceHistory.class);
                })
                .collect(Collectors.toList());
        log.info("OrdersService findAll() ORDERS suc Count: {}", balanceHistories.size());
        return balanceHistories;
    }
}
