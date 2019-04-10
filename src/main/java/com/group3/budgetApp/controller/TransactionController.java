package com.group3.budgetApp.controller;

import com.group3.budgetApp.exceptions.InvalidTransactionAmount;
import com.group3.budgetApp.model.Transaction;
import com.group3.budgetApp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/budget")
public class TransactionController {
    
    private TransactionServices transactionServices;
    
    @Autowired
    public TransactionController(TransactionServices service) {
        transactionServices = service;
    }
    
    @PostMapping("/transaction")
    public ResponseEntity<String>  Transaction(@RequestBody Transaction transaction) {
        
        try {
            transactionServices.createTransaction(transaction);
            return new ResponseEntity<>("Success" ,HttpStatus.CREATED);
        } catch (InvalidTransactionAmount ita ){
            System.err.println(ita.getMessage());
            return new ResponseEntity<>(ita.getMessage() ,HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>("Unknown Failure" ,HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/transaction/")
    public ResponseEntity<Iterable<Transaction>> getAllTransactions() {
        try {
            return new ResponseEntity<>(transactionServices.getAllTransactions(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/transaction/{id}")
    public ResponseEntity<String> transactionRemove(@PathVariable Integer id) {
        try {
            transactionServices.deleteTransaction(id);
            return new ResponseEntity<>("Success" ,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failure" , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
