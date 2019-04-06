package com.group3.budgetApp.controller;

import com.group3.budgetApp.model.User;
import com.group3.budgetApp.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserServices userService;


    public UserController(UserServices userServices) {
        this.userService = userServices;
    }

    @PostMapping("/user")
    public HttpStatus createUser(@RequestBody User user){
        try{
            userService.createUser(user);
            return HttpStatus.CREATED;
        }catch (Exception e){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id){
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public HttpStatus deleteUser(@PathVariable Integer id){
        try{
            userService.removeUser(id);
            return HttpStatus.OK;
        }catch (Exception e){
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

}
