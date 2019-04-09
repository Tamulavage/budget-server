package com.group3.budgetApp.exceptions;

public class InvalidDepositAmount extends Exception {
    public InvalidDepositAmount(String errorMessage){
        super(errorMessage);
    }
    public InvalidDepositAmount(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
