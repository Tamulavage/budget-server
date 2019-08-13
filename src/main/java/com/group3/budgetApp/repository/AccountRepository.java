package com.group3.budgetApp.repository;

import com.group3.budgetApp.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findAccountById(Integer id);
    List<Account> findAllByUserId(Integer userID);
    List<Account> findAll();
    Account getAccountById(Integer id);
}
