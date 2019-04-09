package com.group3.budgetApp.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name= "TRANSACTION")
public class TransactionWithdraw {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "TRANSACTION_ID")
    private Integer transactionId;
    @Column(name= "from_account_id")
    private Integer fromAccountId;
    @Column(name= "to_account_id")
    private Integer toAccountId;
    @Column
    private String memo;
    @Column(name= "transaction_type")
    private Integer transactionType;
    @Column(name= "transaction_dt")
    private Date transactionDt;
    @Column
    private Double amount=0.0;

    public TransactionWithdraw() {}

    public TransactionWithdraw(Integer fromAccountId, Integer toAccountId, String memo, Double amount){
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.memo = memo;
        this.amount = amount;
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

    public Integer getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(Integer transactionType) {
        this.transactionType = transactionType;
    }

    public Date getTransactionDt() {
        return transactionDt;
    }

    public void setTransactionDt(Date transactionDt) {
        this.transactionDt = transactionDt;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
