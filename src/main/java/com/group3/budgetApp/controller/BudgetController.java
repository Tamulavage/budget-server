package com.group3.budgetApp.controller;

import com.group3.budgetApp.model.TransactionWithdraw;
import com.group3.budgetApp.repository.TransactionWithdrawRepo;
import com.group3.budgetApp.services.AccountServices;
import com.group3.budgetApp.services.TransactionServices;
import com.group3.budgetApp.services.TransactionWithDrawService;
import com.group3.budgetApp.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BudgetController {

    private UserServices userService;
    private AccountServices accountService;
    private TransactionServices transactionService;
    private TransactionWithDrawService transactionWithDrawService;

    @Autowired
    public BudgetController(TransactionWithDrawService service) {
        transactionWithDrawService = service;
    }

    @PostMapping("/Transaction")
    public HttpStatus transactionWithdraw(@RequestBody TransactionWithdraw t){

        try {
            transactionWithDrawService.createWithdrawTransaction(t);
            return HttpStatus.CREATED;
        }
        catch (IllegalArgumentException iae){
            System.err.println(iae.getMessage());
            return HttpStatus.NOT_ACCEPTABLE;
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return HttpStatus.SERVICE_UNAVAILABLE;
        }
    }

    @GetMapping("/Transaction/")
    public ResponseEntity<Iterable<TransactionWithdraw>> getAllTransactions(){
        try{
            return new ResponseEntity<>(transactionWithDrawService.getAllTransactions(), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/Transaction/{id}")
    public HttpStatus transactionRemove(@PathVariable Integer id){
        try{
            transactionWithDrawService.deleteTransaction(id);
            return HttpStatus.OK;
        }
        catch (Exception e){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

}
