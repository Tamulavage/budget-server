package com.group3.budgetApp.services;

import com.group3.budgetApp.model.Account;
import com.group3.budgetApp.model.User;
import com.group3.budgetApp.repository.AccountRepository;
import com.group3.budgetApp.repository.UserRepository;
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
        Account account = new Account("Sean", "Rowan", "SpringKing");
        user.setId(1);
        User expected = new User("Sean", "Rowan", "SpringKing");
        expected.setId(1);
        //Verify that create method is being called;
        when(mockRepo.save(user)).thenReturn(expected);

        //Verify result
        User actual = services.createAccount(account);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFindById(){
        User expected = new User("Sean", "Rowan","SpringKing");
        //When
        when(mockRepo.findById(1)).thenReturn(java.util.Optional.of(expected));
        User actual = services.findById(1);
        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testDelete(){
        int id = 1;

        services.deleteUser(id);

        verify(mockRepo, times(1)).deleteById(id);
    }




}