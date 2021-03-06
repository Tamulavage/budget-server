package com.dmt.budgetApp.controller;

import java.util.List;

import com.dmt.budgetApp.exceptions.InvalidTransactionAmount;
import com.dmt.budgetApp.model.Account;
import com.dmt.budgetApp.services.AccountServices;
import com.dmt.budgetApp.services.ProfileAccountXrefService;
import com.dmt.budgetApp.utils.Constants;
import com.dmt.budgetApp.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
// @CrossOrigin("https://budgetapp-client.herokuapp.com")
@CrossOrigin()
@RequestMapping("/budget")
public class ServiceController {
    private AccountServices accountService;
    private ProfileAccountXrefService profileAccountXrefService;

    @Autowired
    public ServiceController(AccountServices accountServices, ProfileAccountXrefService profileAccountXrefService) {
        this.accountService = accountServices;
        this.profileAccountXrefService = profileAccountXrefService;
    }

    @PostMapping("/account/")
    public ResponseEntity<Account> accountCreateAndBind(@RequestBody Account account, Integer userId,
            @RequestHeader HttpHeaders headers) throws InvalidTransactionAmount {
        if (!Utils.isUserAllowedToExecuteFunction(headers)) {
            log.info("no access = trace={}", headers.get(Constants.HEADER_TRACE));
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        try {
            if (account != null && userId != null) {
                log.info("accountCreateAndBind start userId {}", userId);
                Account acc = accountService.createAccount(account);
                profileAccountXrefService.createProfileAccountXref(userId, acc.getId());
                return new ResponseEntity<>(acc, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        } catch (IllegalArgumentException | InvalidTransactionAmount iae) {
            log.info("accountCreateAndBind error", iae);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/account/inactive")
    public ResponseEntity<Account> inactivateAccount(@RequestBody Account account, @RequestHeader HttpHeaders headers) {

        if (!Utils.isUserAllowedToExecuteFunction(headers)) {
            log.info("no access = trace={}", headers.get(Constants.HEADER_TRACE));
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        try {
            log.info(" inactivateAccount for account id {}", account.getId());
            Account acc = accountService.inactivateAccount(account);
            return new ResponseEntity<>(acc, HttpStatus.OK);
        } catch (IllegalArgumentException ia) {
            log.info("accountCreateAndBind error", ia);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/account")
    public ResponseEntity<Account> accountCreate(@RequestBody Account account, @RequestHeader HttpHeaders headers)
            throws InvalidTransactionAmount {
        if (!Utils.isUserAllowedToExecuteFunction(headers)) {
            log.info("no access = trace={}", headers.get(Constants.HEADER_TRACE));
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        try {
            if (account != null) {
                Account acc = accountService.createAccount(account);
                return new ResponseEntity<>(acc, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        } catch (IllegalArgumentException | InvalidTransactionAmount iae) {
            log.error("accountCreate error ", iae);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/account/{id}")
    public ResponseEntity<String> accountRemove(@PathVariable Integer id, @RequestHeader HttpHeaders headers) {

        if (!Utils.isUserAllowedToExecuteFunction(headers)) {
            log.info("no access = trace={}", headers.get(Constants.HEADER_TRACE));
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        try {
            accountService.deleteAccount(id);
            return new ResponseEntity<>("Account Deleted", HttpStatus.OK);
        } catch (Exception e) {
            log.info("accountRemove error ", e);
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Integer id, @RequestHeader HttpHeaders headers) {

        if (!Utils.isUserAllowedToExecuteFunction(headers)) {
            log.info("no access = trace={}", headers.get(Constants.HEADER_TRACE));
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        try {
            Account account = accountService.getAccountById(id);
            if (account == null) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(accountService.getAccountById(id), HttpStatus.OK);
            }
        } catch (Exception e) {
            log.info("getAccountById error ", e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/account/")
    public ResponseEntity<List<Account>> getAllByUserId(Integer userId, @RequestHeader HttpHeaders headers) {

        if (!Utils.isUserAllowedToExecuteFunction(headers)) {
            log.info("no access = trace={}", headers.get(Constants.HEADER_TRACE));
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        try {
            return new ResponseEntity<>(accountService.findAllByUserId(userId), HttpStatus.OK);
        } catch (Exception e) {
            log.info("getAllByUserId error ", e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
