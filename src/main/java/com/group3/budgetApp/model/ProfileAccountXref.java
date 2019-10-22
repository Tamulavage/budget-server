package com.group3.budgetApp.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "profile_account_xref")
public class ProfileAccountXref {
// TODO: remove model if not needed in future

    @EmbeddedId
    Integer id;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "account_id")
    Account account;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    Profile profile;

    @Column(name="primary_profile_id")
    private Boolean primaryProfile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Boolean getPrimaryProfile() {
        return primaryProfile;
    }

    public void setPrimaryProfile(Boolean primaryProfile) {
        this.primaryProfile = primaryProfile;
    }

    

}
