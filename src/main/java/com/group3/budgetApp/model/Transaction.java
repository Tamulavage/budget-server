package com.group3.budgetApp.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.CascadeType.ALL;

@Data
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
    @ManyToOne(cascade = ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "transaction_type")
    private TransactionType transactionType;
    @Column(name = "transaction_dt")
    private LocalDate transactionDt;
    @Column
    private Double amount = 0.0;
    
    public Transaction() {
    }
    
    public Transaction(Integer transactionId, Integer fromAccountId, Integer toAccountId, String memo, Double amount, TransactionType transactionType, LocalDate transactionDt) {
        this.transactionId = transactionId;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.memo = memo;
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionDt = transactionDt;
    }
    
    public TransactionType getTransactionType() {
        if (this.transactionType == null) {
            return new TransactionType();
        } else {
            return transactionType;
        }
    }
    
    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
    
    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", fromAccountId=" + fromAccountId +
                ", toAccountId=" + toAccountId +
                ", memo='" + memo + '\'' +
                ", amount=" + amount +
                ", transactionType=" + transactionType +
                ", transactionDt=" + transactionDt +
                '}';
    }
}
