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
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDto createTransaction(@AuthenticationPrincipal User user,
                                            @Valid @RequestBody CreateTransactionRequest request) {
        return transactionService.create(user, request);
    }

    @GetMapping("/{transactionId}")
    public TransactionDto getTransaction(@AuthenticationPrincipal User user,
                                         @PathVariable Long transactionId) {
        return transactionService.getTransaction(user, transactionId);
    }

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

    @DeleteMapping("/{transactionId}")
    public void deleteTransaction(@AuthenticationPrincipal User user,
                                  @PathVariable Long transactionId) {
        transactionService.deleteTransaction(user, transactionId);
    }

    @PutMapping("/{transactionId}")
    public TransactionDto updateTransaction(@AuthenticationPrincipal User user,
                                            @PathVariable Long transactionId,
                                            @Valid @RequestBody UpdateTransactionRequest request) {
        return transactionService.updateTransaction(user, transactionId, request);
    }

}
