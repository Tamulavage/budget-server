package com.group3.budgetApp.services;

import com.group3.budgetApp.exceptions.*;
import com.group3.budgetApp.model.Transaction;
import com.group3.budgetApp.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


@Service
public class TransactionServices {
    private TransactionRepository repo;
    private DecimalFormat df = new DecimalFormat("#.##");
    
    @Autowired
    public TransactionServices(TransactionRepository repo) {
        this.repo = repo;
    }
    
    // todo: add try catch block
    public Iterable<Transaction> getAllTransactions() {
        return repo.findAll();
    }
    
    public void deleteTransaction(Integer id) throws ResourceNotFound {
        repo.deleteById(id);
    }
    
    public Transaction findTransactionById(Integer id) throws
            ResourceNotFound {
        return repo.findById(id).orElseThrow(
                () -> new ResourceNotFound("Transaction not found with Id " + id));
    }
    
    /*public Transaction findTransactionBySenderId(Integer id) {
        return repo.findByFromAccountId(id);
    }
    
    public Transaction findTransactionByRecipientId(Integer id) {
        return repo.findByToAccountId(id);
    }*/
    
    public Transaction createTransaction(Transaction transaction) throws
            InvalidTransactionAmount {
        df.setRoundingMode(RoundingMode.FLOOR);
        Double amount = Double.parseDouble(df.format(transaction.getAmount()));
        if (amount <= 0) {
            throw new InvalidTransactionAmount("Transactions must be greater than zero.");
        } else if (amount >= (Double.MAX_VALUE / 1e304)) {
            throw new InvalidTransactionAmount(String.format("Money laundering is a crime.\nYour attempted deposit of %f dollars has been reported to the SEC.", amount));
        }
        return repo.save(transaction);
    }
    
    public Page<Transaction> getAllTranscationsUnpaged() {
        Page<Transaction> allTransactions = repo.findAll(Pageable.unpaged());
        allTransactions.getContent();
        return allTransactions;
    }
    
    public List<Transaction> getLatestTransactionsByPage() {
        Sort sort = new Sort(Sort.Direction.DESC, "transactionDt");
        PageRequest pageRequest = PageRequest.of(0, 10, sort);
        Page<Transaction> transactionPage = repo.findAll(pageRequest);
        List<Transaction> allTransactions = new ArrayList<>(transactionPage.getContent());
        while (transactionPage.hasNext()) {
            Page<Transaction> nextTransactionPage = repo.findAll(transactionPage.nextPageable());
            allTransactions.addAll(nextTransactionPage.getContent());
            transactionPage = nextTransactionPage;
        }
        return allTransactions;
    }
    
    public List<Transaction> findAllByFromAccountIdOrderByTransactionDtDesc(Integer fromId){
        return repo.findAllByFromAccountIdOrderByTransactionDtDesc(fromId);
    }


//    public List<Transaction> findTop10ByTransactionDt(LocalDate date, Pageable pageable);
//    public List<Transaction> findAllByFromAccountIdAAndToAccountId(Integer fromId, Integer toId);
//
//   public List<Transaction> findAllByFromAccountId(Integer fromId) {
//        return repo.findAllByFromAccountIdOrderByTransactionDtDesc(fromId);
//    }
//
//    public List<Transaction> findAllByToAccountId(Integer toId) {
//        return repo.findAllByToAccountIdOrderByTransactionDtDesc(toId);
//    }
}
