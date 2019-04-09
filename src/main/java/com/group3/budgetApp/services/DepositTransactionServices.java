package com.group3.budgetApp.services;

import com.group3.budgetApp.exceptions.InvalidDepositAmount;
import com.group3.budgetApp.model.DepositTransaction;
import com.group3.budgetApp.repository.DepositTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DepositTransactionServices {
    private DecimalFormat df = new DecimalFormat("#.##");
    
    @Autowired
    private DepositTransactionRepository depoRepo;
    
    @Autowired
    public DepositTransactionServices(DepositTransactionRepository depoRepo) {
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
    
    public List<DepositTransaction> listAllDepositsSinceDate(Date date) {
        List<DepositTransaction> depositList = depoRepo.findAll();
        Date currentDate = new Date();
        for (DepositTransaction depositTransaction : depositList) {
            if ((depositTransaction.getTransactionDt().equals(date) || depositTransaction.getTransactionDt().after(date))
                    && depositTransaction.getTransactionDt().equals(currentDate) || depositTransaction.getTransactionDt().before(currentDate)) {
                depositList.add(depositTransaction);
            }
        }
        System.out.println(depositList.toString());
        return depositList;
    }
}
