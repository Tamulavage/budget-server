package com.group3.budgetApp.services;

import com.group3.budgetApp.controller.BudgetController;
import com.group3.budgetApp.model.TransactionWithdraw;
import com.group3.budgetApp.repository.TransactionWithdrawRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.*;


@SpringBootTest
public class TransactionWithDrawServiceTest {

    @Autowired
    private TransactionWithDrawService transactionService ;
    @MockBean
    private TransactionWithdrawRepo repo;


    @Before
    public void mockSetUp(){
        repo = mock(TransactionWithdrawRepo.class);
        transactionService = new TransactionWithDrawService(repo);
    }

    @Test
    public void testAddValid(){
        TransactionWithdraw transaction = new TransactionWithdraw(1, 1,2,"3",1.0);
        TransactionWithdraw expected = new TransactionWithdraw(1, 1,2,"3",1.0);

        when(repo.save(transaction)).thenReturn(expected);

        // When
        TransactionWithdraw actual = transactionService.createWithdrawTransaction(transaction);

        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddInvalidAmount(){
        //Given
        TransactionWithdraw transaction = new TransactionWithdraw(1, 1,2,"3",-1.0);
        // When
        transactionService.createWithdrawTransaction(transaction);
    }

    @Test
    public void testRemoveTransaction(){
        TransactionWithdraw transaction = new TransactionWithdraw(1, 1,2,"3",1.0);
        transactionService.createWithdrawTransaction(transaction);
        when(repo.getOne(1)).thenReturn(transaction);

        transactionService.deleteTransaction(1);

        verify(repo).delete(transaction);
    }

}