package com.dmt.budgetApp.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.dmt.budgetApp.exceptions.InvalidAmount;
import com.dmt.budgetApp.model.FutureBudget;
import com.dmt.budgetApp.model.FutureBudgetLineItem;
import com.dmt.budgetApp.model.FutureBudgetOrg;
import com.dmt.budgetApp.repository.FutureBudgetLineItemRepository;
import com.dmt.budgetApp.repository.FutureBudgetOrgRepository;
import com.dmt.budgetApp.repository.FutureBudgetRepository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class FutureBudgetServiceTest {

    @Mock
    private FutureBudgetOrgRepository futureBudgetOrgRepository;
    @Mock
    private FutureBudgetRepository futureBudgetRepository;
    @Mock
    private FutureBudgetLineItemRepository futureBudgetLineItemRepository;

    private FutureBudgetService futureBudgetService;

    private int profileId = 1;

    @Before
    public void setup() {
        this.futureBudgetService = new FutureBudgetService(futureBudgetOrgRepository, futureBudgetRepository,
                futureBudgetLineItemRepository);
    }

    private FutureBudget setupOutgoingBudget() {

        FutureBudget futureBudget = new FutureBudget();
        futureBudget.setOrgId(1);
        futureBudget.setDirection("O");
        futureBudget.setJanuaryAmount(new BigDecimal(1234));
        futureBudget.setMarchAmount(new BigDecimal(1234));
        futureBudget.setCurrentAmount(new BigDecimal(0));

        return futureBudget;
    }

    private FutureBudget setupIncomingBudget() {

        FutureBudget futureBudget = new FutureBudget();
        futureBudget.setOrgId(2);
        futureBudget.setDirection("I");
        futureBudget.setJanuaryAmount(new BigDecimal(2234));
        futureBudget.setFebruaryAmount(new BigDecimal(1234));
        futureBudget.setCurrentAmount(new BigDecimal(0));

        return futureBudget;
    }

    private FutureBudget setupDifferenceBudget() {

        FutureBudget futureBudget = new FutureBudget();
        futureBudget.setDirection("D");
        futureBudget.setJanuaryAmount(new BigDecimal(1000));
        futureBudget.setFebruaryAmount(new BigDecimal(1234));
        futureBudget.setMarchAmount(new BigDecimal(-1234));

        futureBudget.setAprilAmount(new BigDecimal(0));
        futureBudget.setMayAmount(new BigDecimal(0));
        futureBudget.setJuneAmount(new BigDecimal(0));
        futureBudget.setJulyAmount(new BigDecimal(0));
        futureBudget.setAugustAmount(new BigDecimal(0));
        futureBudget.setSeptemberAmount(new BigDecimal(0));
        futureBudget.setOctoberAmount(new BigDecimal(0));
        futureBudget.setNovemberAmount(new BigDecimal(0));
        futureBudget.setDecemberAmount(new BigDecimal(0));
        futureBudget.setCurrentAmount(new BigDecimal(0));

        return futureBudget;
    }

    @Test
    public void sumPerMonthTest() {
        // Given
        FutureBudget outgoingExpected = setupOutgoingBudget();
        FutureBudget incomingExpected = setupIncomingBudget();
        FutureBudget differenceExpected = setupDifferenceBudget();

        when(futureBudgetRepository.sumOutgoing(this.profileId)).thenReturn(outgoingExpected);
        when(futureBudgetRepository.sumIncoming(this.profileId)).thenReturn(incomingExpected);

        // when
        List<FutureBudget> actual = futureBudgetService.sumPerMonth(this.profileId);

        // then
        Assert.assertTrue(actual.get(0).equals(outgoingExpected));
        Assert.assertTrue(actual.get(1).equals(incomingExpected));
        Assert.assertTrue(actual.get(2).equals(differenceExpected));
    }

    @Test
    public void updateBudgetLineItemOrgExistsTest() {

        // Given
        String orgName = "Test";
        String direction = "I";
        FutureBudget expected = new FutureBudget();
        expected.setOrgName(orgName);
        expected.setDirection(direction);
        expected.setJanuaryAmount(new BigDecimal(123));

        List<FutureBudgetOrg> futureBudgetOrgList = new ArrayList<>();
        FutureBudgetOrg futureBudgetOrg = new FutureBudgetOrg();
        futureBudgetOrg.setOrgId(1010);
        futureBudgetOrg.setOrgName(orgName);
        futureBudgetOrg.setDirection(direction);
        futureBudgetOrg.setProfileId(this.profileId);
        futureBudgetOrgList.add(futureBudgetOrg);

        when(futureBudgetOrgRepository.findAllOrgByProfileId(this.profileId)).thenReturn(futureBudgetOrgList);
        when(futureBudgetOrgRepository.save(any())).thenReturn(null);
        when(futureBudgetLineItemRepository.save(any())).thenReturn(null);

        // When
        FutureBudget actual = futureBudgetService.updateBudgetLineItem(expected, this.profileId);

        // then
        Assert.assertEquals(futureBudgetOrg.getOrgId(), actual.getOrgId());
    }

    @Test
    public void updateBudgetLineItemNewOrgTest() {

        // Given
        FutureBudget expected = new FutureBudget();
        expected.setDirection("I");

        List<FutureBudgetOrg> futureBudgetOrgList = new ArrayList<>();

        when(futureBudgetOrgRepository.findAllOrgByProfileId(this.profileId)).thenReturn(futureBudgetOrgList);
        when(futureBudgetOrgRepository.save(any())).thenReturn(null);
        when(futureBudgetLineItemRepository.save(any())).thenReturn(null);

        // When
        FutureBudget actual = futureBudgetService.updateBudgetLineItem(expected, this.profileId);

        // then
        Assert.assertNull(actual.getOrgId());
    }

    @Test(expected = InvalidAmount.class)
    public void completeMonthExceptionTest() throws InvalidAmount {
        // Given
        FutureBudgetLineItem futureBudgetLineItem1 = new FutureBudgetLineItem(1, 13, new BigDecimal("50.12"), 1);
        FutureBudgetLineItem futureBudgetLineItem4 = new FutureBudgetLineItem(1, 2, new BigDecimal("100"), 1);    
        List<FutureBudgetLineItem> expected = new ArrayList<FutureBudgetLineItem>();
        expected.add(futureBudgetLineItem1);

        when(futureBudgetRepository.getCurrentMonthValue(any())).thenReturn(13);
        when(futureBudgetRepository.getCurrentMonthTotalSum(any(), any())).thenReturn(1);
        when(futureBudgetLineItemRepository.findByOrgIdAndMonth(1, 2)).thenReturn(futureBudgetLineItem4);         
        when(futureBudgetLineItemRepository.getCurrentMonthFullDataPerProfile(any(), any())).thenReturn(expected);
        when(futureBudgetLineItemRepository.save(any())).thenReturn(futureBudgetLineItem1);

        // When
        futureBudgetService.completeMonth(profileId, false);

        // Then
        // Exception Thrown
    }

    @Test
    public void completeMonthTest() throws InvalidAmount {
        // Given
        FutureBudgetLineItem dummyReturn1 = new FutureBudgetLineItem(1, 13, new BigDecimal("50.12"), 1);
        FutureBudgetLineItem dummyReturn2 = new FutureBudgetLineItem(2, 13, new BigDecimal("5.12"), 1);
        FutureBudgetLineItem dummyReturn3 = new FutureBudgetLineItem(3, 13, new BigDecimal("150.02"), 1);
        FutureBudgetLineItem futureBudgetLineItem4 = new FutureBudgetLineItem(1, 2, new BigDecimal("100"), 1);
        FutureBudgetLineItem futureBudgetLineItem5 = new FutureBudgetLineItem(2, 2, new BigDecimal("101.5"), 1);
        FutureBudgetLineItem futureBudgetLineItem6 = new FutureBudgetLineItem(3, 2, new BigDecimal("75"), 1);   
        FutureBudgetLineItem expected1 = new FutureBudgetLineItem(1, 14, new BigDecimal("100"), 1);
        FutureBudgetLineItem expected2 = new FutureBudgetLineItem(2, 14, new BigDecimal("101.5"), 1);
        FutureBudgetLineItem expected3 = new FutureBudgetLineItem(3, 14, new BigDecimal("75"), 1);               
        List<FutureBudgetLineItem> dummyReturnData = new ArrayList<FutureBudgetLineItem>();
        dummyReturnData.add(dummyReturn1);
        dummyReturnData.add(dummyReturn2);
        dummyReturnData.add(dummyReturn3);        
        List<FutureBudgetLineItem> expected = new ArrayList<FutureBudgetLineItem>();
        expected.add(expected1);
        expected.add(expected2);
        expected.add(expected3);

        when(futureBudgetRepository.getCurrentMonthValue(any())).thenReturn(13);
        when(futureBudgetRepository.getCurrentMonthTotalSum(any(), any())).thenReturn(1);
        when(futureBudgetLineItemRepository.findByOrgIdAndMonth(1, 2)).thenReturn(futureBudgetLineItem4);
        when(futureBudgetLineItemRepository.findByOrgIdAndMonth(2, 2)).thenReturn(futureBudgetLineItem5);
        when(futureBudgetLineItemRepository.findByOrgIdAndMonth(3, 2)).thenReturn(futureBudgetLineItem6);                
        when(futureBudgetLineItemRepository.getCurrentMonthFullDataPerProfile(any(), any())).thenReturn(dummyReturnData);
        when(futureBudgetLineItemRepository.save(expected1)).thenReturn(expected1);
        when(futureBudgetLineItemRepository.save(expected2)).thenReturn(expected2);
        when(futureBudgetLineItemRepository.save(expected3)).thenReturn(expected3);                

        // When
        List<FutureBudgetLineItem> actual = futureBudgetService.completeMonth(profileId, true);

        // Then
        Assert.assertEquals(expected, actual);
    }

}

