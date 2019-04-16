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
    public Account createAccount(String name, Double balance, Integer user_id, String institution_name, Integer accountTypeId, String nickname){
        Account account = new Account(name, balance, user_id, institution_name, accountTypeId, nickname);
        repo.save(account);
        return account;
    }
    public void deleteAccount(Integer id){
        Account account = repo.getOne(id);
        if( account != null){
            repo.delete(account);
        }
    }
    public Account getAccountById(Integer id) throws ResourceNotFound {
        return repo.findById(id).orElseThrow(
                () -> new ResourceNotFound("Transaction not found with Id " + id));
    }
    public List<Account> findAll(){
        return repo.findAll();
    }
}