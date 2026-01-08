package com.rsa.expense.tracker.service.transaction;

import com.rsa.expense.tracker.dto.CreateTransactionRequest;
import com.rsa.expense.tracker.dto.CreateTransactionResponse;
import com.rsa.expense.tracker.model.User;
import jakarta.validation.Valid;

public interface TransactionService {

    CreateTransactionResponse create(User user, CreateTransactionRequest request);

}
