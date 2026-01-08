package com.rsa.expense.tracker.repository;

import com.rsa.expense.tracker.dto.TransactionSearch;
import com.rsa.expense.tracker.model.Transaction;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TransactionSpecification {

    public static Specification<Transaction> withParameters(TransactionSearch search) {
        List<Specification<Transaction>> specifications = new ArrayList<>();

        if (Objects.nonNull(search.getDateFrom())) {
            specifications.add((root, query, criteriaBuilder) -> criteriaBuilder
                    .greaterThanOrEqualTo(root.get("createdAt"), search.getDateFrom()));
        }

        if (Objects.nonNull(search.getDateTo())) {
            specifications.add((root, query, criteriaBuilder) -> criteriaBuilder
                    .lessThanOrEqualTo(root.get("createdAt"), search.getDateTo()));
        }

        if (Objects.nonNull(search.getCategory())) {
            specifications.add((root, query, criteriaBuilder) -> criteriaBuilder
                    .equal(root.get("category"), search.getCategory()));
        }

        if (Objects.nonNull(search.getType())) {
            specifications.add((root, query, criteriaBuilder) -> criteriaBuilder
                    .equal(root.get("type"), search.getType()));
        }

        if (Objects.nonNull(search.getUserId())) {
            specifications.add((root, query, criteriaBuilder) -> criteriaBuilder
                    .equal(root.get("user").get("id"), search.getUserId()));
        }

        return Specification.allOf(specifications);
    }

}
