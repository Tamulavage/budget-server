package com.group3.budgetApp.services;

import com.group3.budgetApp.model.User;
import com.group3.budgetApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


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

    public void updateUser(User newUserData, Integer id) {
        User original = repo.findById(id).get();
        original.setFirstName(newUserData.getFirstName());
        original.setLastName(newUserData.getLastName());
        repo.save(original);
    }

    public List<User> findAllByLast(String last){
        return repo.findAllByLastName(last);
    }

    public User findByLast(String last){
        return repo.findByLastName(last);
    }

    public User findById(Integer id) {
        return repo.findById(id).get();
    }

    public Boolean deleteUser(Integer id) {
       repo.deleteById(id);
       return true;
    }

    public List<User> findAll(){
        return repo.findAll();
    }

}
