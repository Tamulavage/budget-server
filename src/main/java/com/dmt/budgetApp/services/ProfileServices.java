package com.dmt.budgetApp.services;

import com.dmt.budgetApp.exceptions.ResourceNotFound;
import com.dmt.budgetApp.model.Profile;
import com.dmt.budgetApp.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class ProfileServices {

    private ProfileRepository repo;

    @Autowired
    public ProfileServices(ProfileRepository repo) {
        this.repo = repo;
    }

    public ProfileServices() {
    }

    public Profile createUser(Profile profile) {
        repo.save(profile);

        return profile;
    }

    public Profile createUser(String firstName, String lastName, String userName) {
        Profile profile = new Profile(firstName, lastName, userName);
        repo.save(profile);
        return profile;
    }

    public Profile updateUser(Profile newProfileData, Integer id) {
        Profile original = repo.findById(id).get();
        original.setFirstName(newProfileData.getFirstName());
        original.setLastName(newProfileData.getLastName());
        return repo.save(original);
    }

    public List<Profile> findAllByLast(String last){
        return repo.findAllByLastName(last);
    }

    public Profile findByUsername(String username){
        return repo.findByUserName(username);
    }

    public Profile findById(Integer id) throws ResourceNotFound {
        return repo.findById(id).orElseThrow(
                () -> new ResourceNotFound("Profile not found with Id " + id));
    }

    public boolean deleteUser(Integer id) {
        repo.deleteById(id);
        return true;
    }

    public List<Profile> findAll(){
        return repo.findAll();
    }

    public Profile findByFullName(String first, String last){
        return repo.findByFirstNameAndLastName(first, last);
    }

}
