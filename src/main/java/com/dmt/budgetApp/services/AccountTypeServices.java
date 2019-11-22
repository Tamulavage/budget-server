package com.dmt.budgetApp.services;

import com.dmt.budgetApp.model.AccountType;
import com.dmt.budgetApp.repository.AccountTypeRepository;
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
