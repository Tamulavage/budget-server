package com.group3.budgetApp.exceptions;

public class InvalidTransactionAmount extends Exception {

    private static final long serialVersionUID = 2822414161237409742L;

    public InvalidTransactionAmount(String errorMessage) {
        super(errorMessage);
    }
}
