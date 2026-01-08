package com.rsa.expense.tracker.dto;

import com.rsa.expense.tracker.model.Category;
import com.rsa.expense.tracker.model.ExpenseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionSearch {
    private PageRequest pageRequest;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Category category;
    private ExpenseType type;
    private Long userId;
}
