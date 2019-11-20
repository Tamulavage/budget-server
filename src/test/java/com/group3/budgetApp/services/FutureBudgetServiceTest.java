package com.group3.budgetApp.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.group3.budgetApp.model.FutureBudget;
import com.group3.budgetApp.model.FutureBudgetOrg;
import com.group3.budgetApp.repository.FutureBudgetLineItemRepository;
import com.group3.budgetApp.repository.FutureBudgetOrgRepository;
import com.group3.budgetApp.repository.FutureBudgetRepository;

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
        this.futureBudgetService = new FutureBudgetService(futureBudgetOrgRepository,
                futureBudgetRepository,
                futureBudgetLineItemRepository);
    }

    private FutureBudget setupOutgoingBudget() {

        FutureBudget futureBudget = new FutureBudget();
        futureBudget.setOrgId(1);
        futureBudget.setDirection("O");
        futureBudget.setJanuaryAmount(new BigDecimal(1234));
        futureBudget.setMarchAmount(new BigDecimal(1234));

        return futureBudget;
    }

    private FutureBudget setupIncomingBudget() {

        FutureBudget futureBudget = new FutureBudget();
        futureBudget.setOrgId(2);
        futureBudget.setDirection("I");
        futureBudget.setJanuaryAmount(new BigDecimal(2234));
        futureBudget.setFebruaryAmount(new BigDecimal(1234));

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

        List<FutureBudgetOrg> futureBudgetOrgList = new ArrayList<>();

        when(futureBudgetOrgRepository.findAllOrgByProfileId(this.profileId)).thenReturn(futureBudgetOrgList);
        when(futureBudgetOrgRepository.save(any())).thenReturn(null);
        when(futureBudgetLineItemRepository.save(any())).thenReturn(null);

        // When
        FutureBudget actual = futureBudgetService.updateBudgetLineItem(expected, this.profileId);

        // then
        Assert.assertNull(actual.getOrgId());
    }
}
