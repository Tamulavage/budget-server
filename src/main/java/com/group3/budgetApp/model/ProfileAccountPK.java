package com.group3.budgetApp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProfileAccountPK implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Column(name = "profile_id")
    private Integer profileId;

    @Column(name = "account_id")
    private Integer accountId;

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

}