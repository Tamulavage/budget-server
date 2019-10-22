package com.group3.budgetApp.controller;

import com.group3.budgetApp.model.Transaction;
import com.group3.budgetApp.services.TransactionServices;
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

import java.util.ArrayList;

import static org.mockito.BDDMockito.given;


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class TransactionControllerTest {
    
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private TransactionServices services;
    
    
//     @Test
//     public void testGetTransactions() throws Exception {
//         ArrayList<Transaction> transactions = new ArrayList<>();
//         Transaction transaction1 = new Transaction(1, 1, 2, "3", 1.0, null, null);
//         Transaction transaction2 = new Transaction(2, 1, 2, "3", 1.0, null, null);
//         transactions.add(transaction1);
//         transactions.add(transaction2);
//         given(services.getAllTransactions())
//                 .willReturn(transactions);
        
//         String expectedReturnValue = "[{" +
//                 "\"transactionId\":1," +
//                 "\"fromAccountId\":1," +
//                 "\"toAccountId\":2," +
//                 "\"memo\":\"3\"," +
//                 "\"transactionType\":{" +
//                 "\"description\":null," +
//                 "\"id\":null}," +
//                 "\"transactionDt\":null," +
//                 "\"amount\":1.0" +
//                 "},{" +
//                 "\"transactionId\":2," +
//                 "\"fromAccountId\":1," +
//                 "\"toAccountId\":2," +
//                 "\"memo\":\"3\"," +
//                 "\"transactionType\":{" +
//                 "\"description\":null," +
//                 "\"id\":null}," +
//                 "\"transactionDt\":null," +
//                 "\"amount\":1.0" +
//                 "}]";
//         this.mvc.perform(
//                 MockMvcRequestBuilders
//                         .get("/budget/transaction/"))
//                 .andExpect(MockMvcResultMatchers.status().isOk())
//                 .andExpect(MockMvcResultMatchers.content().string(expectedReturnValue))
//                 .andDo(MockMvcResultHandlers.print())
//         ;
//     }
    
    @Test
    public void testGetTransactionsNoTransactions() throws Exception {
        given(services.getAllTransactions())
                .willReturn(null);
        
        String expectedReturnValue = "";
        this.mvc.perform(
                MockMvcRequestBuilders
                        .get("/budget/transaction/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(expectedReturnValue))
        
        ;
    }
    
//     @Test
//     public void testPostTransaction() throws Exception {
//         Transaction transaction = new Transaction(1, null, null, null, null, null, null);
//         given(services.createTransaction(transaction))
//                 .willReturn(transaction);
        
//         String inputJSON = "{\"transactionId\": 2," +
//                 "\"fromAccountId\": null," +
//                 "\"toAccountId\": null," +
//                 "\"memo\": \"Hey\"," +
//                 "\"transactionType\": null," +
//                 "\"transactionDt\": null," +
//                 "\"amount\": 5}";
//         this.mvc.perform(MockMvcRequestBuilders
//                 .post("/budget/transaction/")
//                 .content(inputJSON)
//                 .accept(MediaType.APPLICATION_JSON)
//                 .contentType(MediaType.APPLICATION_JSON_UTF8)
//         )
//                 .andExpect(MockMvcResultMatchers.status().isCreated())
//                 .andDo(MockMvcResultHandlers.print());
//     }
    
}
