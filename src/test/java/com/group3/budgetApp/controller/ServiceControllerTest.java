package com.group3.budgetApp.controller;

import com.group3.budgetApp.exceptions.ResourceNotFound;
import com.group3.budgetApp.model.Account;
import com.group3.budgetApp.model.Profile;
import com.group3.budgetApp.services.AccountServices;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ServiceControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private AccountServices services;
    private ServiceController controller;

    @Before
    public void setup(){
        this.controller = new ServiceController(services);
    }

    @Test
    public void accountCreateTest() {
        Account account = new Account();
        HttpStatus expected = HttpStatus.CREATED;
        BDDMockito
                .given(services.createAccount(account))
                .willReturn(account);
        //When
        ResponseEntity<Account> entity = controller.accountCreate(account);
        HttpStatus actual = entity.getStatusCode();
        Account actualAccount = entity.getBody();
        //Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(account, actualAccount);
    }
    @Test
    public void accountCreateTest2() {
        Account account = null;
        HttpStatus expected = HttpStatus.BAD_REQUEST;
        BDDMockito
                .given(services.createAccount(account))
                .willReturn(account);
        //When
        ResponseEntity<Account> entity = controller.accountCreate(account);
        HttpStatus actual = entity.getStatusCode();
        Account actualAccount = entity.getBody();
        //Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(account, actualAccount);
    }

    @Test
    public void accountRemoveTest() {
        Account account = new Account();
        HttpStatus expected = HttpStatus.OK;
        BDDMockito
                .given(services.deleteAccount(1)).willReturn(true);
        //When
        ResponseEntity<String> entity = controller.accountRemove(1);
        HttpStatus actual = entity.getStatusCode();
        String expectedStr = entity.getBody();
        //Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals("Account Deleted", expectedStr);
    }

    @Test
    public void getAccountByUserIdTest() {
        Account account = new Account("test",1.0, 1, "1",1,"hey");
        List<Account> list = new ArrayList<>();
        list.add(account);
        HttpStatus expected = HttpStatus.OK;
        BDDMockito
                .given(services.findAllByUserId(1))
                .willReturn(list);
        //When
        ResponseEntity<List<Account>> entity = controller.getAllByUserId(1);
        HttpStatus actual = entity.getStatusCode();
        List<Account> actualAccount = entity.getBody();
        //Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(list, actualAccount);
    }
    @Test
    public void getAccountByIdTest() throws ResourceNotFound {
        Account account = new Account("test",1.0, 1, "1",1,"hey");
        HttpStatus expected = HttpStatus.OK;
        BDDMockito
                .given(services.getAccountById(1))
                .willReturn(account);
        //When
        ResponseEntity<Account> entity = controller.getAccountById(1);
        HttpStatus actual = entity.getStatusCode();
        Account actualAccount = entity.getBody();
        //Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(account, actualAccount);
    }
    @Test
    public void getAccountByIdTest2() throws ResourceNotFound {
        Account account = new Account("test",1.0, 1, "1",1,"hey");
        HttpStatus expected = HttpStatus.NOT_FOUND;
        BDDMockito
                .given(services.getAccountById(2))
                .willReturn(null);
        //When
        ResponseEntity<Account> entity = controller.getAccountById(2);
        HttpStatus actual = entity.getStatusCode();
        Account actualAccount = entity.getBody();
        //Then
        Assert.assertEquals(null, actualAccount);
    }

    @Test
    public void getAllTest() {
        Account account = new Account("test",1.0, 1, "1",1,"hey");
        Account account2 = new Account("test2",122.0, 1, "2",1,"hey2");

        List<Account> list = new ArrayList<>();
        list.add(account);
        list.add(account2);
        HttpStatus expected = HttpStatus.OK;
        BDDMockito
                .given(services.findAll())
                .willReturn(list);
        //When
        ResponseEntity<List<Account>> entity = controller.getAll();
        HttpStatus actual = entity.getStatusCode();
        List<Account> actualAccount = entity.getBody();
        //Then
        Assert.assertEquals(expected, actual);
        Assert.assertEquals(list, actualAccount);
    }
    @Test
    public void getAllTest2() {
        HttpStatus expected = HttpStatus.NOT_FOUND;
        BDDMockito
                .given(services.findAll())
                .willReturn(null);
        //When
        ResponseEntity<List<Account>> entity = controller.getAll();
        HttpStatus actual = entity.getStatusCode();
        List<Account> actualAccount = entity.getBody();
        //Then
        Assert.assertEquals(null, actualAccount);
    }
}