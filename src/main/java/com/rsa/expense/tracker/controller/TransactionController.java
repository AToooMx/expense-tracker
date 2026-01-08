package com.rsa.expense.tracker.controller;

import com.rsa.expense.tracker.config.security.IsUser;
import com.rsa.expense.tracker.dto.CreateTransactionRequest;
import com.rsa.expense.tracker.dto.TransactionDto;
import com.rsa.expense.tracker.dto.TransactionSearch;
import com.rsa.expense.tracker.dto.UpdateTransactionRequest;
import com.rsa.expense.tracker.model.Category;
import com.rsa.expense.tracker.model.ExpenseType;
import com.rsa.expense.tracker.model.User;
import com.rsa.expense.tracker.service.transaction.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@IsUser
@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
@Tag(name = "Transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @Operation(summary = "Create transaction")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDto createTransaction(@AuthenticationPrincipal User user,
                                            @Valid @RequestBody CreateTransactionRequest request) {
        return transactionService.create(user, request);
    }

    @Operation(summary = "Find transaction by id")
    @GetMapping("/{transactionId}")
    public TransactionDto getTransaction(@AuthenticationPrincipal User user,
                                         @PathVariable Long transactionId) {
        return transactionService.getTransaction(user, transactionId);
    }

    @Operation(summary = "Get page of transactions")
    @GetMapping
    public List<TransactionDto> getTransactions(@AuthenticationPrincipal User user,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "50") int size,
                                                @RequestParam(required = false) LocalDate dateFrom,
                                                @RequestParam(required = false) LocalDate dateTo,
                                                @RequestParam(required = false) Category category,
                                                @RequestParam(required = false) ExpenseType type) {

        var search = TransactionSearch.builder()
                .pageRequest(PageRequest.of(page, size))
                .dateFrom(dateFrom)
                .dateTo(dateTo)
                .category(category)
                .type(type)
                .userId(user.getId())
                .build();

        return transactionService.getTransactions(search);
    }

    @Operation(summary = "Delete transaction by id")
    @DeleteMapping("/{transactionId}")
    public void deleteTransaction(@AuthenticationPrincipal User user,
                                  @PathVariable Long transactionId) {
        transactionService.deleteTransaction(user, transactionId);
    }

    @Operation(summary = "Update transaction by id")
    @PutMapping("/{transactionId}")
    public TransactionDto updateTransaction(@AuthenticationPrincipal User user,
                                            @PathVariable Long transactionId,
                                            @Valid @RequestBody UpdateTransactionRequest request) {
        return transactionService.updateTransaction(user, transactionId, request);
    }

}
