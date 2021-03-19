package com.dmt.budgetApp.controller;

import java.util.List;

import com.dmt.budgetApp.exceptions.InvalidAmount;
import com.dmt.budgetApp.exceptions.InvalidData;
import com.dmt.budgetApp.model.FutureBudget;
import com.dmt.budgetApp.model.FutureBudgetLineItem;
import com.dmt.budgetApp.model.FutureBudgetOrg;
import com.dmt.budgetApp.model.RawData;
import com.dmt.budgetApp.services.FutureBudgetService;
import com.dmt.budgetApp.utils.Constants;
import com.dmt.budgetApp.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
    public ResponseEntity<List<FutureBudgetOrg>> getAllOrgByUserId(@PathVariable Integer id,
            @RequestHeader HttpHeaders headers) {

        if (!Utils.isUserAllowedToExecuteFunction(headers)) {
            log.info("no access = trace={}", headers.get(Constants.HEADER_TRACE));
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        try {
            log.info("getAllOrgByUserId userId={}", id);
            return new ResponseEntity<>(futureBudgetService.findAllOrgByUserId(id), HttpStatus.OK);
        } catch (Exception e) {
            log.info("getAllOrgByUserId ", e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/future/output/{id}")
    public ResponseEntity<List<FutureBudget>> getOutputByUserId(@PathVariable Integer id,
            @RequestHeader HttpHeaders headers) {

        if (!Utils.isUserAllowedToExecuteFunction(headers)) {
            log.info("no access = trace={}", headers.get(Constants.HEADER_TRACE));
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        try {
            log.info("getOutputByUserId userId={}", id);
            return new ResponseEntity<>(futureBudgetService.findAllOutputByUserId(id), HttpStatus.OK);

        } catch (Exception e) {
            log.info("getOutputByUserId ", e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/future/input/{id}")
    public ResponseEntity<List<FutureBudget>> getInputByUserId(@PathVariable Integer id,
            @RequestHeader HttpHeaders headers) {

        if (!Utils.isUserAllowedToExecuteFunction(headers)) {
            log.info("no access = trace={}", headers.get(Constants.HEADER_TRACE));
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        try {
            log.info("getInputByUserId userId={}", id);
            return new ResponseEntity<>(futureBudgetService.findAllInputByUserId(id), HttpStatus.OK);

        } catch (Exception e) {
            log.info("getInputByUserId ", e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/future/sums/{id}")
    public ResponseEntity<List<FutureBudget>> getSumsbyUserId(@PathVariable Integer id,
            @RequestHeader HttpHeaders headers) {

        if (!Utils.isUserAllowedToExecuteFunction(headers)) {
            log.info("no access = trace={}", headers.get(Constants.HEADER_TRACE));
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        try {
            log.info("getSumsbyUserId userId={}", id);
            return new ResponseEntity<>(futureBudgetService.sumPerMonth(id), HttpStatus.OK);

        } catch (Exception e) {
            log.info("getSumsbyUserId ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/future/lineitem/{profileId}")
    public ResponseEntity<FutureBudget> updateBudgetLineItem(@RequestBody FutureBudget futureBudget,
            @PathVariable Integer profileId, @RequestHeader HttpHeaders headers) {

        if (!Utils.isUserAllowedToExecuteFunction(headers)) {
            log.info("no access = trace={}", headers.get(Constants.HEADER_TRACE));
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        try {
            log.info("updateBudgetLineItem userId={}", profileId);
            return new ResponseEntity<>(futureBudgetService.updateBudgetLineItem(futureBudget, profileId),
                    HttpStatus.ACCEPTED);

        } catch (Exception e) {
            log.info("updateBudgetLineItem ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/future/completemonth/{profileId}")
    public ResponseEntity<List<FutureBudgetLineItem>> completeMonth(@PathVariable Integer profileId,
            boolean forceComplete, @RequestHeader HttpHeaders headers) {

        if (!Utils.isUserAllowedToExecuteFunction(headers)) {
            log.info("no access = trace={}", headers.get(Constants.HEADER_TRACE));
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        try {
            log.info("completeMonth userId={}", profileId);
            return new ResponseEntity<>(futureBudgetService.completeMonth(profileId, forceComplete),
                    HttpStatus.ACCEPTED);
        } catch (InvalidAmount e) {
            log.info("completeMonth ", e);
            return new ResponseEntity<>(null, HttpStatus.PRECONDITION_FAILED);
        } catch (Exception e) {
            log.info("completeMonth ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/future/currentMonthValue/{profileId}")
    public ResponseEntity<RawData> getCurrentMonth(@PathVariable Integer profileId,
            @RequestHeader HttpHeaders headers) {

        if (!Utils.isUserAllowedToExecuteFunction(headers)) {
            log.info("no access = trace={}", headers.get(Constants.HEADER_TRACE));
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        try {
            log.info("getCurrentMonth userId={}", profileId);
            return new ResponseEntity<>(futureBudgetService.getCurrentMonth(profileId), HttpStatus.OK);
        } catch (Exception e) {
            log.info("getCurrentMonth ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/future/currentMonth/{profileId}")
    public ResponseEntity<RawData> currentMonth(@PathVariable Integer profileId, Integer month,
            @RequestHeader HttpHeaders headers) {

        if (!Utils.isUserAllowedToExecuteFunction(headers)) {
            log.info("no access = trace={}", headers.get(Constants.HEADER_TRACE));
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        try {
            log.info("currentMonth userId={}", profileId);
            return new ResponseEntity<>(futureBudgetService.currentMonth(profileId, month), HttpStatus.OK);
        } catch (Exception e) {
            log.info("currentMonth ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/future/specificMonth/{profileId}")
    public ResponseEntity<FutureBudgetLineItem> updateSpecificMonth(@PathVariable Integer profileId,
            @RequestBody FutureBudgetLineItem futureBudgetLineItem, @RequestHeader HttpHeaders headers) {

        if (!Utils.isUserAllowedToExecuteFunction(headers)) {
            log.info("no access = trace={}", headers.get(Constants.HEADER_TRACE));
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        try {
            log.info("updateSpecificMonth userId={}", profileId);
            return new ResponseEntity<>(futureBudgetService.updateBudgetLineItem(futureBudgetLineItem, profileId),
                    HttpStatus.ACCEPTED);
        } catch (InvalidData e) {
            log.info("updateSpecificMonth - precondition failed ", e);
            return new ResponseEntity<>(null, HttpStatus.PRECONDITION_FAILED);
        } catch (Exception e) {
            log.info("updateSpecificMonth ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/future/lineitem/{profileId}")
    public ResponseEntity<String> removeBudgetLineItem(@RequestBody FutureBudgetOrg futureBudgetOrg,
            @PathVariable Integer profileId, @RequestHeader HttpHeaders headers) {

        if (!Utils.isUserAllowedToExecuteFunction(headers)) {
            log.info("no access = trace={}", headers.get(Constants.HEADER_TRACE));
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        try {
            log.info("removeBudgetLineItem userId={}", profileId);
            futureBudgetService.removeBudgetLineItem(futureBudgetOrg, profileId);
            return new ResponseEntity<>("", HttpStatus.OK);
        } catch (Exception e) {
            log.info("updateSpecificMonth ", e);
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
