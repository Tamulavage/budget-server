package com.dmt.budgetApp.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.dmt.budgetApp.model.FutureBudget;
import com.dmt.budgetApp.model.FutureBudgetOrg;
import com.dmt.budgetApp.services.FutureBudgetService;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class FutureBudgetControllerTest {

    @MockBean
    private FutureBudgetService futureBudgetService;

    private FutureBudgetController controller;

    private List<FutureBudget> dummyFutureBudget;

    private List<FutureBudgetOrg> dummyFutureBudgetOrg;

    private HttpHeaders header = new HttpHeaders();

    @Before
    public void setup() {
        this.controller = new FutureBudgetController(futureBudgetService);

        setupLineItems();
        setupOrgs();
    }

    private void setupLineItems() {

        this.dummyFutureBudget = new ArrayList<>();
        FutureBudget futureBudget = new FutureBudget();
        futureBudget.setOrgId(1);
        futureBudget.setDirection("I");
        futureBudget.setJanuaryAmount(new BigDecimal(1234));
        futureBudget.setFrequencyPerMonth(2);

        this.dummyFutureBudget.add(futureBudget);
    }

    private void setupOrgs() {
        this.dummyFutureBudgetOrg = new ArrayList<>();
        FutureBudgetOrg futureBudgetOrg = new FutureBudgetOrg();
        futureBudgetOrg.setDirection("I");
        futureBudgetOrg.setOrgId(1);
        futureBudgetOrg.setOrgName("Test");
        futureBudgetOrg.setProfileId(123);
        this.dummyFutureBudgetOrg.add(futureBudgetOrg);
    }

    @Test
    public void getAllOrgByUserIdStatusOKTest() {
        // Given
        int testUserId = 1;
        HttpStatus expected = HttpStatus.OK;
        BDDMockito.given(futureBudgetService.findAllOrgByUserId(testUserId)).willReturn(dummyFutureBudgetOrg);

        // WHen
        ResponseEntity<List<FutureBudgetOrg>> entity = controller.getAllOrgByUserId(testUserId, header);
        HttpStatus actual = entity.getStatusCode();

        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getAllOrgByUserIdValuesTest() {
        // Given
        int testUserId = 123;
        BDDMockito.given(futureBudgetService.findAllOrgByUserId(testUserId)).willReturn(dummyFutureBudgetOrg);

        // WHen
        ResponseEntity<List<FutureBudgetOrg>> entity = controller.getAllOrgByUserId(testUserId, header);
        List<FutureBudgetOrg> actual = entity.getBody();

        // Then
        Assert.assertTrue(dummyFutureBudgetOrg.get(0).equals(actual.get(0)));
    }

    @Test
    public void getOutputByUserIdStatusOKTest() {
        // Given
        int testUserId = 1;
        HttpStatus expected = HttpStatus.OK;
        BDDMockito.given(futureBudgetService.findAllOutputByUserId(testUserId)).willReturn(dummyFutureBudget);

        // WHen
        ResponseEntity<List<FutureBudget>> entity = controller.getOutputByUserId(testUserId, header);
        HttpStatus actual = entity.getStatusCode();

        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getOutputByUserIdValuesTest() {
        // Given
        int testUserId = 1;
        BDDMockito.given(futureBudgetService.findAllOutputByUserId(testUserId)).willReturn(dummyFutureBudget);

        // WHen
        ResponseEntity<List<FutureBudget>> entity = controller.getOutputByUserId(testUserId, header);
        List<FutureBudget> actual = entity.getBody();

        // Then
        Assert.assertTrue(dummyFutureBudget.get(0).equals(actual.get(0)));
    }

    @Test
    public void getInputByUserIdStatusOKTest() {
        // Given
        int testUserId = 1;
        HttpStatus expected = HttpStatus.OK;
        BDDMockito.given(futureBudgetService.findAllInputByUserId(testUserId)).willReturn(dummyFutureBudget);

        // WHen
        ResponseEntity<List<FutureBudget>> entity = controller.getInputByUserId(testUserId, header);
        HttpStatus actual = entity.getStatusCode();

        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getInputByUserIdValuesTest() {
        // Given
        int testUserId = 1;
        BDDMockito.given(futureBudgetService.findAllInputByUserId(testUserId)).willReturn(dummyFutureBudget);

        // WHen
        ResponseEntity<List<FutureBudget>> entity = controller.getInputByUserId(testUserId, header);
        List<FutureBudget> actual = entity.getBody();

        // Then
        Assert.assertTrue(dummyFutureBudget.get(0).equals(actual.get(0)));
    }

    @Test
    public void getSumsbyUserIdStatusOKTest() {
        // Given
        int testUserId = 1;
        HttpStatus expected = HttpStatus.OK;
        BDDMockito.given(futureBudgetService.sumPerMonth(testUserId)).willReturn(dummyFutureBudget);

        // WHen
        ResponseEntity<List<FutureBudget>> entity = controller.getSumsbyUserId(testUserId, header);
        HttpStatus actual = entity.getStatusCode();

        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getSumsbyUserIdValuesTest() {
        // Given
        int testUserId = 1;
        BDDMockito.given(futureBudgetService.sumPerMonth(testUserId)).willReturn(dummyFutureBudget);

        // WHen
        ResponseEntity<List<FutureBudget>> entity = controller.getSumsbyUserId(testUserId, header);
        List<FutureBudget> actual = entity.getBody();

        // Then
        Assert.assertTrue(dummyFutureBudget.get(0).equals(actual.get(0)));
    }

    @Test
    public void createNewBudgetLineItemStatusOKTest() {
        // Given
        int testUserId = 1;
        HttpStatus expected = HttpStatus.ACCEPTED;
        BDDMockito.given(futureBudgetService.updateBudgetLineItem(dummyFutureBudget.get(0), testUserId)).willReturn(dummyFutureBudget.get(0));

        // WHen
        ResponseEntity<FutureBudget> entity = controller.updateBudgetLineItem(dummyFutureBudget.get(0), testUserId, header);
        HttpStatus actual = entity.getStatusCode();

        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void createNewBudgetLineItemValuesTest() {
        // Given
        int testUserId = 1;
        BDDMockito.given(futureBudgetService.updateBudgetLineItem(dummyFutureBudget.get(0), testUserId)).willReturn(dummyFutureBudget.get(0));

        // WHen
        ResponseEntity<FutureBudget> entity = controller.updateBudgetLineItem(dummyFutureBudget.get(0), testUserId, header);
        FutureBudget actual = entity.getBody();

        // Then
        Assert.assertEquals(dummyFutureBudget.get(0), actual);
    }

}