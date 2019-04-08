package com.group3.budgetApp.services;

import com.group3.budgetApp.controller.BudgetController;
import com.group3.budgetApp.model.TransactionWithdraw;
import com.group3.budgetApp.repository.TransactionWithdrawRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

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


    @Test
    public void testRepo(){
        transactionService = new TransactionWithDrawService(repo);
        Integer fromID = 1;
        Integer toID = 2;
        String memo = "testingFromMock";
    }

    @Test
    public void testAddValid(){
        TransactionWithdraw transaction = new TransactionWithdraw(1,2,"3",1.0);
        TransactionWithdraw expected = new TransactionWithdraw(1,2,"3",1.0);

        //site.moickito.org
        // create mock
        // Given
        TransactionWithdrawRepo mockRepo = mock(TransactionWithdrawRepo.class);
        TransactionWithDrawService service = new TransactionWithDrawService(mockRepo);
        when(mockRepo.save(transaction)).thenReturn(expected);

        // When
        TransactionWithdraw actual = service.createWithdrawTransaction(transaction);

        // Then
        Assert.assertEquals(expected, actual);
    }

  //  @Test
    public void testRemoveTransaction(){
        TransactionWithdraw transaction = new TransactionWithdraw(1,2,"3",1.0);

        TransactionWithdrawRepo mockRepo = mock(TransactionWithdrawRepo.class);
        TransactionWithDrawService service = new TransactionWithDrawService(mockRepo);
       //  when(mockRepo.delete(transaction));
      //  TransactionWithdraw actual = service.deleteTransaction(1);

     //   verify(mockRepo).delete(transaction);

    }



}