package com.rsa.expense.tracker.repository;

import com.rsa.expense.tracker.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
