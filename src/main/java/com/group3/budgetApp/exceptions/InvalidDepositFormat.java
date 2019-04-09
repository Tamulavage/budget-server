package com.group3.budgetApp.exceptions;

public class InvalidDepositFormat extends Exception {
    public InvalidDepositFormat(String errorMessage){
        super(errorMessage);
    }
    public InvalidDepositFormat(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
