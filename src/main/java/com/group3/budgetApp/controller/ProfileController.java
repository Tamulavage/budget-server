package com.group3.budgetApp.controller;

import com.group3.budgetApp.model.Profile;
import com.group3.budgetApp.services.ProfileServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin("https://budgetapp-client.herokuapp.com")
@RequestMapping("/budget")
public class ProfileController {

    private ProfileServices userService;

    @Autowired
    public ProfileController(ProfileServices userServices) {
        this.userService = userServices;
    }

    @PostMapping("/profile")
    public ResponseEntity<Profile> createUser(@RequestBody Profile profile) {
        try {
            userService.createUser(profile);
            return new ResponseEntity<>(profile, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<Profile> getUser(@PathVariable Integer id) {
        try {
            Profile profile = userService.findById(id);
            if (profile == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/profile/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>("Profile Deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/profile/{id}")
    public ResponseEntity<Profile> updateUser(@PathVariable Integer id, @RequestBody Profile profile) {
        try {
            return new ResponseEntity<>(userService.updateUser(profile, id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/profile/find/{username}")
    public ResponseEntity<Profile> findByUsername(@PathVariable String username) {

        try {
            Profile profile = userService.findByUsername(username);
            if (profile == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(userService.findByUsername(username), HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/profile")
    public ResponseEntity<List<Profile>> findAll() {
        try {
            return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/profile/lastname/{last}")
    public ResponseEntity<List<Profile>> findByLastName(@PathVariable String last) {
        try {
            List<Profile> profileList = userService.findAllByLast(last);
            if (profileList.size() == 0) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(profileList, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/profile/name/{last}/{first}")
    public ResponseEntity<Profile> findByFull(@PathVariable String last, @PathVariable String first){
        try {
            Profile profile = userService.findByFullName(last, first);
            if (profile == null) {
                return new ResponseEntity<>(userService.findByFullName(first, last), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

