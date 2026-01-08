package com.rsa.expense.tracker.dto;

import com.rsa.expense.tracker.model.Category;
import com.rsa.expense.tracker.model.ExpenseType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private Long id;
    private Instant createdAt;
    private Instant updatedAt;
    private BigDecimal amount;
    private ExpenseType type;
    private String description;
    private Category category;
}
