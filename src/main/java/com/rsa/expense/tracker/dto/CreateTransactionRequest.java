package com.rsa.expense.tracker.dto;

import com.rsa.expense.tracker.model.Category;
import com.rsa.expense.tracker.model.ExpenseType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransactionRequest {
    @Positive(message = "message amount must be positive")
    @NotNull(message = "amount can't be null")
    private BigDecimal amount;
    @NotNull(message = "type can't be null")
    private ExpenseType type;
    @NotBlank(message = "description can't be blank")
    private String description;
    @NotNull(message = "category can't be null")
    private Category category;
}
