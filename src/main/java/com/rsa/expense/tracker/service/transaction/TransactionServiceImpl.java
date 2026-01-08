package com.rsa.expense.tracker.service.transaction;

import com.rsa.expense.tracker.dto.CreateTransactionRequest;
import com.rsa.expense.tracker.dto.TransactionDto;
import com.rsa.expense.tracker.dto.TransactionSearch;
import com.rsa.expense.tracker.exception.CustomException;
import com.rsa.expense.tracker.exception.Error;
import com.rsa.expense.tracker.mapper.TransactionMapper;
import com.rsa.expense.tracker.model.Category;
import com.rsa.expense.tracker.model.Transaction;
import com.rsa.expense.tracker.model.User;
import com.rsa.expense.tracker.repository.TransactionRepository;
import com.rsa.expense.tracker.repository.TransactionSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public TransactionDto create(User user, CreateTransactionRequest request) {
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

    @Override
    public TransactionDto getTransaction(User user, Long transactionId) {
        return transactionMapper.toDto(findTransaction(transactionId, user.getId()));
    }

    @Override
    public List<TransactionDto> getTransactions(TransactionSearch search) {
        return transactionRepository.findAll(TransactionSpecification.withParameters(search), search.getPageRequest()).stream()
                .map(transactionMapper::toDto)
                .toList();
    }

    private Transaction findTransaction(Long transactionId, Long userId) {
        return transactionRepository.findByIdAndUserId(transactionId, userId)
                .orElseThrow(() -> new CustomException(Error.ENTITY_NOT_FOUND, "Transaction %s not found".formatted(transactionId)));
    }
}
