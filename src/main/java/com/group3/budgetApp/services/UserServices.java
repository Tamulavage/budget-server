package com.group3.budgetApp.services;

import com.group3.budgetApp.model.User;
import com.group3.budgetApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


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

    public User createUser(String firstName, String lastName, String userName) {
        User user = new User(firstName, lastName, userName);
        repo.save(user);
        return user;
    }

    public void updateUser(User newUserData, Integer id) {
        User original = repo.findById(id).get();
        original.setFirstName(newUserData.getFirstName());
        original.setLastName(newUserData.getLastName());
        repo.save(original);
    }

    public List<User> findAllByLast(String last){
        return repo.findAllByLastName(last);
    }

    public User findByUsername(String username){
        return repo.findByUserName(username);
    }

    public User findById(Integer id) {
        return repo.findById(id).get();
    }

    public void deleteUser(Integer id) {
        repo.deleteById(id);
    }

    public List<User> findAll(){
        return repo.findAll();
    }

    public User findByFullName(String first, String last){
        return repo.findByFirstNameAndLastName(first, last);
    }

}
