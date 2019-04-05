package com.group3.budgetApp.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name= "TRANSACTION")
public class TransactionWithdraw {

    @Id
    private Integer transaction_id;
    @Column
    private Integer from_transaction_id;
    @Column
    private Integer to_transaction_id;
    @Column
    private String memo;
    @Column
    private Integer transaction_type;
    @Column
    private Date transaction_dt;
    @Column
    private Double amount;

    public TransactionWithdraw() {}

    public TransactionWithdraw(Integer from_transaction_id, Integer to_transaction_id, String memo){
        this.from_transaction_id = from_transaction_id;
        this.to_transaction_id = to_transaction_id;
        this.memo = memo;
    }

    public Integer getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(Integer transaction_id) {
        transaction_id = transaction_id;
    }

    public Integer getFrom_transaction_id() {
        return from_transaction_id;
    }

    public void setFrom_transaction_id(Integer from_transaction_id) {
        this.from_transaction_id = from_transaction_id;
    }

    public Integer getTo_transaction_id() {
        return to_transaction_id;
    }

    public void setTo_transaction_id(Integer to_transaction_id) {
        this.to_transaction_id = to_transaction_id;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(Integer transaction_type) {
        this.transaction_type = transaction_type;
    }

    public Date getTransaction_dt() {
        return transaction_dt;
    }

    public void setTransaction_dt(Date transaction_dt) {
        this.transaction_dt = transaction_dt;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
