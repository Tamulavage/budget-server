package com.group3.budgetApp.controller;

import com.group3.budgetApp.model.TransactionType;
import com.group3.budgetApp.services.TransactionTypeServices;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class TransactionTypeControllerTest {

    @MockBean
    private TransactionTypeServices services;

    private TransactionTypeController controller;

    @Before
    public void setup(){
        this.controller = new TransactionTypeController(services);
    }

    @Test
    public void getTransactionTypeTest() {
        List<TransactionType> expected = new ArrayList<>();
        expected.add(new TransactionType("test1"));
        expected.add(new TransactionType("test2"));
        HttpStatus httpExpected = HttpStatus.OK;
        BDDMockito
                .given(services.getAllTransactionTypes())
                .willReturn(expected);
        //When
        ResponseEntity<List<TransactionType>> responseEntity = controller.getTransactionTypes();
        HttpStatus httpActual = responseEntity.getStatusCode();
        List<TransactionType> actual = responseEntity.getBody();
        //Then
        Assert.assertEquals(httpExpected, httpActual);
        Assert.assertEquals(expected, actual);

    }

}