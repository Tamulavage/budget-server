package com.group3.budgetApp.repository;

import com.group3.budgetApp.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findAccountById(Integer id);

    Account findAccountByNickname(String nickname);

}
