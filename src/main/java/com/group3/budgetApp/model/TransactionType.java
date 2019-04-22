package com.group3.budgetApp.model;


import javax.persistence.*;

@Entity
@Table(name = "TRANSACTION_type")
public class TransactionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer Id;

    @Column(name="description")
    private String description;

    public TransactionType() {}

    public TransactionType(String description) {
        this.description = description;
    }

    public Integer getId() {
        return Id;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
