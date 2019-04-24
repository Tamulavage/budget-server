package com.group3.budgetApp.controller;

import com.group3.budgetApp.exceptions.InvalidTransactionAmount;
import com.group3.budgetApp.model.Account;
import com.group3.budgetApp.services.AccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("https://budgetapp-client.herokuapp.com")
@RequestMapping("/budget")
public class ServiceController {
    private AccountServices accountService;

    @Autowired
    public ServiceController(AccountServices accountServices){
        accountService = accountServices;
    }

    @PostMapping("/account")
    public ResponseEntity<Account> accountCreate(@RequestBody Account account) throws InvalidTransactionAmount {
        try {
            if(account != null){
                Account acc = accountService.createAccount(account);
                return new ResponseEntity<>(acc, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        }
        catch (IllegalArgumentException | InvalidTransactionAmount iae){
            System.err.println(iae.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/account/{id}")
    public ResponseEntity<String> accountRemove(@PathVariable Integer id){
        try {
            accountService.deleteAccount(id);
            return new ResponseEntity<>("Account Deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/account/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Integer id){
        try {
            Account account = accountService.getAccountById(id);
            if (account == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(accountService.getAccountById(id), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/account")
    public ResponseEntity<List<Account>> getAll() {
        try {
            return new ResponseEntity<>(accountService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/account/")
    public ResponseEntity<List<Account>> getAllByUserId(Integer userId) {
        try {
            return new ResponseEntity<>(accountService.findAllByUserId(userId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
