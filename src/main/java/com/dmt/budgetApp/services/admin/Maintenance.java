package com.dmt.budgetApp.services.admin;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.dmt.budgetApp.exceptions.InvalidTransactionAmount;
import com.dmt.budgetApp.model.Account;
import com.dmt.budgetApp.model.FutureBudgetLineItem;
import com.dmt.budgetApp.model.FutureBudgetOrg;
import com.dmt.budgetApp.model.ProfileAccountPK;
import com.dmt.budgetApp.model.ProfileAccountXrefDB;
import com.dmt.budgetApp.model.Transaction;
import com.dmt.budgetApp.repository.AccountRepository;
import com.dmt.budgetApp.repository.FutureBudgetLineItemRepository;
import com.dmt.budgetApp.repository.FutureBudgetOrgRepository;
import com.dmt.budgetApp.repository.ProfileAccountXrefRepository;
import com.dmt.budgetApp.repository.TransactionRepository;
import com.dmt.budgetApp.services.TransactionServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class Maintenance {

    private TransactionServices transactionServices;

    private ProfileAccountXrefRepository profileAccountXrefRepository;
    private AccountRepository accountRepository;

    private FutureBudgetLineItemRepository futureBudgetLineItemRepository;
    private FutureBudgetOrgRepository futureBudgetOrgRepository;
    private TransactionRepository transactionRepository;

    private Integer userId;

    private List<Account> accountIdsToDelete = new ArrayList<>();
    private List<Integer> accountIds = new ArrayList<>();

    private List<FutureBudgetOrg> futureBudgets = new ArrayList<>();

    @Autowired
    public Maintenance(TransactionServices transactionServices, 
            ProfileAccountXrefRepository profileAccountXrefRepository,
            AccountRepository accountRepository, 
            FutureBudgetLineItemRepository futureBudgetLineItemRepository,
            FutureBudgetOrgRepository futureBudgetOrgRepository,
            TransactionRepository transactionRepository) {
        this.transactionServices = transactionServices;
        this.profileAccountXrefRepository = profileAccountXrefRepository;
        this.accountRepository = accountRepository;
        this.futureBudgetLineItemRepository = futureBudgetLineItemRepository;
        this.futureBudgetOrgRepository = futureBudgetOrgRepository;
        this.transactionRepository = transactionRepository;
    }

    public void nullUserValues(Integer userId) throws Exception {
        log.info("nullUserValues: userId {}", userId);
        try {
            if (userId != null) {
                this.userId = userId;
                deleteTransactions();
                getAccountByProfile();
                deleteProfileAccountXref();
                deleteAccounts();
                getOrgIdByProfile();
                deleteFutureAccounting();
                deleteFutureOrg();
            } else {
                log.info("userId is null");
                throw new Exception("Invalid Data");
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    public void resetDemoValues(Integer userId) throws Exception {
        log.info("resetDemoValues: userId {}", userId);
        try {
            if (userId != null) {
                this.userId = userId;

                insertAccounts();
                bindAccountToProfile();
                insertTransactions();
                insertFutureOrg();
                getOrgIdByProfile();
                insertFutureAccounting();
            } else {
                log.info("userId is null");
                throw new Exception("Invalid Data");
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    private void deleteTransactions() {

        log.info("deleteTransactions");
        List<Transaction> transactionToDelete = transactionRepository.findAllByUserIdForce(userId); 
        for (Transaction t : transactionToDelete) {
            transactionRepository.deleteByTransactionID(t.getTransactionId());
        }
    }

    private void deleteProfileAccountXref() {
        log.info("deleteProfileAccountXref");
        profileAccountXrefRepository.deleteByUserId(userId);
    }

    private void getAccountByProfile() {
        log.info("getAccountByProfile");
        accountIdsToDelete = accountRepository.findAllByProfileUserId(userId);
    }

    private void deleteAccounts() {
        log.info("deleteAccounts");
        for (Account account : accountIdsToDelete) {
            accountRepository.deleteById(account.getId());
        }
    }

    private void insertAccounts() {
        log.info("insertAccounts");
        Account account1 = new Account();
        account1.setAccountTypeId(1);
        account1.setBalance(100d);
        account1.setInstitutionName("My Bank");
        account1.setNickname("Main Checking account");
        Account account2 = new Account();
        account2.setAccountTypeId(2);
        account2.setBalance(100d);
        account2.setInstitutionName("My 2nd Bank");
        account2.setNickname("Main Savings account");

        accountRepository.save(account1);
        accountRepository.save(account2);
        accountIds.add(account1.getId());
        accountIds.add(account2.getId());

    }

    private void bindAccountToProfile() {

        log.info("bindAccountToProfile");
        for (Integer accountId : accountIds) {
            ProfileAccountXrefDB profileAccountXref = new ProfileAccountXrefDB();
            ProfileAccountPK profileAccountPK = new ProfileAccountPK();
            profileAccountPK.setAccountId(accountId);
            profileAccountPK.setProfileId(userId);
            profileAccountXref.setProfileAccountPK(profileAccountPK);
            this.profileAccountXrefRepository.save(profileAccountXref);

        }

    }

    private void insertTransactions() {
        LocalDate today = LocalDate.now();

        log.info("insertTransactions");
        Transaction transaction1 = new Transaction();
        if(accountIds.size()<1){
            List<Account> accounts = accountRepository.findAllByProfileUserId(userId);
            for (Account account : accounts) {
                accountIds.add(account.getId());
            }
        }

        transaction1.setAmount(100d);
        transaction1.setToAccountId(accountIds.get(0));
        transaction1.setTransactionDt(today.minusDays(1));
        transaction1.setMemo("Initial deposit");

        Transaction transaction2 = new Transaction();

        transaction2.setAmount(100d);
        transaction2.setToAccountId(accountIds.get(1));
        transaction2.setTransactionDt(today.minusDays(1));
        transaction2.setMemo("Initial Savings deposit");

        Transaction transaction3 = new Transaction();
        transaction3.setAmount(10d);
        transaction3.setToAccountId(accountIds.get(1));
        transaction3.setTransactionDt(today);
        transaction3.setFromAccountId(accountIds.get(0));
        transaction3.setMemo("Transfer to Savings");

        Transaction transaction4 = new Transaction();

        transaction4.setAmount(7.5d);
        transaction4.setFromAccountId(accountIds.get(0));
        transaction4.setTransactionDt(today);
        transaction4.setMemo("Credit Card Payment");

        try {
            transactionServices.createTransaction(transaction1);
            transactionServices.createTransaction(transaction2);
            transactionServices.createTransaction(transaction3);
            transactionServices.createTransaction(transaction4);
        } catch (InvalidTransactionAmount e) {
            log.error("insertTransactions error {}", e.getMessage());
        }
    }

    private void getOrgIdByProfile(){
        log.info("getOrgIdByProfile");
        futureBudgets = futureBudgetOrgRepository.findAllOrgByProfileId(userId);
    }

    private void deleteFutureAccounting(){
        log.info("deleteFutureAccounting");
        for (FutureBudgetOrg futureBudget : futureBudgets) {
            futureBudgetLineItemRepository.deleteByOrgID(futureBudget.getOrgId());
        }
    }

    private void deleteFutureOrg(){
        log.info("deleteFutureOrg");
        for (FutureBudgetOrg futureBudget : futureBudgets) {
            futureBudgetOrgRepository.deleteById(futureBudget.getOrgId());
        }
    }

    private void insertFutureOrg(){
        log.info("insertFutureOrg");
        FutureBudgetOrg futureBudgetOrg1 = new FutureBudgetOrg();
        futureBudgetOrg1.setDirection("O");
        futureBudgetOrg1.setOrgName("Rent");
        futureBudgetOrg1.setProfileId(userId);
        FutureBudgetOrg futureBudgetOrg2 = new FutureBudgetOrg();
        futureBudgetOrg2.setDirection("I");
        futureBudgetOrg2.setOrgName("PayCheck");
        futureBudgetOrg2.setProfileId(userId);
        FutureBudgetOrg futureBudgetOrg3 = new FutureBudgetOrg();
        futureBudgetOrg3.setDirection("O");
        futureBudgetOrg3.setOrgName("Utilities");
        futureBudgetOrg3.setProfileId(userId);

        futureBudgetOrgRepository.save(futureBudgetOrg1);
        futureBudgetOrgRepository.save(futureBudgetOrg2);
        futureBudgetOrgRepository.save(futureBudgetOrg3);
    }

    private void insertFutureAccounting(){
        log.info("insertFutureAccounting");

        for (FutureBudgetOrg futureBudget : futureBudgets) {
            Integer orgId = futureBudget.getOrgId();
            String direction = futureBudget.getDirection();
            String orgName = futureBudget.getOrgName();
            for(int month = 1; month <=13; month++){
                FutureBudgetLineItem futureBudgetLineItem = new FutureBudgetLineItem();
                futureBudgetLineItem.setOrgId(orgId);
                futureBudgetLineItem.setFrequencyPerMonth(1);
                futureBudgetLineItem.setMonth(month);
                if("I".equals(direction)){
                    futureBudgetLineItem.setAmount(new BigDecimal("4000"));
                }
                else if("Rent".equals(orgName)){
                    futureBudgetLineItem.setAmount(new BigDecimal("1000"));
                } else {
                    futureBudgetLineItem.setAmount(new BigDecimal("300"));
                }
                futureBudgetLineItemRepository.save(futureBudgetLineItem);
            }
            
        }
        
    }
}