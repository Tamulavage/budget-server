package com.group3.budgetApp.services;

import com.group3.budgetApp.model.TransactionType;
import com.group3.budgetApp.repository.TransactionTypeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TransactionTypeServicesTest {

    @Autowired
    private TransactionTypeServices transactionTypeService;

    @MockBean
    private TransactionTypeRepository repo;

    @Before
    public void mockSetUp() {
        repo = mock(TransactionTypeRepository.class);
        transactionTypeService = new TransactionTypeServices(repo);
    }

    @Test
    public void testReceiveData() {
        List<TransactionType> expected = new ArrayList<>();
        expected.add(new TransactionType("test1"));
        expected.add(new TransactionType("test2"));

        when(repo.findAll()).thenReturn(expected);

        List<TransactionType> actual = transactionTypeService.getAllTransactionTypes();

        Assert.assertEquals(expected, actual);
    }

}