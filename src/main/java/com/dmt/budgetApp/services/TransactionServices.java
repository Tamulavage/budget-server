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
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

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

    // todo: add try catch block
    public List<Transaction> getAllTransactions() {
        return repo.findAll();
    }

    public void deleteTransaction(Integer id) {
        repo.deleteById(id);
    }

    public Transaction findTransactionById(Integer id) throws ResourceNotFound {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFound("Transaction not found with Id " + id));
    }

    public Transaction createTransaction(Transaction transaction) throws InvalidTransactionAmount {
        df.setRoundingMode(RoundingMode.FLOOR);
        Double amount = Double.parseDouble(df.format(transaction.getAmount()));
        transaction.setAmount(amount);
        if (amount <= 0) {
            throw new InvalidTransactionAmount("Transactions must be greater than zero.");
        } else if (amount >= (Double.MAX_VALUE / 1e304)) {
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

    private void depositTo(Integer accountId, Double amount) {
        Account account = accountRepository.findAccountById(accountId);
        Double initBalance = account.getBalance();
        account.setBalance(initBalance + amount);
        accountRepository.save(account);
    }

    private void withdrawFrom(Integer accountId, Double amount) {
        Account account = accountRepository.findAccountById(accountId);
        Double initBalance = account.getBalance();
        if ((initBalance - amount) < 0.0) {
            throw new IllegalArgumentException("Withdraw account will be below zero");
        }
        account.setBalance(initBalance - amount);
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

    public List<Transaction> getLatestTransactionsByPage() {
        Sort sort = new Sort(Sort.Direction.DESC, "transactionDt");
        // PageRequest pageRequest = PageRequest.of(0, 10, sort);
        PageRequest pageRequest = PageRequest.of(0, 100, sort);
        Page<Transaction> transactionPage = repo.findAll(pageRequest);
        List<Transaction> allTransactions = new ArrayList<>(transactionPage.getContent());
        while (transactionPage.hasNext()) {
            Page<Transaction> nextTransactionPage = repo.findAll(transactionPage.nextPageable());
            allTransactions.addAll(nextTransactionPage.getContent());
            transactionPage = nextTransactionPage;
        }
        return allTransactions;
    }

    public List<TransactionsRunningValues> findAllWithAccountNameByUserIdAndAccountValues(Integer userId) {
        List<TransactionsRunningValues> checkbook = new ArrayList<>();
        checkbook = populateTransctions(userId);
        checkbook = populateRunningTotals(checkbook);
        return checkbook;
    }

    private  List<TransactionsRunningValues> populateRunningTotals(List<TransactionsRunningValues> checkbook){

        // TODO: clean up this function
        for (int i = 0; i < checkbook.size() - 1; i++) {
            TransactionsRunningValues current = checkbook.get(i);
            TransactionsRunningValues prevTran = checkbook.get(i + 1);
            List<AccountPOJO> prevAccountList = prevTran.getAccounts();
            List<AccountPOJO> currAccountList = current.getAccounts();

            for (AccountPOJO curr : currAccountList) {
                for (AccountPOJO prev : prevAccountList) {
                    if (prev.getId() == current.getFromAccountId() && curr.getId() == prev.getId()) {
                        prev.setBalance(curr.getBalance().add(new BigDecimal(current.getAmount())));
                    } else if (prev.getId() == current.getToAccountId() && curr.getId() == prev.getId()) {
                        prev.setBalance(curr.getBalance().subtract(new BigDecimal(current.getAmount())));
                    } else if (curr.getId() == prev.getId()) {
                        prev.setBalance(curr.getBalance());
                    }     
                }
            }
        }
        return checkbook;
    }

    private  List<TransactionsRunningValues> populateTransctions(Integer userId) {
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
                accountPOJOTemp.setBalance(new BigDecimal(a.getBalance()));
                accountPOJOTemp.setNickname(a.getNickname());
                accountPOJOTemp.setInstitutionName(a.getInstitutionName());
                accountPOJOList.add(accountPOJOTemp);
            }
            transactionsRunningValue.setAccounts(accountPOJOList);
            checkbook.add(transactionsRunningValue);
        }

        return checkbook;
    }

}
