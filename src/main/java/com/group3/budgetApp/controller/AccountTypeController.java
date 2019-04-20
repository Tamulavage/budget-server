package com.group3.budgetApp.controller;

import com.group3.budgetApp.model.AccountType;
import com.group3.budgetApp.services.AccountTypeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/budget")
public class AccountTypeController {

    private AccountTypeServices accountTypeServices;

    @Autowired
    public AccountTypeController(AccountTypeServices accountTypeServices){
        this.accountTypeServices = accountTypeServices;
    }

    @GetMapping("/accounttype")
    public ResponseEntity<Iterable<AccountType>> getAccountTypes() {
        try {
            return new ResponseEntity<>(accountTypeServices.getAllAccountTypes(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
