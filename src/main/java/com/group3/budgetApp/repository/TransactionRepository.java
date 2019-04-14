package com.group3.budgetApp.repository;

import com.group3.budgetApp.model.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Transaction findByFromAccountId(Integer id);
    Transaction findByToAccountId(Integer id);
//    List<Transaction> findAllByTransactionDtOrderByTransactionDtDesc(Pageable pageable);
//    List<Transaction> findTop10ByTransactionDt(LocalDate date, Pageable pageable);
//    List<Transaction> findAllByFromAccountIdAAndToAccountId(Integer fromId, Integer toId);
//    List<Transaction> findAllByFromAccountId(Integer fromId);
//    List<Transaction> findAllByToAccountId(Integer toId);
}
