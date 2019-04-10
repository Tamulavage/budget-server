package com.group3.budgetApp.services;

import com.group3.budgetApp.exceptions.*;
import com.group3.budgetApp.model.Transaction;
import com.group3.budgetApp.repository.TransactionRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@SpringBootTest
public class TransactionServicesTest {

    @Autowired
    private TransactionServices transactionService ;
    @MockBean
    private TransactionRepository repo;

    @Before
    public void mockSetUp(){
        repo = mock(TransactionRepository.class);
        transactionService = new TransactionServices(repo);
    }


    @Test(expected = InvalidTransactionAmount.class)
    public void testAddTransactionNegativeAmount() throws InvalidTransactionAmount {
        //Given
        Transaction transaction = new Transaction(1, 1,2,"3",-1.0, null, null);
        // When
        transactionService.createTransaction(transaction);
    }

    @Test
    public void testAddTransactionValid() throws InvalidTransactionAmount {
        Transaction transaction = new Transaction(1, 1,2,"3",1.0, null, null);
        Transaction expected = new Transaction(1, 1,2,"3",1.0, null, null);
        when(repo.save(transaction)).thenReturn(expected);

        // When
        Transaction actual = transactionService.createTransaction(transaction);

        // Then
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testDeleteTransactionById() throws InvalidTransactionAmount {
        Transaction transaction = new Transaction(1, 1,2,"3",1.0, null, null);
        transactionService.createTransaction(transaction);

        transactionService.deleteTransaction(1);

        verify(repo).deleteById(1);
    }

    @Test
    public void testReturnAllTransactions()
    {
        // given
        ArrayList<Transaction> transactions = new ArrayList<>();
        Transaction transaction1 = new Transaction(1, 1,2,"3",1.0, null, null);
        Transaction transaction2 = new Transaction(2, 1,2,"3",1.0, null, null);
        transactions.add(transaction1);
        transactions.add(transaction2);
        when(repo.findAll()).thenReturn(transactions);

        Iterable<Transaction> actual = transactionService.getAllTransactions();

        // Then
        Assert.assertEquals(transactions, actual);
    }

    @Test
    public void testReturnAllTransactionsWhenNoValue()
    {
        when(repo.findAll()).thenReturn(null);

        Iterable<Transaction> actual = transactionService.getAllTransactions();

        Assert.assertNull(actual);
    }

}