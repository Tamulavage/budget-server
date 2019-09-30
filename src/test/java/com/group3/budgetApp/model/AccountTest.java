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
        account.setName("My Account");
        account.setBalance(0.0);
    }
    @Test
    public void constructorTest(){
        Integer id = 1;
        Account account = new Account(id);
        Assert.assertEquals(account.getId(),id);
    }
    @Test
    public void constructorTest2(){
        Integer user_id = 3;
        String name = "test";
        Account account = new Account(name, user_id);

        Assert.assertEquals(user_id,account.getUserId());
        Assert.assertEquals(name, account.getName());
    }
    @Test
    public void constructorTest3(){
        Integer user_id = 3;
        String name = "test";
        Double balance = 100000.00;
        String institution_name = "BOA";
        Integer accountTypeId = 1;
        String nickname = "checking";
        Account account = new Account(name, balance, user_id, institution_name, accountTypeId, nickname);

        Assert.assertEquals(user_id,account.getUserId());
        Assert.assertEquals(name, account.getName());
        Assert.assertEquals(balance, account.getBalance());

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