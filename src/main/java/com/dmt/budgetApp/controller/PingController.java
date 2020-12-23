package com.dmt.budgetApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
// @CrossOrigin("https://budgetapp-client.herokuapp.com")
@CrossOrigin()
@RequestMapping("/budget")
public class PingController {

    @GetMapping("/ping")
    public ResponseEntity<Object> simplePing() {
        try {
            return new ResponseEntity<>("{\"status\": \"up\"}", HttpStatus.OK);

        } catch (Exception e) {
            log.error("ping unknown error ", e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}