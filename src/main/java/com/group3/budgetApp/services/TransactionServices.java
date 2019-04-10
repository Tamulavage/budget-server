package com.group3.budgetApp.services;

import com.group3.budgetApp.exceptions.InvalidDepositAmount;
import com.group3.budgetApp.exceptions.ResourceNotFound;
import com.group3.budgetApp.model.Transaction;
import com.group3.budgetApp.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

@Service
public class TransactionServices {
    private TransactionRepository repo;
    private DecimalFormat df = new DecimalFormat("#.##");
    
    @Autowired
    public TransactionServices(TransactionRepository repo) {
        this.repo = repo;
    }
    
    public Transaction createWithdrawal(Transaction transaction) throws InvalidDepositAmount {
        Double amount = transaction.getAmount();
        if (amount <= 0) {
            throw new InvalidDepositAmount("Amount must be greater than zero");
        }
        return repo.save(transaction);
    }
    
    // todo: add try catch block
    public Iterable<Transaction> getAllTransactions() {
        return repo.findAll();
    }
    
    public void deleteTransaction(Integer id) {
        repo.deleteById(id);
    }
    
    public Transaction findTransactionById(Integer id) throws ResourceNotFound {
        return repo.findById(id).orElseThrow(
                () -> new ResourceNotFound("Transaction not found with Id " + id));
    }
    
    public Transaction createDeposit(Transaction deposit) throws InvalidDepositAmount {
        Double amount = Double.parseDouble(df.format(deposit.getAmount()));
        if (amount <= 0) {
            throw new InvalidDepositAmount("Please deposit a positive amount");
        } else if (amount >= (Double.MAX_VALUE / 1e304)) {
            throw new InvalidDepositAmount(String.format("Money laundering is a crime.\nYour attempted deposit of %f dollars has been reported to the SEC.", amount));
        }
        return repo.save(deposit);
    }
    
    //todo: implement comparator for date comparison
    public List<Transaction> listAllDepositsSinceDate(Date date) {
        List<Transaction> depositList = repo.findAll();
        Date currentDate = new Date();
        for (Transaction depositTransaction : depositList) {
            if ((depositTransaction.getTransactionDt().equals(date) || depositTransaction.getTransactionDt().after(date))
                    && depositTransaction.getTransactionDt().equals(currentDate) || depositTransaction.getTransactionDt().before(currentDate)) {
                depositList.add(depositTransaction);
            }
        }
        System.out.println(depositList.toString());
        return depositList;
    }
}
