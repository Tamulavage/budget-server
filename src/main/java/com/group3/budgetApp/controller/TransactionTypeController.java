package com.group3.budgetApp.controller;

import com.group3.budgetApp.model.TransactionType;
import com.group3.budgetApp.services.TransactionTypeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/budget")
public class TransactionTypeController {

    private TransactionTypeServices transactionTypeServices;

    @Autowired
    public TransactionTypeController(TransactionTypeServices transactionTypeServices){
        this.transactionTypeServices = transactionTypeServices;
    }

    @GetMapping("/transactiontype")
    public ResponseEntity<List<TransactionType>> getTransactionTypes() {
        try {
            return new ResponseEntity<>(transactionTypeServices.getAllTransactionTypes(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
