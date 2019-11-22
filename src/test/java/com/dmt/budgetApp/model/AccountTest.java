package com.dmt.budgetApp.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AccountTest {
    private Account account;
    @Before
    public void setup(){
        account = new Account();
        account.setId(0);
        account.setInstitutionName("BOA");
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
        String name = "test";
        Account account = new Account(name);

        Assert.assertEquals(name, account.getNickname());
    }

    @Test
    public void constructorTest3(){
        Double balance = 100000.00;
        String institutionName = "BOA";
        Integer accountTypeId = 1;
        String nickname = "checking";
        Account account = new Account( balance,  institutionName, accountTypeId, nickname);

        Assert.assertEquals(nickname, account.getNickname());
        Assert.assertEquals(balance, account.getBalance());
        Assert.assertEquals(institutionName, account.getInstitutionName());

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
    public void getBalance() {
        Assert.assertTrue(account.getBalance()== 0.0);
    }
    @Test
    public void setBalance() {
        account.setBalance(4.0);
        Assert.assertTrue(account.getBalance()== 4.0);
    }

    @Test
    public void getInstitutionName() {
        Assert.assertTrue(account.getInstitutionName()=="BOA");
    }

    @Test
    public void setInstitutionName() {
        account.setInstitutionName("CAP1");
        Assert.assertTrue(account.getInstitutionName()=="CAP1");
    }
}