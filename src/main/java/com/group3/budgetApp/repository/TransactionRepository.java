package com.group3.budgetApp.repository;

import com.group3.budgetApp.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository  extends JpaRepository<Transaction, Integer> {
}
