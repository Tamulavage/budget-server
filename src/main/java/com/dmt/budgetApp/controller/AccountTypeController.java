package com.dmt.budgetApp.controller;

import com.dmt.budgetApp.model.AccountType;
import com.dmt.budgetApp.services.AccountTypeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
// @CrossOrigin("https://budgetapp-client.herokuapp.com")
@CrossOrigin()
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
            log.info("getAccountTypes called");
            return new ResponseEntity<>(accountTypeServices.getAllAccountTypes(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("getAccountTypes error ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
