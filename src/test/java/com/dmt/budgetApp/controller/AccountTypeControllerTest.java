package com.dmt.budgetApp.controller;

import java.util.ArrayList;
import java.util.List;

import com.dmt.budgetApp.model.AccountType;
import com.dmt.budgetApp.services.AccountTypeServices;

import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class AccountTypeControllerTest {

    @MockBean
    private AccountTypeServices accountTypeServices;

    private AccountTypeController controller;
    private List<AccountType> dummyAccountType; 


    @Before
    public void setup() {
        this.controller = new AccountTypeController(accountTypeServices);

        this.dummyAccountType= new ArrayList<>();
        AccountType tempAccountType = new AccountType();
        tempAccountType.setId(1);
        tempAccountType.setDescription("test");
        this.dummyAccountType.add(tempAccountType);
    }

    @Test
    public void getAccountTypesStatusOKTest() {
        // Given        
        HttpStatus expected = HttpStatus.OK;
        BDDMockito
            .given(accountTypeServices.getAllAccountTypes())
            .willReturn(dummyAccountType);

        // WHen
        ResponseEntity<Iterable<AccountType>> entity = controller.getAccountTypes();
        HttpStatus actual = entity.getStatusCode();

        // Then
        Assert.assertEquals(expected, actual);
    }

    
    @Test
    public void getAccountTypesValidResponseTest() {
        // Given 
        List<AccountType> expectedAccountType =  new ArrayList<>();
        AccountType tempAccountType = new AccountType();
        tempAccountType.setId(1);
        tempAccountType.setDescription("test");
        expectedAccountType.add(tempAccountType);

        BDDMockito
            .given(accountTypeServices.getAllAccountTypes())
            .willReturn(dummyAccountType);

        // WHen
        ResponseEntity<Iterable<AccountType>> entity = controller.getAccountTypes();
        List<AccountType> actualAccountType = (List<AccountType>) entity.getBody();

        // Then
        // Description
        Assert.assertEquals(expectedAccountType.get(0).getDescription(), 
            actualAccountType.get(0).getDescription());
        // id            
        Assert.assertEquals(expectedAccountType.get(0).getId(), 
            actualAccountType.get(0).getId());    
    }
}