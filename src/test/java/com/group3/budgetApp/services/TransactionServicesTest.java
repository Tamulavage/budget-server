package com.group3.budgetApp.services;

import com.group3.budgetApp.exceptions.*;
import com.group3.budgetApp.model.Account;
import com.group3.budgetApp.model.Transaction;
import com.group3.budgetApp.repository.AccountRepository;
import com.group3.budgetApp.repository.TransactionRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class TransactionServicesTest {
    
    @Autowired
    private TransactionServices transactionService;
    @MockBean
    private TransactionRepository repo;
    @MockBean
    private AccountRepository accountRepository;
    
    @Before
    public void mockSetUp() {
        repo = mock(TransactionRepository.class);
        accountRepository =  mock(AccountRepository.class);
        transactionService = new TransactionServices(repo,  accountRepository);
    }
    
    
    // @Test(expected = InvalidTransactionAmount.class)
    // public void testAddTransactionNegativeAmount() throws InvalidTransactionAmount {
    //     //Given
    //     Transaction transaction = new Transaction(1, 1, 2, "3", -1.0, null, null);
    //     // When
    //     transactionService.createTransaction(transaction);
    // }
    
    // @Test
    // public void testAddTransactionValidWithdraw() throws InvalidTransactionAmount {
    //     Transaction transaction = new Transaction(1, 1, null, "3", 1.0, null, null);
    //     Transaction expected = new Transaction(1, 1, null, "3", 1.0, null, null);
    //     TransactionType transactionType = new TransactionType("Other");

    //     Account account = new Account("test",10.0, null, null, null,null);

    //     when(repo.save(transaction)).thenReturn(expected);
    //     when(transactionTypeRepository.findByDescription("Other")).thenReturn(transactionType);
    //     when(accountRepository.findAccountById(1)).thenReturn(account);

    //     // When
    //     Transaction actual = transactionService.createTransaction(transaction);

    //     // Then
    //     Assert.assertEquals(expected, actual);
    // }

    // @Test(expected = IllegalArgumentException.class)
    // public void testAddTransactionInvalidWithdraw() throws InvalidTransactionAmount {
    //     Transaction transaction = new Transaction(1, 1, null, "3", 100.0, null, null);
    //     Transaction expected = new Transaction(1, 1, null, "3", 100.0, null, null);
    //     TransactionType transactionType = new TransactionType("Other");

    //     Account account = new Account("test",10.0, null, null, null,null);

    //     when(repo.save(transaction)).thenReturn(expected);
    //     when(transactionTypeRepository.findByDescription("Other")).thenReturn(transactionType);
    //     when(accountRepository.findAccountById(1)).thenReturn(account);

    //     // When
    //     Transaction actual = transactionService.createTransaction(transaction);

    //     // Then
    //     Assert.assertEquals(expected, actual);
    // }

    // @Test
    // public void testAddTransactionValidDepost() throws InvalidTransactionAmount {
    //     Transaction transaction = new Transaction(1, null, 1, "3", 100.0, null, null);
    //     Transaction expected = new Transaction(1, null, 1, "3", 100.0, null, null);
    //     TransactionType transactionType = new TransactionType("Other");

    //     Account account = new Account("test",10.0, null, null, null,null);

    //     when(repo.save(transaction)).thenReturn(expected);
    //     when(transactionTypeRepository.findByDescription("Other")).thenReturn(transactionType);
    //     when(accountRepository.findAccountById(1)).thenReturn(account);

    //     // When
    //     Transaction actual = transactionService.createTransaction(transaction);

    //     // Then
    //     Assert.assertEquals(expected, actual);
    // }

    // @Test
    // public void testAddTransactionValidTransfer() throws InvalidTransactionAmount {
    //     Transaction transaction = new Transaction(1, 2, 1, "3", 1.0, null, null);
    //     Transaction expected = new Transaction(1, 2, 1, "3", 1.0, null, null);
    //     TransactionType transactionType = new TransactionType("Other");

    //     Account account1 = new Account("test",10.0, null, null, null,null);
    //     Account account2 = new Account("test",10.0, null, null, null,null);

    //     when(repo.save(transaction)).thenReturn(expected);
    //     when(transactionTypeRepository.findByDescription("Other")).thenReturn(transactionType);
    //     when(accountRepository.findAccountById(1)).thenReturn(account1);
    //     when(accountRepository.findAccountById(2)).thenReturn(account2);

    //     // When
    //     Transaction actual = transactionService.createTransaction(transaction);

    //     // Then
    //     Assert.assertEquals(expected, actual);
    // }
    
    
    // @Test
    // public void testDeleteTransactionById() throws InvalidTransactionAmount {
    //     Transaction transaction = new Transaction(1, null, 1, "3", 1.0, null, null);
    //     Account account = new Account("test",10.0, null, null, null,null);
    //     when(accountRepository.findAccountById(1)).thenReturn(account);
    //     transactionService.createTransaction(transaction);
        
    //     transactionService.deleteTransaction(1);
        
    //     verify(repo).deleteById(1);
    // }
    
    // @Test
    // public void testReturnAllTransactions() {
    //     // given
    //     ArrayList<Transaction> transactions = new ArrayList<>();
    //     Transaction transaction1 = new Transaction(1, 1, 2, "3", 1.0, null, null);
    //     Transaction transaction2 = new Transaction(2, 1, 2, "3", 1.0, null, null);
    //     transactions.add(transaction1);
    //     transactions.add(transaction2);
    //     when(repo.findAll()).thenReturn(transactions);
        
    //     Iterable<Transaction> actual = transactionService.getAllTransactions();
        
    //     // Then
    //     Assert.assertEquals(transactions, actual);
    // }
    
    // @Test
    // public void testFindTransactionById() throws ResourceNotFound {
    //     //Given
    //     Transaction transaction1 = new Transaction(1, 1, 2, "3", 1.0, null, null);
    //     repo.save(transaction1);
    //     when(repo.findById(1)).thenReturn(Optional.of(transaction1));
    //     //When
    //     Transaction actual = transactionService.findTransactionById(1);
    //     //Then
    //     Assert.assertEquals(transaction1, actual);
    //     verify(repo).findById(1);
    // }
    
    @Test
    public void testReturnAllTransactionsWhenNoValue() {
        when(repo.findAll()).thenReturn(null);
        
        Iterable<Transaction> actual = transactionService.getAllTransactions();
        
        Assert.assertNull(actual);
    }
}
