package com.profy.profuru.specification;

import com.profy.profuru.models.Executor;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExecutorSpecification {

    public static Specification<Executor> byCriteria(Map<String, Object> criteria) {
        return (Root<Executor> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            criteria.forEach((field, value) -> {
                if (value != null) {
                    predicates.add(builder.equal(root.get(field), value));
                }
            });
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}


