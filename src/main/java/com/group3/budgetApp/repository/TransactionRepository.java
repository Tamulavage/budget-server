package com.group3.budgetApp.repository;

import com.group3.budgetApp.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findAllByFromAccountIdOrderByTransactionDtDesc(Integer fromId);
//    List<Transaction> findAllByToAccountIdOrderByTransactionDtDesc(Integer toId);
//    List<Transaction> findAllByFromAccountIdAndToAccountIdOrderByTransactionDtDesc(Integer fromId, Integer toId);
}
