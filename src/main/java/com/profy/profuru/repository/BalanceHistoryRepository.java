package com.profy.profuru.repository;

import com.profy.profuru.models.BalanceHistory;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BalanceHistoryRepository extends CrudRepository<BalanceHistory, UUID> {
}
