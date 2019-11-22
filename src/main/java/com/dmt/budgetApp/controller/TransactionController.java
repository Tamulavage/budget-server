package com.dmt.budgetApp.controller;

import com.dmt.budgetApp.exceptions.*;
import com.dmt.budgetApp.model.Transaction;
import com.dmt.budgetApp.model.TransactionWithAccount;
import com.dmt.budgetApp.model.TransactionsRunningValues;
import com.dmt.budgetApp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
// @CrossOrigin("https://budgetapp-client.herokuapp.com")
@CrossOrigin()
@RequestMapping("/budget")
public class TransactionController {
    
    private TransactionServices transactionServices;
    
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
    
    @GetMapping("/transaction/{userId}")
    public ResponseEntity<Iterable<TransactionWithAccount>> findAllWithAccountNameByUserId(@PathVariable Integer userId) {
        try {
            return new ResponseEntity<>(transactionServices.findAllWithAccountNameByUserId(userId), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/transactions/{userId}")
    public ResponseEntity<Iterable<TransactionsRunningValues>> findAllWithAccountAmountsByUserId(@PathVariable Integer userId) {
        try {
            return new ResponseEntity<>(transactionServices.findAllWithAccountNameByUserIdAndAccountValues(userId), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.toString());
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
}
