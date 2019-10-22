package com.group3.budgetApp.controller;

import java.util.List;

import com.group3.budgetApp.model.FutureBudget;
import com.group3.budgetApp.model.FutureBudgetOrg;
import com.group3.budgetApp.services.FutureBudgetService;

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
@RequestMapping("/budget")
public class FutureBudgetController {

    private FutureBudgetService futureBudgetService;

    @Autowired
    public FutureBudgetController(FutureBudgetService futureBudgetService) {
        this.futureBudgetService = futureBudgetService;
    }

    @GetMapping("/future/org/{id}")
    public ResponseEntity<List<FutureBudgetOrg>> getAllOrgByUserId(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(futureBudgetService.findAllOrgByUserId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/future/{id}")
    public ResponseEntity<List<FutureBudget>> getByUserId(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(futureBudgetService.findAllByUserId(id), HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
