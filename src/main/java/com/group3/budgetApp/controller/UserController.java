package com.group3.budgetApp.controller;

import com.group3.budgetApp.model.User;
import com.group3.budgetApp.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@RestController
@RequestMapping("/budget")
public class UserController {

    private UserServices userService;

    @Autowired
    public UserController(UserServices userServices) {
        this.userService = userServices;
    }

    @PostMapping("/user")
    public HttpStatus createUser(@RequestBody User user) {
        try {
            userService.createUser(user);
            return HttpStatus.CREATED;
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Integer id) {
        try{
            return userService.findById(id);
        }catch(Exception e){
           return null;
        }
    }

    @DeleteMapping("/user/{id}")
    public HttpStatus deleteUser(@PathVariable Integer id) {
        try {
            userService.deleteUser(id);
            return HttpStatus.OK;
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @PutMapping("/user/{id}")
    public void updateUser(@PathVariable Integer id, @RequestBody User user){
        try{
            userService.updateUser(user, id);
        }catch (Exception e){
            throw new NullPointerException();
        }
    }

    @GetMapping("/user")
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/user/lastname/{last}")
    public List<User> findByLastName(@PathVariable String last){
        return userService.findAllByLast(last);
    }

}
