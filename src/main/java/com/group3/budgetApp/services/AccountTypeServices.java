package com.group3.budgetApp.services;

import com.group3.budgetApp.model.AccountType;
import com.group3.budgetApp.repository.AccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountTypeServices {

    private AccountTypeRepository accountTypeRepository;

    @Autowired
    public AccountTypeServices(AccountTypeRepository accountTypeRepository){
        this.accountTypeRepository = accountTypeRepository;
    }

    public List<AccountType> getAllAccountTypes(){
        return accountTypeRepository.findAll();
    }
}
