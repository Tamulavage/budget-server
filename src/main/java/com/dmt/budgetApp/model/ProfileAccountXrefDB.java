package com.dmt.budgetApp.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "profile_account_xref")
public class ProfileAccountXrefDB {

    @EmbeddedId
    private ProfileAccountPK profileAccountPK;

    @Column(name = "primary_profile_id")
    private  Integer primaryProfile = 0;

    public Integer getPrimaryProfile() {
        return primaryProfile;
    }

    public void setPrimaryProfileId(Integer primaryProfile) {
        this.primaryProfile = primaryProfile;
    }

    public ProfileAccountPK getProfileAccountPK() {
        return profileAccountPK;
    }

    public void setProfileAccountPK(ProfileAccountPK profileAccountPK) {
        this.profileAccountPK = profileAccountPK;
    }


}
