package com.rsa.expense.tracker.dto;

import com.rsa.expense.tracker.model.Category;
import com.rsa.expense.tracker.model.ExpenseType;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTransactionRequest {
    private Category category;
    private ExpenseType type;
    private String description;
    @Positive(message = "amount must be positive")
    private BigDecimal amount;
}
