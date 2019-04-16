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
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "institution_name")
    private String institutionName;
    @Column(name = "account_type_id")
    private Integer accountTypeId;
    @Column(name = "nickname")
    private String nickname;

    public Account(){
    }
    public Account(Integer id){
        this.id = id;
    }
    public Account(String name, Integer user_id) {
        this.name = name;
        this.userId = user_id;
        this.balance = 0.0;
        this.institutionName = "";
    }

    public Account(String name, Double balance, Integer user_id, String institution_name, Integer accountTypeId, String nickname) {
        this.name = name;
        this.balance = balance;
        this.userId = user_id;
        this.institutionName = institution_name;
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

    public Integer getUser_id() {
        return userId;
    }

    public void setUser_id(Integer user_id) {
        this.userId = user_id;
    }

    public String getInstitution_name() {
        return institutionName;
    }

    public void setInstitution_name(String institution_name) {
        this.institutionName = institution_name;
    }
}
