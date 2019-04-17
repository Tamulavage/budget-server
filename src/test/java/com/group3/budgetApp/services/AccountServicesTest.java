package com.group3.budgetApp.services;

import com.group3.budgetApp.exceptions.ResourceNotFound;
import com.group3.budgetApp.model.Account;
import com.group3.budgetApp.repository.AccountRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

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
    public void testFindAllById() throws ResourceNotFound {
        List<Account> expectedList = new ArrayList<>();
        Account expected = new Account("test",1.0, 1, "1",1,"hey");
        expectedList.add(expected);
        //When
        when(mockRepo.findById(1)).thenReturn(java.util.Optional.of(expected));//.thenReturn(java.util.Optional.of(expected));
        Account actual = services.getAccountById(1);
        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDelete(){
        Account account = new Account();
        //when
        when(mockRepo.getOne(1)).thenReturn(account);
        int id = 1;
        services.deleteAccount(id);
        verify(mockRepo, times(1)).delete(account);
    }




}