package com.group3.budgetApp.repository;

import com.group3.budgetApp.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionTypeRepository  extends JpaRepository<TransactionType, Integer> {
    TransactionType findByDescription(String string);
}
