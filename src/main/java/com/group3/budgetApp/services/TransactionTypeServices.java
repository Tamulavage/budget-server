package com.group3.budgetApp.services;

import com.group3.budgetApp.model.TransactionType;
import com.group3.budgetApp.repository.TransactionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionTypeServices {

    private TransactionTypeRepository transactionTypeRepository;

    @Autowired
    public TransactionTypeServices(TransactionTypeRepository transactionTypeRepository){
        this.transactionTypeRepository = transactionTypeRepository;
    }

    public List<TransactionType> getAllTransactionTypes(){
        return transactionTypeRepository.findAll();
    }
}
