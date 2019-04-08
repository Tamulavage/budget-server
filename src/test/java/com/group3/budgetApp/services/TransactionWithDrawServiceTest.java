package com.group3.budgetApp.services;

import com.group3.budgetApp.controller.BudgetController;
import com.group3.budgetApp.model.Transaction;
import com.group3.budgetApp.model.TransactionWithdraw;
import com.group3.budgetApp.repository.TransactionWithdrawRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.mockito.Mockito.*;


//@SpringBootTest
@RunWith(SpringRunner.class)
@WebMvcTest(TransactionWithDrawService.class)
public class TransactionWithDrawServiceTest {

    @Autowired
    private TransactionWithDrawService transactionService ;
    @MockBean
    private TransactionWithdrawRepo repo;

    private BudgetController controller;

    @Before
    public void setEMF(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("budgetApp");
        EntityManager em = emf.createEntityManager();
        //this.transactionService =new TransactionWithDrawService(em);
        this.controller = new BudgetController(transactionService);
    }

    @Test
    public void testRepo(){
        transactionService = new TransactionWithDrawService(repo);
        Integer fromID = 1;
        Integer toID = 2;
        String memo = "testingFromMock";
     //   transactionService.testWithRepo(fromID, toID, memo);;
    }

    @Test
    public void testAdd(){
        TransactionWithdraw transaction = new TransactionWithdraw(1,2,"3");
        TransactionWithdraw expected = new TransactionWithdraw(1,2,"3");

        //site.moickito.org
        // create mock
        // Given
        TransactionWithdrawRepo mockRepo = mock(TransactionWithdrawRepo.class);
        TransactionWithDrawService service = new TransactionWithDrawService(mockRepo);

        when(mockRepo.save(transaction)).thenReturn(expected);

        // When
        TransactionWithdraw actual = service.createWithdrawTransaction(transaction);

        // Then
        Assert.assertEquals(expected, actual);}

//    @Test
//    public void testCreateWithdrawTransaction(){
//
//        // Given
//        Integer fromID = 1;
//        Integer toID = 2;
//        String memo = "testing";
//        Integer newId ;
//        TransactionWithdraw transaction ;
//
//        // When
//        transaction = transactionService.createTransaction(fromID, toID, memo);
//        newId = transaction.getTransactionId();
//
//        // Then
//        Assert.assertNotNull(newId);
//        System.out.println(newId);
//
//        // clean up
//        transactionService.deleteTransaction(newId);
//    }

}