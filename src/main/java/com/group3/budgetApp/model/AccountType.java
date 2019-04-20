package com.group3.budgetApp.model;

import javax.persistence.*;

@Entity
@Table(name = "account_type")
public class AccountType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer Id;

    @Column(name="description")
    private String description;

    public AccountType() {}

    public AccountType(String description) {
        this.description = description;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
