package com.group3.budgetApp.model;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "balance")
    private Double balance;
    @Column(name = "type_id")
    private Integer type_id;
    @Column(name = "user_id")
    private Integer user_id;
    @Column(name = "institution_name")
    private String institution_name;
    @Column(name = "account_type_id")
    private Integer accountTypeId;
    @Column(name = "nickname")
    private String nickname;

    public Account(){
    }
    public Account(String name, Integer user_id) {
        this.name = name;
        this.user_id = user_id;
        this.balance = 0.0;
        this.type_id = 0;
        this.institution_name = "";
    }

    public Account(String name, Double balance, Integer type_id, Integer user_id, String institution_name, Integer accountTypeId, String nickname) {
        this.name = name;
        this.balance = balance;
        this.type_id = type_id;
        this.user_id = user_id;
        this.institution_name = institution_name;
        this.accountTypeId = accountTypeId;
        this.nickname = nickname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getInstitution_name() {
        return institution_name;
    }

    public void setInstitution_name(String institution_name) {
        this.institution_name = institution_name;
    }
}
