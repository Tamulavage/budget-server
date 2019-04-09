package com.group3.budgetApp.controller;

import com.group3.budgetApp.model.Account;
import com.group3.budgetApp.services.AccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ServiceController {
    private AccountServices accountService;

    @Autowired
    public ServiceController(AccountServices accountServices){
        accountService = accountServices;
    }
    @PostMapping("/account")
    public HttpStatus accountCreate(@RequestBody Account account){
        try {
            accountService.createAccount(account);
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
    public HttpStatus accountGet(@PathVariable Integer id){
        try{
            accountService.getAccountById(id);
            return HttpStatus.OK;
        }
        catch (Exception e){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
