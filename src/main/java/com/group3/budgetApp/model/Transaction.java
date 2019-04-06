package com.group3.budgetApp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Transaction {
    @Id
    private Integer Transaction_id;
    private Long from_transaction_id;
    private Long to_transaction_id;
    private String memo;
    private Long transaction_type;
    private Date transaction_dt;
    private Double amount;
}
