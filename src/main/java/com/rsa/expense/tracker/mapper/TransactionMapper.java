package com.rsa.expense.tracker.mapper;

import com.rsa.expense.tracker.dto.TransactionDto;
import com.rsa.expense.tracker.model.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionDto toDto(Transaction transaction);

}
