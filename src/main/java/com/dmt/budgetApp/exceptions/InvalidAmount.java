package com.dmt.budgetApp.exceptions;

public class InvalidAmount extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidAmount(String errorMessage) {
        super(errorMessage);
    }
}
