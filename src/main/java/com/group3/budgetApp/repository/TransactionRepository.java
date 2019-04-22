package com.group3.budgetApp.repository;

import com.group3.budgetApp.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    @Query(value = "SELECT DISTINCT t.* FROM Transaction t INNER JOIN Account a ON t.from_account_id = a.id OR t.to_account_id = a.id INNER JOIN PROFILE p ON p.user_id = a.user_id WHERE p.user_id = ?1 ORDER BY transaction_dt DESC", nativeQuery = true)
    List<Transaction> findAllByUserId(Integer userId);
    List<Transaction> findAllByFromAccountIdOrToAccountId(Integer acctId, Integer acctId2);
//    List<Transaction> findAllByToAccountIdOrderByTransactionDtDesc(Integer toId);
//    List<Transaction> findAllByFromAccountIdAndToAccountIdOrderByTransactionDtDesc(Integer fromId, Integer toId);
}
