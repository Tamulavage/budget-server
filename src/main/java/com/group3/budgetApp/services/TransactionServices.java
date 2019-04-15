package com.group3.budgetApp.services;

import com.group3.budgetApp.exceptions.InvalidTransactionAmount;
import com.group3.budgetApp.exceptions.ResourceNotFound;
import com.group3.budgetApp.model.Transaction;
import com.group3.budgetApp.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionServices {
    private TransactionRepository repo;
    private DecimalFormat df = new DecimalFormat("#.##");
    
    @Autowired
    public TransactionServices(TransactionRepository repo) {
        this.repo = repo;
    }
    
    // todo: add try catch block
    public List<Transaction> getAllTransactions() {
        return repo.findAll();
    }
    
    public void deleteTransaction(Integer id) {
        repo.deleteById(id);
    }
    
    public Transaction findTransactionById(Integer id) throws ResourceNotFound {
        return repo.findById(id).orElseThrow(
                () -> new ResourceNotFound("Transaction not found with Id " + id));
    }
    
    public Transaction findTransactionBySenderId(Integer id) {
        return repo.findByFromAccountId(id);
    }
    
    public Transaction findTransactionByRecipientId(Integer id) {
        return repo.findByToAccountId(id);
    }
    
    public Transaction createTransaction(Transaction transaction) throws
            InvalidTransactionAmount {
        Double amount = Double.parseDouble(df.format(transaction.getAmount()));
        if (amount <= 0) {
            throw new InvalidTransactionAmount("Transactions must be greater than zero.");
        } else if (amount >= (Double.MAX_VALUE / 1e304)) {
            throw new InvalidTransactionAmount(String.format("Money laundering is a crime.\nYour attempted deposit of %f dollars has been reported to the SEC.", amount));
        }
        return repo.save(transaction);
    }
    
    public List<Transaction> getLatestDeposits() {
        List<Transaction> depositList = repo.findAll();
        Comparator<Transaction> comparator = Comparator.comparing(Transaction::getTransactionDt);
        Comparator<Transaction> reverseComparator = comparator.reversed();
        return depositList.stream().sorted(reverseComparator).collect(Collectors.toList());
    }
    
//    public List<Transaction> findAllByTransactionDtOrderByTransactionDtDesc(Pageable pageable);
//    public List<Transaction> findTop10ByTransactionDt(LocalDate date, Pageable pageable);
//    public List<Transaction> findAllByFromAccountIdAAndToAccountId(Integer fromId, Integer toId);
//    public List<Transaction> findAllByFromAccountId(Integer fromId);
//    public List<Transaction> findAllByToAccountId(Integer toId);
//
}
