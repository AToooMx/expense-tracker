package com.rsa.expense.tracker.mapper;

import com.rsa.expense.tracker.dto.TransactionDto;
import com.rsa.expense.tracker.dto.UpdateTransactionRequest;
import com.rsa.expense.tracker.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TransactionMapper {

    TransactionDto toDto(Transaction transaction);

    void update(@MappingTarget Transaction transaction, UpdateTransactionRequest request);
}
