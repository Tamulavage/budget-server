package com.group3.budgetApp.services;

import com.group3.budgetApp.model.TransactionWithdraw;
import com.group3.budgetApp.repository.TransactionWithdrawRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TransactionWithDrawService {

    private TransactionWithdrawRepo repo;

    @Autowired
    public TransactionWithDrawService(TransactionWithdrawRepo repo){
        this.repo = repo;
    }

    public TransactionWithdraw createWithdrawTransaction(TransactionWithdraw transaction){
        Double amount = transaction.getAmount();
        if(amount < 0 ){
            throw new IllegalArgumentException("Amount must be greater then zero");
        }
        return repo.save(transaction);
    }

    // todo: add try catch block
    public TransactionWithdraw getWithdrawTransactionById(Integer id){
        return repo.getOne(id);
    }

    // todo: add try catch block
    public Iterable<TransactionWithdraw> getAllTransactions(){
        return repo.findAll();
    }


    public void deleteTransaction(Integer id){
        TransactionWithdraw transaction = repo.getOne(id);
        if( transaction != null){
            repo.delete(transaction);
        }
    }

    public TransactionWithdraw findTransactionById(Integer id){
        return repo.getOne(id);
    }
}
