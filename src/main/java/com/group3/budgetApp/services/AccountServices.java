package com.group3.budgetApp.services;
import com.group3.budgetApp.model.Account;
import com.group3.budgetApp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServices{
 //   @Autowired
    private AccountRepository repo;
    @Autowired
    public AccountServices(AccountRepository repo){
        this.repo = repo;
    }
    public Account createAccount(Account account){
        repo.save(account);
        return account;
    }
    public void deleteAccount(Integer id){
        Account account = repo.getOne(id);
        if( account != null){
            repo.delete(account);
        }
    }
    public Account getAccountById(Integer id){
        return repo.getOne(id);
    }
}

