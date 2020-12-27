package com.dmt.budgetApp.services;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

import com.dmt.budgetApp.exceptions.InvalidTransactionAmount;
import com.dmt.budgetApp.exceptions.ResourceNotFound;
import com.dmt.budgetApp.model.Account;
import com.dmt.budgetApp.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AccountServices {
    private DecimalFormat df = new DecimalFormat("#.##");
    @Autowired
    private AccountRepository repo;
    
    @Autowired
    public AccountServices(AccountRepository repo) {
        this.repo = repo;
    }
    
    public Account createAccount(Account account) throws InvalidTransactionAmount {
        if (account.getBalance() < 0.0) {
            log.error("Initial balance must be at least zero. ({})", account.getBalance());
            throw new InvalidTransactionAmount("Initial balance must be at least zero.");
        } else {
            repo.save(account);
            return account;
        }
    }
    
    public Account createAccount( Double balance,  String institutionName, Integer accountTypeId, String nickname) throws
            InvalidTransactionAmount {
        if (balance < 0.0) {
            log.error("Initial balance must be at least zero. ({})", balance);
            throw new InvalidTransactionAmount("Initial balance must be at least zero.");
        } else {
            df.setRoundingMode(RoundingMode.FLOOR);
            Double balance1 = Double.parseDouble(df.format(balance));
            Account account = new Account( balance1, institutionName, accountTypeId, nickname);
            repo.save(account);
            return account;
        }
    }
    
    public boolean deleteAccount(Integer id) {
        Account account = repo.getOne(id);
        if (account != null) {
            repo.delete(account);
            return true;
        }
        return false;
    }
    
    public Account getAccountById(Integer id) throws ResourceNotFound {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Account not found with Id " + id));
    }
    
    public List<Account> findAll() {
        return repo.findAll();
    }
    
    public List<Account> findAllByUserId(Integer userId) {
       return repo.findAllByProfileUserId(userId);
    }

	public Account inactivateAccount(Account account) {
        Account accountToUpdate = repo.getAccountById(account.getId());
        accountToUpdate.setActive("No");
		return repo.save(accountToUpdate);
	}
    
}
