package com.group3.budgetApp.controller;

import com.group3.budgetApp.model.User;
import com.group3.budgetApp.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<String> createUser(@RequestBody User user) {
        try {
            userService.createUser(user);
            return new ResponseEntity<>("User Created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        try{
            return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
        }catch(Exception e){
           return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>("User Deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Integer id, @RequestBody User user){
        try{
            userService.updateUser(user, id);
            return new ResponseEntity<>("User Updated", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("No User Found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/find/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable String username){
        try{
            return new ResponseEntity<>(userService.findByUsername(username), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> findAll(){
        try{
            return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/lastname/{last}")
    public ResponseEntity<List<User>> findByLastName(@PathVariable String last){
        try{
            return new ResponseEntity<>(userService.findAllByLast(last), HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}
