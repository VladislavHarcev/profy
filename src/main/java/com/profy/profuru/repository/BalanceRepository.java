package com.profy.profuru.repository;


import com.profy.profuru.models.Balance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.UUID;

@RepositoryRestResource(exported = false)
public interface BalanceRepository extends CrudRepository<Balance, UUID> {
    Balance findByCustomerId(UUID customerId);
    Balance findByExecutorId(UUID executorId);
}
