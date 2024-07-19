package com.profy.profuru.specification;

import com.profy.profuru.DTO.ExecutorDTO;
import com.profy.profuru.models.Executor;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;
import java.util.List;


public class ExecutorSpecification {
    public static Specification<Executor> byCriteria(ExecutorDTO executorDTO) {
        return (Root<Executor> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();
               if (executorDTO != null) {
                   if (executorDTO.getTitle() != null) {
                       predicates.add(builder.like(builder.lower(root.get("title")), "%" + executorDTO.getTitle().toLowerCase() + "%"));
                   }
               }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}


