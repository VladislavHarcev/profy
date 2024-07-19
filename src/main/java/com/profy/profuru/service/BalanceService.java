package com.profy.profuru.service;

import com.profy.profuru.DTO.BalanceDTO;
import com.profy.profuru.expection.EntityNotFoundException;
import com.profy.profuru.models.*;
import com.profy.profuru.repository.BalanceRepository;
import com.profy.profuru.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.UUID;


@RequiredArgsConstructor
@Service
@Slf4j
public class BalanceService {
    private final OrdersRepository ordersRepository;
    private final BalanceRepository balanceRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Transactional
    public void closingOrder(UUID id) {
        Orders order = ordersRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found"));
        Executor executor = order.getExecutor();
        BigDecimal price = order.getPrice();
        Balance balanceEx = executor.getBalance();
        balanceEx.setBalance(balanceEx.getBalance().add(price));
        balanceRepository.save(balanceEx);
    }

    @Transactional
    public BalanceDTO save(BalanceDTO balanceDTO) {
        Balance balance = modelMapper.map(balanceDTO, Balance.class);
        BalanceDTO savedBalance = modelMapper.map(balanceRepository.save(balance), BalanceDTO.class);
        log.info("BalanceService create new balance. Id: {}, customerID: {}, executorId: {}",
                balance.getId(),
                balance.getCustomer() != null ? balance.getCustomer().getId() : "null",
                balance.getExecutor() != null ? balance.getExecutor().getId() : "null");
        return savedBalance;
    }
}

