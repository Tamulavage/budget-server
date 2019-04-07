package com.group3.budgetApp.repository;

import com.group3.budgetApp.model.DepositTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface DepositTransactionRepository extends JpaRepository<DepositTransaction, Integer> {
    Optional<DepositTransaction> find(Integer id);
    DepositTransaction save(DepositTransaction depositTransaction);
    List<DepositTransaction> findAll();
    List<DepositTransaction> findAllDepositsSince(Date minDate);
}
