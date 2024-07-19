package com.profy.profuru.specification;

import com.profy.profuru.DTO.CustomerDTO;
import com.profy.profuru.models.Customer;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;
import java.util.List;


public class CustomerSpecification {
    public static Specification<Customer> byCriteria(CustomerDTO customerDTO) {
        return (Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();
                if (customerDTO != null) {
                    if (customerDTO.getTitle() != null) {
                        predicates.add(builder.like(builder.lower(root.get("title")), "%" + customerDTO.getTitle().toLowerCase() + "%"));
                    }
                }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
