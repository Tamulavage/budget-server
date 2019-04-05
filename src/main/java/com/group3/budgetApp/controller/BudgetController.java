package com.group3.budgetApp.controller;

import com.group3.budgetApp.services.AccountServices;
import com.group3.budgetApp.services.TransactionServices;
import com.group3.budgetApp.services.UserServices;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BudgetController {

    private UserServices userService;
    private AccountServices accountService;
    private TransactionServices transactionService;

}
