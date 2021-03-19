package com.dmt.budgetApp.utils;

import org.springframework.http.HttpHeaders;

public class Utils {

    private Utils() {}
    
    public static boolean isUserAllowedToExecuteFunction(HttpHeaders headers){
        // TODO: get token in header, return correct status
        return true;
    }
}
