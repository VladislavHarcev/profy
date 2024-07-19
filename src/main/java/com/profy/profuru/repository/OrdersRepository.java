package com.profy.profuru.repository;

import com.profy.profuru.models.Orders;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.UUID;

@RepositoryRestResource(exported = false)
public interface OrdersRepository extends CrudRepository<Orders, UUID>, JpaSpecificationExecutor<Orders> {
    List<Orders> findByCustomerId(UUID customer);
    List<Orders> findByExecutorId(UUID executor);
    @Query(value = "SELECT o.id, o.title, c.title AS customer_title, e.title AS executor_title, o.create_date " +
            "FROM orders o " +
            "JOIN customer c ON o.customer_id = c.id " +
            "JOIN executor e ON o.executor_id = e.id " +
            "WHERE o.state = 'CLOSED'", nativeQuery = true)
    List<OrdersStatisticInterface> findStatistic();
}
