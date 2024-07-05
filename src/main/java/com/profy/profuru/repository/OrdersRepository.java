package com.profy.profuru.repository;

import com.profy.profuru.models.Orders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource(exported = false)
public interface OrdersRepository extends CrudRepository<Orders, UUID> {
    List<Orders> findByCustomerId(UUID customer);
    List<Orders> findByExecutorId(UUID executor);
}
