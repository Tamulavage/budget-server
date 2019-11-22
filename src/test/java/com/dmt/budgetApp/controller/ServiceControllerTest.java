package com.dmt.budgetApp.controller;

import com.dmt.budgetApp.exceptions.InvalidTransactionAmount;
import com.dmt.budgetApp.exceptions.ResourceNotFound;
import com.dmt.budgetApp.model.Account;
import com.dmt.budgetApp.services.AccountServices;
import com.dmt.budgetApp.services.ProfileAccountXrefService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ServiceControllerTest {

    @MockBean
    private AccountServices services;
    @MockBean
    private ProfileAccountXrefService profileAccountXrefService;    
    private ServiceController controller;

    @Before
    public void setup(){
        this.controller = new ServiceController(services, profileAccountXrefService);
    }

    @Test
    public void accountCreateTest() throws InvalidTransactionAmount {
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
    public void accountCreateTest2() throws InvalidTransactionAmount {
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
        Account account = new Account(1.0, "test", 1, "hey");
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
        Account account = new Account(1.0, "test",1,"hey");
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
        BDDMockito
                .given(services.getAccountById(2))
                .willReturn(null);
        //When
        ResponseEntity<Account> entity = controller.getAccountById(2);
        Account actualAccount = entity.getBody();
        //Then
        Assert.assertEquals(null, actualAccount);
    }

    @Test
    public void getAllTest() {
        Account account = new Account(1.0, "test",1,"hey");
        Account account2 = new Account(122.0, "test2" ,1,"hey2");

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
        BDDMockito
                .given(services.findAll())
                .willReturn(null);
        //When
        ResponseEntity<List<Account>> entity = controller.getAll();
        List<Account> actualAccount = entity.getBody();
        //Then
        Assert.assertEquals(null, actualAccount);
    }
}
