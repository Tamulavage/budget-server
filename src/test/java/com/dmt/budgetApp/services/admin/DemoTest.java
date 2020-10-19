package com.dmt.budgetApp.services.admin;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.dmt.budgetApp.model.Account;
import com.dmt.budgetApp.model.Transaction;
import com.dmt.budgetApp.repository.AccountRepository;
import com.dmt.budgetApp.repository.ProfileAccountXrefRepository;
import com.dmt.budgetApp.repository.TransactionRepository;
import com.dmt.budgetApp.services.TransactionServices;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class DemoTest {

    @Autowired
    private Demo demo;
    @MockBean
    private TransactionRepository transactionRepositoryRepo;
    @MockBean
    private AccountRepository accountRepository;
    @MockBean    
    private ProfileAccountXrefRepository profileAccountXrefRepository;

    private TransactionServices transactionServices;

    private Integer demoUserId = 8;

    private List<Transaction> transactions;
    private Account account; 

    @Test
    public void test(){
        System.out.println("test");
        Assert.assertTrue(false);
    }

    // @Before
    // public void mockSetUp() {
    //     transactionRepositoryRepo = mock(TransactionRepository.class);
    //     accountRepository =  mock(AccountRepository.class);
    //     transactionServices = new TransactionServices(transactionRepositoryRepo, accountRepository);
    //     demo = new Demo(transactionServices, 
    //         profileAccountXrefRepository,
    //         accountRepository);

    //     Transaction transaction = new Transaction(1, 2, 3, "Test", 455d, null);
    //     transactions = new ArrayList<>();
    //     transactions.add(transaction);

    //     account = new Account(10.0, "test", null,null);
    // }

    // @Test
    // public void demoTransactionResetTest(){
    //     // given

    //     when(transactionRepositoryRepo.findAllByUserId(demoUserId)).thenReturn(transactions);
    //     when(accountRepository.findAccountById(any())).thenReturn(account);

    //     // when
    //     demo.resetDemoUser(demoUserId);

    //     // then
    //     Assert.assertTrue(false);
    // }
    
    
}