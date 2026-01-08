package com.rsa.expense.tracker.mapper;

import com.rsa.expense.tracker.dto.CreateTransactionResponse;
import com.rsa.expense.tracker.model.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    CreateTransactionResponse toDto(Transaction save);

}
