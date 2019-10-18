package com.group3.budgetApp.model;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "TRANSACTION")
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANSACTION_ID")
    private Integer transactionId;
    @Column(name = "from_account_id")
    private Integer fromAccountId;
    @Column(name = "to_account_id")
    private Integer toAccountId;
    @Column
    private String memo;
    @Column(name = "transaction_dt")
    private LocalDate transactionDt;
    @Column
    private Double amount = 0.0;
    
    public Transaction() {
    }
    
    public Transaction(Integer transactionId, Integer fromAccountId, Integer toAccountId, String memo, Double amount,  LocalDate transactionDt) {
        this.transactionId = transactionId;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.memo = memo;
        this.amount = amount;
        this.transactionDt = transactionDt;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", fromAccountId=" + fromAccountId +
                ", toAccountId=" + toAccountId +
                ", memo='" + memo + '\'' +
                ", amount=" + amount +
                ", transactionDt=" + transactionDt +
                '}';
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(Integer fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public Integer getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Integer toAccountId) {
        this.toAccountId = toAccountId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public LocalDate getTransactionDt() {
        return transactionDt;
    }

    public void setTransactionDt(LocalDate transactionDt) {
        this.transactionDt = transactionDt;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
