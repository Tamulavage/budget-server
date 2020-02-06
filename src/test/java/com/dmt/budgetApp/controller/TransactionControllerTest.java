package com.dmt.budgetApp.controller;

import com.dmt.budgetApp.model.Transaction;
import com.dmt.budgetApp.services.TransactionServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class TransactionControllerTest {
    
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private TransactionServices services;
    
    @Test
    public void testPostTransaction() throws Exception {
        Transaction transaction = new Transaction(1, null,  null, null, null, null);
        given(services.createTransaction(transaction))
                .willReturn(transaction);
        
        String inputJSON = "{\"transactionId\": 2," +
                "\"fromAccountId\": null," +
                "\"toAccountId\": null," +
                "\"memo\": \"Hey\"," +
                "\"transactionType\": null," +
                "\"transactionDt\": null," +
                "\"amount\": 5}";
        this.mvc.perform(MockMvcRequestBuilders
                .post("/budget/transaction/")
                .content(inputJSON)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }
    
}
