package com.dmt.budgetApp.controller;

import java.util.List;

import com.dmt.budgetApp.exceptions.InvalidAmount;
import com.dmt.budgetApp.model.FutureBudget;
import com.dmt.budgetApp.model.FutureBudgetLineItem;
import com.dmt.budgetApp.model.FutureBudgetOrg;
import com.dmt.budgetApp.services.FutureBudgetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/future/output/{id}")
    public ResponseEntity<List<FutureBudget>> getOutputByUserId(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(futureBudgetService.findAllOutputByUserId(id), HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/future/input/{id}")
    public ResponseEntity<List<FutureBudget>> getInputByUserId(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(futureBudgetService.findAllInputByUserId(id), HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/future/sums/{id}")
    public ResponseEntity<List<FutureBudget>> getSumsbyUserId(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(futureBudgetService.sumPerMonth(id), HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/future/lineitem/{profileId}")
    public ResponseEntity<FutureBudget> updateBudgetLineItem(@RequestBody FutureBudget futureBudget, @PathVariable Integer profileId) {
        try {
            return new ResponseEntity<>(futureBudgetService.updateBudgetLineItem(futureBudget, profileId), HttpStatus.ACCEPTED);

        } catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/future/completemonth/{profileId}")
    public ResponseEntity<List<FutureBudgetLineItem>> completeMonth(@PathVariable Integer profileId, boolean forceComplete) {
        try {
            return new ResponseEntity<>(futureBudgetService.completeMonth(profileId, forceComplete), HttpStatus.ACCEPTED);
        } catch (InvalidAmount e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.PRECONDITION_FAILED);
        }catch (Exception e) {
            System.out.println(e.toString());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } 

    @DeleteMapping("/future/lineitem/{profileId}")
    public ResponseEntity<String> removeBudgetLineItem(@RequestBody FutureBudgetOrg futureBudgetOrg, @PathVariable Integer profileId){
        try {
            futureBudgetService.removeBudgetLineItem(futureBudgetOrg, profileId);
            return new ResponseEntity<>("", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
