package com.group3.budgetApp.services;

import com.group3.budgetApp.model.User;
import com.group3.budgetApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class UserServices {

    private UserRepository repo;

    @Autowired
    public UserServices(UserRepository repo) {
        this.repo = repo;
    }

    public UserServices() {
    }

    public User createUser(User user) {
        repo.save(user);
        return user;
    }

    public User createUser(String firstName, String lastName) {
        User user = new User(firstName, lastName);
        repo.save(user);
        return user;
    }

    public User updateUser(User newUserData, Integer id) {
        User original = repo.findById(id).get();
        original.setFirstName(newUserData.getFirstName());
        original.setLastName(newUserData.getLastName());
        return repo.save(original);

    }


    public User findById(Integer id) {
        return repo.findById(id).get();
    }

    public Boolean removeUser(Integer id) {
       repo.deleteById(id);
       return true;
    }

    public Boolean removeUser(User user) {
        repo.delete(user);
        return true;
    }

}
