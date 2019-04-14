package com.group3.budgetApp.services;

import com.group3.budgetApp.model.Account;
import com.group3.budgetApp.repository.AccountRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
public class AccountServicesTest {

    @Mock
    private AccountRepository mockRepo;
    @InjectMocks
    private AccountServices services;

    @Before
    public void setup(){
        mockRepo = mock(AccountRepository.class);
        services = new AccountServices(mockRepo);
    }

    @Test
    public void testCreateAccount(){
        Account account = new Account();
        account.setId(1);
        Account expected = new Account();
        expected.setId(1);
        //Verify that create method is being called;
        when(mockRepo.save(account)).thenReturn(expected);

        //Verify result
        Account actual = services.createAccount(account);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFindById(){
        Account expected = new Account();
        //When
        when(mockRepo.getAccountById(1)).thenReturn(expected);//.thenReturn(java.util.Optional.of(expected));
        Account actual = services.getAccountById(1);
        //Then
        Assert.assertEquals(expected, actual);
        System.out.println(expected.toString());
    }

    @Test
    public void testDelete(){
        Account account = new Account();
        int id = 1;
        services.deleteAccount(id);
        verify(mockRepo, times(1)).deleteById(id);
    }




}