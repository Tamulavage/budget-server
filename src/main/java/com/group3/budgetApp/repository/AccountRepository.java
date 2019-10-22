package com.group3.budgetApp.repository;

import java.util.List;

import com.group3.budgetApp.model.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findAccountById(Integer id);

    @Query(value = "SELECT id, account_type_id, nick_name, institution_name, balance, active FROM account a,  profile_account_xref x where a.id = x.account_id and x.profile_id = ?1", nativeQuery = true)
    List<Account> findAllByProfileUserId(Integer userId);
    // List<Account> findAllByPrimaryProfileUserId(Integer userId);  
    List<Account> findAll();
    Account getAccountById(Integer id);
}
