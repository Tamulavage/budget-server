package com.group3.budgetApp.services;
import com.group3.budgetApp.exceptions.ResourceNotFound;
import com.group3.budgetApp.model.Account;
import com.group3.budgetApp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Account createAccount(String name, Double balance, Integer userId, String institutionName, Integer accountTypeId, String nickname){
        Account account = new Account(name, balance, userId, institutionName, accountTypeId, nickname);
        repo.save(account);
        return account;
    }
    public boolean deleteAccount(Integer id){
        Account account = repo.getOne(id);
        if( account != null){
            repo.delete(account);
            return true;
        }
        return false;
    }
    public Account getAccountById(Integer id) throws ResourceNotFound {
        return repo.findById(id)
                .orElseThrow( () -> new ResourceNotFound("Transaction not found with Id " + id));
    }
    public List<Account> findAll(){
        return repo.findAll();
    }
    public List<Account> findAllByUserId(Integer userId){
        return repo.findAllByUserId(userId);
    }
}