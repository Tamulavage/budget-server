package com.group3.budgetApp.controller;

import com.group3.budgetApp.model.Account;
import com.group3.budgetApp.services.AccountServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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

    @Test
    public void accountCreate() {
        Account account = new Account();
        given(services.createAccount(account)).willReturn(account);
        //this.mvc.perform();
    }

    @Test
    public void accountRemove() {
    }

    @Test
    public void getAccountById() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void getAllByUserId() {
    }
}