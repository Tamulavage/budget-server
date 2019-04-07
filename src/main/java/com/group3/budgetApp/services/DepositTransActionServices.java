package com.group3.budgetApp.services;

import com.group3.budgetApp.exceptions.InvalidDepositAmount;
import com.group3.budgetApp.model.DepositTransaction;
import com.group3.budgetApp.repository.DepositTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class DepositTransActionServices {
    private DecimalFormat df = new DecimalFormat("#.##");
    
    @Autowired
    private DepositTransactionRepository depoRepo;
    
    @Autowired
    public DepositTransActionServices(DepositTransactionRepository depoRepo) {
        this.depoRepo = depoRepo;
    }
    
    public DepositTransaction createDepositTransaction(DepositTransaction depoTransaction) throws InvalidDepositAmount {
        Double amount = Double.parseDouble(df.format(depoTransaction.getAmount()));
        if (amount <= 0) {
            throw new InvalidDepositAmount("Please deposit a positive amount");
        } else if (amount >= (Double.MAX_VALUE / 1e304)) {
            throw new InvalidDepositAmount(String.format("Money laundering is a crime.\nYour attempted deposit of %f dollars has been reported to the SEC.", amount));
        }
        return depoRepo.save(depoTransaction);
    }
}
