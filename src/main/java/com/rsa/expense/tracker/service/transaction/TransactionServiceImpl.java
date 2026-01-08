package com.rsa.expense.tracker.service.transaction;

import com.rsa.expense.tracker.dto.CreateTransactionRequest;
import com.rsa.expense.tracker.dto.CreateTransactionResponse;
import com.rsa.expense.tracker.mapper.TransactionMapper;
import com.rsa.expense.tracker.model.Category;
import com.rsa.expense.tracker.model.Transaction;
import com.rsa.expense.tracker.model.User;
import com.rsa.expense.tracker.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public CreateTransactionResponse create(User user, CreateTransactionRequest request) {
        log.info("{}", user);
        var transaction = Transaction.builder()
                .user(user)
                .amount(request.getAmount())
                .category(Optional.ofNullable(request.getCategory()).orElse(Category.OTHER))
                .description(request.getDescription())
                .type(request.getType())
                .build();
        Transaction save = transactionRepository.save(transaction);
        return transactionMapper.toDto(save);
    }
}
