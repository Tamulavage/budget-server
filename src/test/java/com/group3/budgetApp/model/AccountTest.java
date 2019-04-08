package com.group3.budgetApp.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {
    private Account account;
    @Before
    public void setup(){
        account = new Account();
        account.setId(0);
        account.setInstitution_name("BOA");
        account.setUser_id(0);
        account.setType_id(0);
        account.setName("My Account");
        account.setBalance(0.0);
    }
    @Test
    public void getId() {
        int id = 5;
        account.setId(id);
        Assert.assertTrue(account.getId()==id);
    }
    @Test
    public void setId() {
        int id = 1;
        account.setId(id);
        Assert.assertTrue(account.getId()==id);
    }
    @Test
    public void getName() {
        assertEquals(account.getName(),"My Account");
    }
    @Test
    public void setName() {
        account.setName("Default");
        assertEquals(account.getName(),"Default");
    }
    @Test
    public void getBalance() {
        Assert.assertTrue(account.getBalance()== 0.0);
    }
    @Test
    public void setBalance() {
        account.setBalance(4.0);
        Assert.assertTrue(account.getBalance()== 4.0);
    }
    @Test
    public void getType_id() {
        Assert.assertTrue(account.getType_id()==0);
    }
    @Test
    public void setType_id() {
        account.setType_id(50);
        Assert.assertTrue(account.getType_id()==50);
    }
    @Test
    public void getUser_id() {
        Assert.assertTrue(account.getUser_id()==0);
    }
    @Test
    public void setUser_id() {
        account.setUser_id(100);
        Assert.assertTrue(account.getUser_id()==100);
    }
    @Test
    public void getInstitution_name() {
        Assert.assertTrue(account.getInstitution_name()=="BOA");
    }
    @Test
    public void setInstitution_name() {
        account.setInstitution_name("CAP1");
        Assert.assertTrue(account.getInstitution_name()=="CAP1");
    }
}