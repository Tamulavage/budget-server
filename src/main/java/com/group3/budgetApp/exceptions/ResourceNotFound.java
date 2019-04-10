package com.group3.budgetApp.exceptions;

public class ResourceNotFound extends Exception {
    public ResourceNotFound(String errorMessage){
        super(errorMessage);
    }
    public ResourceNotFound(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
