package com.group3.budgetApp.repository;

import com.group3.budgetApp.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findAccountById(Integer id);
    Account findAllByUser_id(Integer id);
    Account findAccountByNickname(String nickname);
    Account getAllByUser_id(Integer id);
    Account getAccountById(Integer id);
}
