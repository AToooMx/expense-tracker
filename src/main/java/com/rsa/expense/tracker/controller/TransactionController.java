package com.rsa.expense.tracker.controller;

import com.rsa.expense.tracker.config.security.IsUser;
import com.rsa.expense.tracker.dto.CreateTransactionRequest;
import com.rsa.expense.tracker.dto.CreateTransactionResponse;
import com.rsa.expense.tracker.model.User;
import com.rsa.expense.tracker.service.transaction.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@IsUser
@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateTransactionResponse createTransaction(@AuthenticationPrincipal User user,
                                                       @Valid @RequestBody CreateTransactionRequest request) {
        return transactionService.create(user, request);
    }

}
