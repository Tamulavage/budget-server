package com.group3.budgetApp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class TransactionsRunningValues extends Transaction {

    private String fromAccountName;

    private String toAccountName;

    private ArrayList<AccountPOJO> accounts; 

    public TransactionsRunningValues() {        
        accounts = new ArrayList<>();
    }

    public TransactionsRunningValues (Transaction transaction ){
        super(transaction.getTransactionId(), 
            transaction.getFromAccountId(),
            transaction.getToAccountId(), 
            transaction.getMemo(),
            transaction.getAmount(), 
            transaction.getTransactionDt()
            );
            accounts = new ArrayList<>();
    }

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

    public List<AccountPOJO> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountPOJO> accounts) {
        this.accounts = (ArrayList<AccountPOJO>) accounts;
    }

}