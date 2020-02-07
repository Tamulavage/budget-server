package com.dmt.budgetApp.model;

public class RawData {
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public RawData(Integer input){
        this.data = input.toString();
    }    
    
    public RawData(String input){
        this.data = input;
    }
    
    public RawData(){
    }
}