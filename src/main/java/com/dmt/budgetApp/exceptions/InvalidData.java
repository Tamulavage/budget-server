package com.dmt.budgetApp.exceptions;

public class InvalidData extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidData(String errorMessage) {
        super(errorMessage);
    }
}
