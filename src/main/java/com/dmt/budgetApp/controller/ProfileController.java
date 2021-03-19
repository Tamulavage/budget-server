package com.dmt.budgetApp.controller;

import java.util.List;

import com.dmt.budgetApp.model.Profile;
import com.dmt.budgetApp.services.ProfileServices;
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
import org.springframework.web.bind.annotation.PutMapping;
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
public class ProfileController {

    private ProfileServices userService;

    @Autowired
    public ProfileController(ProfileServices userServices) {
        this.userService = userServices;
    }

    @PostMapping("/profile")
    public ResponseEntity<Profile> createUser(@RequestBody Profile profile, @RequestHeader HttpHeaders headers) {

        if (!Utils.isUserAllowedToExecuteFunction(headers)) {
            log.info("no access = trace={}", headers.get(Constants.HEADER_TRACE));
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        try {
            log.info("createUser called");
            userService.createUser(profile);
            return new ResponseEntity<>(profile, HttpStatus.CREATED);
        } catch (Exception e) {
            log.info("createUser ", e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<Profile> getUser(@PathVariable Integer id, @RequestHeader HttpHeaders headers) {

        if (!Utils.isUserAllowedToExecuteFunction(headers)) {
            log.info("no access = trace={}", headers.get(Constants.HEADER_TRACE));
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        try {
            log.info("getUser userId", id);
            Profile profile = userService.findById(id);
            if (profile == null) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
            }
        } catch (Exception e) {
            log.info("getUser ", e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/profile/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id, @RequestHeader HttpHeaders headers) {

        if (!Utils.isUserAllowedToExecuteFunction(headers)) {
            log.info("no access = trace={}", headers.get(Constants.HEADER_TRACE));
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        try {
            log.info("deleteUser userId", id);
            userService.deleteUser(id);
            return new ResponseEntity<>("Profile deleted", HttpStatus.OK);
        } catch (Exception e) {
            log.info("deleteUser ", e);
            return new ResponseEntity<>("Profile does not exist", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/profile/{id}")
    public ResponseEntity<Profile> updateUser(@PathVariable Integer id, @RequestBody Profile profile,
            @RequestHeader HttpHeaders headers) {

        if (!Utils.isUserAllowedToExecuteFunction(headers)) {
            log.info("no access = trace={}", headers.get(Constants.HEADER_TRACE));
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        try {
            log.info("updateUser userId", id);
            return new ResponseEntity<>(userService.updateUser(profile, id), HttpStatus.OK);
        } catch (Exception e) {
            log.info("updateUser ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/profile/find/{username}")
    public ResponseEntity<Profile> findByUsername(@PathVariable String username, @RequestHeader HttpHeaders headers) {

        if (!Utils.isUserAllowedToExecuteFunction(headers)) {
            log.info("no access = trace={}", headers.get(Constants.HEADER_TRACE));
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        try {
            log.info("findByUsername username {}", username);
            Profile profile = userService.findByUsername(username);
            if (profile == null) {
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(userService.findByUsername(username), HttpStatus.OK);
            }
        } catch (Exception e) {
            log.info("findByUsername ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/profile")
    // TODO: Remove if not called
    public ResponseEntity<List<Profile>> findAll() {
        try {
            log.info("findAll ");
            return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            log.info("findAll ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/profile/lastname/{last}")
    public ResponseEntity<List<Profile>> findByLastName(@PathVariable String last, @RequestHeader HttpHeaders headers) {

        if (!Utils.isUserAllowedToExecuteFunction(headers)) {
            log.info("no access = trace={}", headers.get(Constants.HEADER_TRACE));
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        try {
            log.info("findByLastName :{}", last);
            List<Profile> profileList = userService.findAllByLast(last);
            if (profileList.size() == 0) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                return new ResponseEntity<>(profileList, HttpStatus.OK);
            }
        } catch (Exception e) {
            log.info("findByLastName ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
