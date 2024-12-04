package com.profy.profuru.service;

import com.profy.profuru.DTO.OrdersDTO;
import com.profy.profuru.expection.EntityNotFoundException;
import com.profy.profuru.models.*;
import com.profy.profuru.repository.BalanceRepository;
import com.profy.profuru.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.UUID;


@RequiredArgsConstructor
@Service
public class BalanceService {
    private final OrdersRepository ordersRepository;
    private final BalanceRepository balanceRepository;

    public void updateExBalanceByOrdId(UUID id, OrderStatus newStatus) {
        if (newStatus == OrderStatus.CLOSED) {
            Orders order = ordersRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found"));;
            Executor executor = order.getExecutor();
            BigDecimal price = order.getPrice();
            Balance balance = executor.getBalance();
            balance.setBalance(balance.getBalance().add(price));
            balanceRepository.save(balance);
        }
    }

    public void updateExBalanceByOrdDto(OrdersDTO ordersDTO) {
        if (ordersDTO.getStatus() == OrderStatus.CLOSED) {

            Orders order = ordersRepository.findById(ordersDTO.getId()).orElseThrow(() -> new EntityNotFoundException("Order not found"));;
            Executor executor = order.getExecutor();
            BigDecimal price = order.getPrice();
            Balance balance = executor.getBalance();
            balance.setBalance(balance.getBalance().add(price));
            balanceRepository.save(balance);
        }
    }
}

