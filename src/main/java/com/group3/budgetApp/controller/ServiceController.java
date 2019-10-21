package com.group3.budgetApp.controller;

import java.util.List;

import com.group3.budgetApp.exceptions.InvalidTransactionAmount;
import com.group3.budgetApp.model.Account;
import com.group3.budgetApp.services.AccountServices;
import com.group3.budgetApp.services.ProfileAccountXrefService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @CrossOrigin("https://budgetapp-client.herokuapp.com")
@CrossOrigin()
@RequestMapping("/budget")
public class ServiceController {
    private AccountServices accountService;
    private ProfileAccountXrefService profileAccountXrefService;

    @Autowired
    public ServiceController(AccountServices accountServices, 
            ProfileAccountXrefService profileAccountXrefService ){
        this.accountService = accountServices;
        this.profileAccountXrefService = profileAccountXrefService;
    }

    @PostMapping("/account/")
    public ResponseEntity<Account> accountCreateAndBind(@RequestBody Account account, Integer userId) throws InvalidTransactionAmount {
        try {
            if(account != null && userId != null){
                System.out.println("accountCreateAndBind");
                Account acc = accountService.createAccount(account);
                profileAccountXrefService.createProfileAccountXref(userId, acc.getId());
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
    @PostMapping("/account")
    public ResponseEntity<Account> accountCreate(@RequestBody Account account) throws InvalidTransactionAmount {
        try {
            if(account != null){
                System.out.println("accountCreate");
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
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
