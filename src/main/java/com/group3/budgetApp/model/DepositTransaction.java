package com.group3.budgetApp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "TRANSACTION")
public class DepositTransaction {
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
    @Column
    private Double amount;
    @Column(name = "transaction_type")
    private Integer transactionType;
    @Column(name = "transaction_dt")
    private Date transactionDt;
    
    public DepositTransaction(Integer fromAccountId, Integer toAccountId, String memo, Double amount, Integer transactionType, Date transactionDt) {
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.memo = memo;
        this.amount = amount;
        this.transactionType = transactionType;
        this.transactionDt = transactionDt;
    }
    
    @Override
    public String toString() {
        return "DepositTransaction{" +
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
