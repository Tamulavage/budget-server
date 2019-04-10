package com.group3.budgetApp.controller;

import com.group3.budgetApp.model.Transaction;
import com.group3.budgetApp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    
    private TransactionServices transactionServices;
    
    @Autowired
    public TransactionController(TransactionServices service) {
        transactionServices = service;
    }
//
//    @PostMapping("/transaction")
//    public HttpStatus depositTransaction(@RequestBody Transaction d) {
//
//        try {
//            transactionServices.createDeposit(d);
//            return HttpStatus.CREATED;
//        } catch (IllegalArgumentException iae) {
//            System.err.println(iae.getMessage());
//            return HttpStatus.NOT_ACCEPTABLE;
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//            return HttpStatus.SERVICE_UNAVAILABLE;
//        }
//    }
    
    @PostMapping("/transaction")
    public HttpStatus withdrawalTransaction(@RequestBody Transaction w) {
        
        try {
            transactionServices.createWithdrawal(w);
            return HttpStatus.CREATED;
        } catch (IllegalArgumentException iae) {
            System.err.println(iae.getMessage());
            return HttpStatus.NOT_ACCEPTABLE;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return HttpStatus.SERVICE_UNAVAILABLE;
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
    public HttpStatus transactionRemove(@PathVariable Integer id) {
        try {
            transactionServices.deleteTransaction(id);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
