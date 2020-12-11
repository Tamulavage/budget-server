package com.dmt.budgetApp.repository;

import java.util.List;

import com.dmt.budgetApp.model.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findAccountById(Integer id);

    @Query(value = "SELECT id, account_type_id, nick_name, institution_name, balance, active "
            +" FROM account a,  profile_account_xref x "
            +" WHERE a.id = x.account_id "
            +" AND active is null "
            +" AND x.profile_id = ?1", nativeQuery = true)
    List<Account> findAllByProfileUserId(Integer userId);
    List<Account> findAll();
    Account getAccountById(Integer id);
    
}
