package com.profy.profuru.service;

import com.profy.profuru.models.Balance;
import com.profy.profuru.models.Customer;
import com.profy.profuru.models.Executor;
import com.profy.profuru.repository.BalanceRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BalanceCreation {

    private final BalanceRepository balanceRepository;

    public BalanceCreation(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }

    public void onExecutorSaved(Executor savedExecutor) {
        Balance balance = new Balance();
        balance.setExecutor(savedExecutor);
        balance.setBalance(BigDecimal.ZERO);
        balanceRepository.save(balance);
    }

    public void onCustomerSaved(Customer savedCustomer) {
        Balance balance = new Balance();
        balance.setCustomer(savedCustomer);
        balance.setBalance(BigDecimal.ZERO);
        balanceRepository.save(balance);
    }
}
