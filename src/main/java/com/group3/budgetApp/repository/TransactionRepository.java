package com.group3.budgetApp.repository;

import java.util.List;

import com.group3.budgetApp.model.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    @Query(value = "SELECT distinct t.* FROM Transaction t , Account a , profile_account_xref x where x.account_id = a.id and x.profile_id = ?1 and (t.from_account_id = a.id  OR t.to_account_id = a.id) ORDER BY transaction_dt DESC, transaction_id DESC", nativeQuery = true)
    List<Transaction> findAllByUserId(Integer userId);
}
