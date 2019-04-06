package com.group3.budgetApp.repository;

import com.group3.budgetApp.model.TransactionWithdraw;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionWithdrawRepo extends JpaRepository<TransactionWithdraw, Integer> {
}
