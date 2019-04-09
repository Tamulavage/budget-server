package com.group3.budgetApp.controller;

import com.group3.budgetApp.model.DepositTransaction;
import com.group3.budgetApp.model.Transaction;
import com.group3.budgetApp.model.TransactionWithdraw;
import com.group3.budgetApp.repository.DepositTransactionRepository;
import com.group3.budgetApp.repository.TransactionWithdrawRepo;
import com.group3.budgetApp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepoBudgetController {
    
    private UserServices userService;
    private AccountServices accountService;
    private TransactionServices transactionService;
    private DepositTransactionServices depositTransactionService;
    private DepositTransactionRepository depositTransactionRepository;
    
    @Autowired
    public DepoBudgetController(DepositTransactionServices depoService) {
        depositTransactionService = depoService;
    }
    
    @PostMapping("/transaction")
    public HttpStatus depositTransaction(@RequestBody DepositTransaction dt) {
        
        try {
            depositTransactionService.createDepositTransaction(dt);
            return HttpStatus.CREATED;
        } catch (IllegalArgumentException iae) {
            System.err.println(iae.getMessage());
            return HttpStatus.NOT_ACCEPTABLE;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return HttpStatus.SERVICE_UNAVAILABLE;
        }
    }
    
   /* @PostMapping("/{transaction}")
    public void addToTransactionList(
            @PathVariable("transaction") DepositTransaction depositTransaction) {
        depositTransactionRepository.save(depositTransaction);
    }*/
    
    @GetMapping("/{transaction}")
    public String findAllTransactions(
            @PathVariable("transaction") Integer typeId,
            Model transaction) {
        List<DepositTransaction> transactionList =
                depositTransactionRepository.findAll();
        if (transactionList != null) {
            transaction.addAttribute("transactions", transactionList);
        }
        return "transactionList";
    }
}
