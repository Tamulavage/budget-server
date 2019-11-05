package com.group3.budgetApp.model;

import javax.persistence.Entity;

@Entity
public class TransactionWithAccount extends Transaction {

    private String fromAccountName;

    private String toAccountName;

    public String getFromAccountName() {
        return fromAccountName;
    }

    public void setFromAccountName(String fromAccountName) {
        this.fromAccountName = fromAccountName;
    }

    public String getToAccountName() {
        return toAccountName;
    }

    public void setToAccountName(String toAccountName) {
        this.toAccountName = toAccountName;
    }
}