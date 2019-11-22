package com.dmt.budgetApp.services;

import com.dmt.budgetApp.model.ProfileAccountPK;
import com.dmt.budgetApp.model.ProfileAccountXrefDB;
import com.dmt.budgetApp.repository.ProfileAccountXrefRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileAccountXrefService {

    private ProfileAccountXrefDB profileAccountXref;
    @Autowired
    private ProfileAccountXrefRepository profileAccountXrefRepository;

    @Autowired
    public ProfileAccountXrefService(ProfileAccountXrefRepository profileAccountXrefRepository) {
        this.profileAccountXrefRepository = profileAccountXrefRepository;
    }

    public void createProfileAccountXref(Integer profileId, Integer accountId){
        profileAccountXref = new ProfileAccountXrefDB();
        ProfileAccountPK profileAccountPK = new ProfileAccountPK();
        profileAccountPK.setAccountId(accountId);
        profileAccountPK.setProfileId(profileId);
        profileAccountXref.setProfileAccountPK(profileAccountPK);
        this.profileAccountXrefRepository.save(profileAccountXref);
    }

}
