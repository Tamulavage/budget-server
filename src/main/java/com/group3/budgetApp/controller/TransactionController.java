package com.group3.budgetApp.controller;

import com.group3.budgetApp.exceptions.*;
import com.group3.budgetApp.model.Transaction;
import com.group3.budgetApp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("https://zipbudget.herokuapp.com")
@RequestMapping("/budget")
public class TransactionController {
    
    private TransactionServices transactionServices;
    private Transaction transaction;
    
    @Autowired
    public TransactionController(TransactionServices service) {
        transactionServices = service;
    }
    
    @PostMapping("/transaction")
    public ResponseEntity<Transaction> Transaction(@RequestBody Transaction transaction) {
        
        try {
            Transaction t = transactionServices.createTransaction(transaction);
            return new ResponseEntity<>(t, HttpStatus.CREATED);
        } catch (InvalidTransactionAmount ita) {
            System.err.println(ita.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/transaction/{id}")
    public ResponseEntity<Transaction> findTransactionsById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(transactionServices.findTransactionById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
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
    
//    @GetMapping("/transaction/latest")
//    public ResponseEntity<Iterable<Transaction>> getLatestTransactions() {
//        try {
//            return new ResponseEntity<>(transactionServices.getLatestDeposits(), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    
    @DeleteMapping("/transaction/{id}")
    public ResponseEntity<String> transactionRemove(@PathVariable Integer id) {
        try {
            transactionServices.deleteTransaction(id);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failure", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/transaction/latest")
    public ResponseEntity<Iterable<Transaction>> getLatestTransactionsByPage() {
        try {
            return new ResponseEntity<>(transactionServices.getLatestTransactionsByPage(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/transaction/sender/{id}")
    public ResponseEntity<Iterable<Transaction>> getAllSenderTransactions(@PathVariable Integer id) {
        
        try {
            return new ResponseEntity<>(transactionServices.getAllTransactions(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/transaction/recipient/{id}")
    public ResponseEntity<Iterable<Transaction>> getAllRecipientTransactions(@PathVariable Integer id) {
        
        try {
            return new ResponseEntity<>(transactionServices.getAllTransactions(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
