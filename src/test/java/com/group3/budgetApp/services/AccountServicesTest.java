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
    public void testFindAllById() throws ResourceNotFound {
        List<Account> expectedList = new ArrayList<>();
        Account expected = new Account(1.0, "test", 1,"hey");
        expectedList.add(expected);
        //When
        when(mockRepo.findById(1)).thenReturn(java.util.Optional.of(expected));
        Account actual = services.getAccountById(1);
        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFindAll() {
        List<Account> expectedList = new ArrayList<>();
        Account expected = new Account(1.0, "test", 1,"hey");
        Account expected2 = new Account(2.0, "test2", 2,"hey2");
        expectedList.add(expected);
        expectedList.add(expected2);
        //When
        when(mockRepo.findAll()).thenReturn(expectedList);
        List<Account> actual = services.findAll();
        //Then
        Assert.assertEquals(expectedList, actual);
    }

    @Test
    public void testFindAllByUserID() {
        List<Account> expectedList = new ArrayList<>();
        List<Account> fullList = new ArrayList<>();
        Account expected = new Account(2.0, "test2", 2,"hey2");
        Account expected2 = new Account(1.0, "test" ,1,"hey");
        Account decoy3 = new Account(3.0, "test3" ,3,"hey3");
        expectedList.add(expected);
        expectedList.add(expected2);
        fullList.add(expected);
        fullList.add(expected2);
        fullList.add(decoy3);
        //When
        when(mockRepo.findAllByProfileUserId(2)).thenReturn(expectedList);
        List<Account> actual = services.findAllByUserId(2);
        //Then
        Assert.assertEquals(expectedList, actual);
    }

    @Test(expected = ResourceNotFound.class)
    public void testFindAllById2() throws ResourceNotFound {
        //When
        services.getAccountById(10);
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
