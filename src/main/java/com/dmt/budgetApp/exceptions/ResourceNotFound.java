package com.dmt.budgetApp.exceptions;

public class ResourceNotFound extends Exception {

    private static final long serialVersionUID = 6865585363850775426L;

    public ResourceNotFound(String errorMessage) {
        super(errorMessage);
    }
}
