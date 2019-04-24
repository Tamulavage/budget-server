package com.group3.budgetApp.exceptions;

public class InvalidTransactionAmount extends Exception {
    public InvalidTransactionAmount(String errorMessage) {
        super(errorMessage);
    }
}
