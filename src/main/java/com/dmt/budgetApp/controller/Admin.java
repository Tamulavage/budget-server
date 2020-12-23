package com.dmt.budgetApp.controller;

import com.dmt.budgetApp.services.admin.Maintenance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
// @CrossOrigin("https://budgetapp-client.herokuapp.com")
@CrossOrigin()
@RequestMapping("/budget")
public class Admin {

    
    private Maintenance maintenance;
 
    @Autowired
    public Admin(Maintenance maintenance) {
        this.maintenance = maintenance;
    }

    @PostMapping("/reset_demo/{id}")
    public ResponseEntity<Object> resetDemoValues(@PathVariable Integer id) {
        try {
            log.info("resetDemoValues called userId= {}");
            maintenance.nullUserValues(id);
            maintenance.resetDemoValues(id);
        } catch (Exception e) {
            log.error("resetDemoValues error ", e);
            return new ResponseEntity<>("{\"status\": \"Unknown Failure - look at logs\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<>("{\"status\": \"Demo User Updated\"}", HttpStatus.OK);
    }

    @PostMapping("/reset/{id}")
    public ResponseEntity<Object> resetValues(@PathVariable Integer id) {
        try {
            log.info("resetValues called userId= {}");
            maintenance.nullUserValues(id);
        } catch (Exception e) {
            log.error("resetDemoValues error ", e);
            return new ResponseEntity<>("{\"status\": \"Unknown Failure - look at logs\"}", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        return new ResponseEntity<>("{\"status\": \"Demo User Updated\"}", HttpStatus.OK);
    }
    
}
