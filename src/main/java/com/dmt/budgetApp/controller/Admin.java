package com.dmt.budgetApp.controller;

import com.dmt.budgetApp.services.admin.Demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @CrossOrigin("https://budgetapp-client.herokuapp.com")
@CrossOrigin()
@RequestMapping("/admin")
public class Admin {

    
    private Demo demo;
 
    @Autowired
    public Admin(Demo demo) {
        this.demo = demo;
    }

    @GetMapping("/reset_demo/{id}")
    public ResponseEntity<Object> resetDemoUser(@PathVariable Integer id) {
        try {
            demo.resetDemoUser(id);
        } catch (Exception e) {
            return new ResponseEntity<>("{\"status\": \"Unknown Failure - look at logs\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<>("{\"status\": \"Demo User Updated\"}", HttpStatus.OK);
    }
    
}
