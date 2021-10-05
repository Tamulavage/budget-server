package com.dmt.budgetApp.model;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "balance")
    private BigDecimal balance;
    @Column(name = "institution_name")
    private String institutionName;
    @Column(name = "account_type_id")
    private Integer accountTypeId;
    @Column(name = "nick_name")
    private String nickname;

    @JsonIgnore
    @Column(name = "active")
    private String active;

    @JsonIgnore
    @OneToMany(mappedBy = "account")
    Set<ProfileAccountXref> profiles;

    public Account(){
    }
    public Account(Integer id){
        this.id = id;
    }
    public Account(String nickname) {
        this.nickname = nickname;
        this.balance = new BigDecimal("0.0");
        this.institutionName = "";
    }

    public Account(BigDecimal balance, String institutionName, Integer accountTypeId, String nickname) {
        this.balance = balance;
        this.institutionName = institutionName;
        this.accountTypeId = accountTypeId;
        this.nickname = nickname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }


    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public Integer getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Integer accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Set<ProfileAccountXref> getProfiles() {
        return profiles;
    }

    public void setProfiles(Set<ProfileAccountXref> profiles) {
        this.profiles = profiles;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Account [accountTypeId=" + accountTypeId + ", balance=" + balance + ", id=" + id
                + ", institutionName=" + institutionName + ", nickname=" + nickname + "]";
    }
}
