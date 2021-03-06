package com.dmt.budgetApp.controller;

import com.dmt.budgetApp.exceptions.ResourceNotFound;
import com.dmt.budgetApp.model.Profile;
import com.dmt.budgetApp.services.ProfileServices;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class ProfileControllerTest {
    @MockBean
    private ProfileServices services;
    private Profile profile;
    private ProfileController controller;
    List<Profile> list;

    private HttpHeaders header = new HttpHeaders();

    @Before
    public void setup() {
        this.controller = new ProfileController(services);
        profile = new Profile("S", "R", "username");
        profile.setId(1);
        list = new ArrayList<>();
        list.add(profile);
    }


    @Test
    public void createUser() {
        HttpStatus expected = HttpStatus.CREATED;
        BDDMockito
                .given(services.createUser(profile))
                .willReturn(profile);
        //When
        ResponseEntity<Profile> entity = controller.createUser(profile, header);
        HttpStatus actual = entity.getStatusCode();
        Profile actualProfile = entity.getBody();
        //Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(profile, actualProfile);
    }

    @Test
    public void getUser() throws ResourceNotFound {
        HttpStatus expected = HttpStatus.OK;
        BDDMockito
                .given(services.findById(1))
                .willReturn(profile);
        //When
        ResponseEntity<Profile> responseEntity = controller.getUser(1, header);
        HttpStatus actual = responseEntity.getStatusCode();
        Profile expectedPro = responseEntity.getBody();
        //Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(profile, expectedPro);
    }

    @Test
    public void deleteUser() {
        HttpStatus expected = HttpStatus.OK;
        BDDMockito
                .given(services.deleteUser(1))
                .willReturn(true);
        //When
        ResponseEntity<String> responseEntity = controller.deleteUser(1, header);
        HttpStatus actual = responseEntity.getStatusCode();
        String expectedPro = responseEntity.getBody();
        //Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals("Profile deleted", expectedPro);
    }

    @Test
    public void updateUser() {
        HttpStatus expected = HttpStatus.OK;
        Profile newPro = new Profile("Mike", "KRUNK", "Notchyo");
        BDDMockito
                .given(services.updateUser(newPro, 1))
                .willReturn(newPro);
        //When
        ResponseEntity<Profile> responseEntity = controller.updateUser(1,newPro, header);
        HttpStatus actual = responseEntity.getStatusCode();
        Profile expectedPro = responseEntity.getBody();
        //Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(newPro, expectedPro);
    }

    @Test
    public void findByUsername() {
        HttpStatus expected = HttpStatus.OK;
        BDDMockito
                .given(services.findByUsername("SpringKing"))
                .willReturn(profile);
        //When
        ResponseEntity<Profile> responseEntity = controller.findByUsername("SpringKing", header);
        HttpStatus actual = responseEntity.getStatusCode();
        Profile expectedPro = responseEntity.getBody();
        //Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(profile, expectedPro);
    }

    @Test
    public void findAll() {
        HttpStatus expected = HttpStatus.OK;
        BDDMockito
                .given(services.findAll())
                .willReturn(list);
        //When
        ResponseEntity<List<Profile>> responseEntity = controller.findAll();
        HttpStatus actual = responseEntity.getStatusCode();
        List<Profile> expectedPro = responseEntity.getBody();
        //Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(list, expectedPro);
    }

    @Test
    public void findByLastName() {
        HttpStatus expected = HttpStatus.OK;
        BDDMockito
                .given(services.findAllByLast("Test"))
                .willReturn(list);
        //When
        ResponseEntity<List<Profile>> responseEntity = controller.findByLastName("Test", header);
        HttpStatus actual = responseEntity.getStatusCode();
        List<Profile> expectedPro = responseEntity.getBody();
        //Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(list, expectedPro);
    }


    @Test
    public void testFindException() throws ResourceNotFound {
        HttpStatus expected = HttpStatus.INTERNAL_SERVER_ERROR;
        BDDMockito
                .given(services.findById(99))
                .willReturn(null);
        //When
        ResponseEntity<Profile> responseEntity = controller.getUser(99, header);
        HttpStatus actual = responseEntity.getStatusCode();
        Assert.assertEquals(expected, actual);
    }

}
