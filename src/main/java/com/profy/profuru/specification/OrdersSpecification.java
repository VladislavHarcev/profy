package com.profy.profuru.specification;


import com.profy.profuru.DTO.OrdersDTO;
import com.profy.profuru.models.Orders;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class OrdersSpecification {
    public static Specification<Orders> byCriteria(OrdersDTO ordersDTO) {
        return (Root<Orders> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (ordersDTO != null) {
                if (ordersDTO.getStatus() != null) {
                    predicates.add(builder.like(builder.lower(root.get("status")), "%" + ordersDTO.getStatus().name().toLowerCase() + "%"));
                }
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
