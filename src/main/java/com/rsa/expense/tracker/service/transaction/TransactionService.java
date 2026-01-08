package com.rsa.expense.tracker.service.transaction;

import com.rsa.expense.tracker.dto.CreateTransactionRequest;
import com.rsa.expense.tracker.dto.TransactionDto;
import com.rsa.expense.tracker.dto.TransactionSearch;
import com.rsa.expense.tracker.model.User;

import java.util.List;

public interface TransactionService {

    TransactionDto create(User user, CreateTransactionRequest request);

    TransactionDto getTransaction(User user, Long transactionId);

    List<TransactionDto> getTransactions(TransactionSearch search);

    void deleteTransaction(User user, Long transactionId);
}
