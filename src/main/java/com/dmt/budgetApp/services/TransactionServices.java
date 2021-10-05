package com.dmt.budgetApp.services;

import com.dmt.budgetApp.exceptions.*;
import com.dmt.budgetApp.model.Account;
import com.dmt.budgetApp.model.AccountPOJO;
import com.dmt.budgetApp.model.Transaction;
import com.dmt.budgetApp.model.TransactionWithAccount;
import com.dmt.budgetApp.model.TransactionsRunningValues;
import com.dmt.budgetApp.repository.AccountRepository;
import com.dmt.budgetApp.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TransactionServices {
    private TransactionRepository repo;
    private AccountRepository accountRepository;
    private DecimalFormat df = new DecimalFormat("#.##");

    @Autowired
    public TransactionServices(TransactionRepository repo, AccountRepository accountRepository) {
        this.repo = repo;
        this.accountRepository = accountRepository;
    }

    public List<Transaction> getAllTransactions() {
        return repo.findAll();
    }

    public void deleteTransaction(Integer id) {
        repo.deleteById(id);
    }

    public List<Transaction> getAllTransactionsByUserId(Integer userId) {
        return repo.findAllByUserId(userId);
    }

    public Transaction findTransactionById(Integer id) throws ResourceNotFound {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFound("Transaction not found with Id " + id));
    }

    public Transaction createTransaction(Transaction transaction) throws InvalidTransactionAmount {
        df.setRoundingMode(RoundingMode.FLOOR);
        Double amount = Double.parseDouble(df.format(transaction.getAmount()));
        if (amount <= 0) {
            throw new InvalidTransactionAmount("Transactions must be greater than zero.");
        } else if (amount >= (Double.MAX_VALUE)) {
            throw new InvalidTransactionAmount(String.format("Transactions amount is to large %f", amount));
        }
        Character withdrawDepositTransfer = identifyTransaction(transaction);
        updateAccountBalance(transaction, withdrawDepositTransfer);
        return repo.save(transaction);
    }

    private void updateAccountBalance(Transaction transaction, Character withdrawDepositTransfer) {
        if (withdrawDepositTransfer.equals('T')) {
            withdrawFrom(transaction.getFromAccountId(), transaction.getAmount());
            depositTo(transaction.getToAccountId(), transaction.getAmount());
        } else if (withdrawDepositTransfer.equals('D')) {
            depositTo(transaction.getToAccountId(), transaction.getAmount());

        } else if (withdrawDepositTransfer.equals('W')) {
            withdrawFrom(transaction.getFromAccountId(), transaction.getAmount());
        }
    }

    private void depositTo(Integer accountId, BigDecimal amount) {
        Account account = accountRepository.findAccountById(accountId);
        BigDecimal initBalance = account.getBalance();
        account.setBalance(initBalance.add(amount));
        accountRepository.save(account);
    }

    private void withdrawFrom(Integer accountId, BigDecimal amount) {
        Account account = accountRepository.findAccountById(accountId);
        BigDecimal initBalance = account.getBalance();
        if (initBalance.subtract(amount).compareTo(new BigDecimal("0.0")) < 0.0) {
            throw new IllegalArgumentException("Withdraw account will be below zero");
        }
        account.setBalance(initBalance.subtract(amount));
        accountRepository.save(account);
    }

    private Character identifyTransaction(Transaction transaction) {
        Character withdrawDepositTransfer = 'D';
        if (transaction.getFromAccountId() != null && transaction.getToAccountId() != null) {
            withdrawDepositTransfer = 'T';
        }
        if (transaction.getFromAccountId() != null && transaction.getToAccountId() == null) {
            withdrawDepositTransfer = 'W';
        }
        return withdrawDepositTransfer;
    }

    public List<TransactionWithAccount> findAllWithAccountNameByUserId(Integer userId) {
        return repo.findAllWithAccountNameByUserId(userId);
    }

    public List<TransactionsRunningValues> findAllWithAccountNameByUserIdAndAccountValues(Integer userId) {
        List<TransactionsRunningValues> checkbook;
        checkbook = populateTransctions(userId);
        checkbook = populateRunningTotals(checkbook);
        return checkbook;
    }

    private List<TransactionsRunningValues> populateRunningTotals(List<TransactionsRunningValues> checkbook) {

        // TODO: clean up this function
        for (int i = 0; i < checkbook.size() - 1; i++) {
            TransactionsRunningValues current = checkbook.get(i);
            TransactionsRunningValues prevTran = checkbook.get(i + 1);
            List<AccountPOJO> prevAccountList = prevTran.getAccounts();
            List<AccountPOJO> currAccountList = current.getAccounts();

            for (AccountPOJO curr : currAccountList) {
                for (AccountPOJO prev : prevAccountList) {
                    if (prev.getId().equals(current.getFromAccountId()) && curr.getId().equals(prev.getId())) {
                        prev.setBalance(curr.getBalance().add(current.getAmount()));
                    } else if (prev.getId().equals(current.getToAccountId()) && curr.getId().equals(prev.getId())) {
                        prev.setBalance(curr.getBalance().subtract(current.getAmount()));
                    } else if (curr.getId().equals(prev.getId())) {
                        prev.setBalance(curr.getBalance());
                    }
                }
            }
        }
        return checkbook;
    }

    private List<TransactionsRunningValues> populateTransctions(Integer userId) {
        // TODO: clean up this function
        List<TransactionWithAccount> checkbookFromDB = repo.findAllWithAccountNameByUserId(userId);
        List<Account> accountList = accountRepository.findAllByProfileUserId(userId);
        List<TransactionsRunningValues> checkbook = new ArrayList<>();

        for (TransactionWithAccount transaction : checkbookFromDB) {
            TransactionsRunningValues transactionsRunningValue = new TransactionsRunningValues(transaction);
            transactionsRunningValue.setFromAccountName(transaction.getFromAccountName());
            transactionsRunningValue.setToAccountName(transaction.getToAccountName());
            List<AccountPOJO> accountPOJOList = new ArrayList<>();
            for (Account a : accountList) {
                AccountPOJO accountPOJOTemp = new AccountPOJO();
                accountPOJOTemp.setId(a.getId());
                accountPOJOTemp.setBalance(a.getBalance());
                accountPOJOTemp.setNickname(a.getNickname());
                accountPOJOTemp.setInstitutionName(a.getInstitutionName());
                accountPOJOList.add(accountPOJOTemp);
            }
            transactionsRunningValue.setAccounts(accountPOJOList);
            checkbook.add(transactionsRunningValue);
        }

        return checkbook;
    }

    public Transaction updateInsertTransaction(Transaction transaction) throws InvalidTransactionAmount {
        if (transaction.getTransactionId() != null) {
            log.info("updating Transction");
            return updateTransaction(transaction);
        } else {
            return createTransaction(transaction);
        }
    }

    public Transaction updateTransaction(Transaction transaction) throws InvalidTransactionAmount {
        df.setRoundingMode(RoundingMode.FLOOR);
        Double amount = Double.parseDouble(df.format(transaction.getAmount()));
        if (amount <= 0) {
            throw new InvalidTransactionAmount("Transactions must be greater than zero.");
        } else if (amount >= (Double.MAX_VALUE)) {
            throw new InvalidTransactionAmount(String.format("Transactions amount is to large %f", amount));
        }

        Transaction transactionToUpdate = repo.findAllByTranId(transaction.getTransactionId());

        revertOldTransaction(transactionToUpdate);

        transactionToUpdate = updateFields(transactionToUpdate, transaction);
        
        Character withdrawDepositTransfer = identifyTransaction(transaction);
        updateAccountBalance(transactionToUpdate, withdrawDepositTransfer);

        return repo.save(transactionToUpdate);
    }

    private void revertOldTransaction(Transaction transaction){
        Integer accountIdHold =  transaction.getToAccountId();
        transaction.setToAccountId(transaction.getFromAccountId());
        transaction.setFromAccountId(accountIdHold);

        Character withdrawDepositTransferUndo = identifyTransaction(transaction);
        updateAccountBalance(transaction, withdrawDepositTransferUndo);
    }

    private Transaction updateFields(Transaction initial, Transaction changeValues){
        initial.setMemo(changeValues.getMemo());
        initial.setToAccountId(changeValues.getToAccountId());
        initial.setFromAccountId(changeValues.getFromAccountId());
        initial.setAmount(changeValues.getAmount());
        return initial;
    }

}
