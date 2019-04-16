package com.group3.budgetApp.controller;

import com.group3.budgetApp.model.Account;
import com.group3.budgetApp.model.User;
import com.group3.budgetApp.services.AccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/budget")
public class ServiceController {
    private AccountServices accountService;

    @Autowired
    public ServiceController(AccountServices accountServices){
        accountService = accountServices;
    }

    @PostMapping("/account")
    public ResponseEntity<Account> accountCreate(@RequestBody Account account){
        try {
            accountService.createAccount(account);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        }
        catch (IllegalArgumentException iae){
            System.err.println(iae.getMessage());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/account/{id}")
    public HttpStatus accountRemove(@PathVariable Integer id){
        try{
            accountService.deleteAccount(id);
            return HttpStatus.OK;
        }
        catch (Exception e){
            return HttpStatus.INTERNAL_SERVER_ERROR;
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
}
