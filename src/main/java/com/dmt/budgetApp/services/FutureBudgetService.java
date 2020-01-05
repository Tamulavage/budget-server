package com.dmt.budgetApp.services;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FutureBudgetService {

    private FutureBudgetOrgRepository futureBudgetOrgRepository;
    private FutureBudgetRepository futureBudgetRepository;
    private FutureBudgetLineItemRepository futureBudgetLineItemRepository;

    @Autowired
    public FutureBudgetService(FutureBudgetOrgRepository futureBudgetOrgRepository,
            FutureBudgetRepository futureBudgetRepository,
            FutureBudgetLineItemRepository futureBudgetLineItemRepository) {
        this.futureBudgetOrgRepository = futureBudgetOrgRepository;
        this.futureBudgetRepository = futureBudgetRepository;
        this.futureBudgetLineItemRepository = futureBudgetLineItemRepository;
    }

    public List<FutureBudgetOrg> findAllOrgByUserId(Integer id) {
        return futureBudgetOrgRepository.findAllOrgByProfileId(id);
    }

    public List<FutureBudget> findAllByUserId(Integer id) {
        return futureBudgetRepository.findAllByProfileId(id);
    }

    public List<FutureBudget> findAllOutputByUserId(Integer id) {
        return futureBudgetRepository.findAllOutputByProfileId(id);
    }

    public List<FutureBudget> findAllInputByUserId(Integer id) {
        return futureBudgetRepository.findAllInputByProfileId(id);
    }

    public List<FutureBudget> sumPerMonth(Integer id) {
        List<FutureBudget> retVal = new ArrayList<FutureBudget>();
        FutureBudget outgoing = futureBudgetRepository.sumOutgoing(id);
        FutureBudget incoming = futureBudgetRepository.sumIncoming(id);
        retVal.add(outgoing);
        retVal.add(incoming);
        retVal.add(incomingMinusOutgoing(incoming, outgoing));

        return retVal;
    }

    private FutureBudget incomingMinusOutgoing(FutureBudget in, FutureBudget out) {
        FutureBudget difference = new FutureBudget();
        difference.setDirection("D");
        difference.setJanuaryAmount(in.getJanuaryAmount().subtract(out.getJanuaryAmount()));
        difference.setFebruaryAmount(in.getFebruaryAmount().subtract(out.getFebruaryAmount()));
        difference.setMarchAmount(in.getMarchAmount().subtract(out.getMarchAmount()));
        difference.setAprilAmount(in.getAprilAmount().subtract(out.getAprilAmount()));
        difference.setMayAmount(in.getMayAmount().subtract(out.getMayAmount()));
        difference.setJuneAmount(in.getJuneAmount().subtract(out.getJuneAmount()));
        difference.setJulyAmount(in.getJulyAmount().subtract(out.getJulyAmount()));
        difference.setAugustAmount(in.getAugustAmount().subtract(out.getAugustAmount()));
        difference.setSeptemberAmount(in.getSeptemberAmount().subtract(out.getSeptemberAmount()));
        difference.setOctoberAmount(in.getOctoberAmount().subtract(out.getOctoberAmount()));
        difference.setNovemberAmount(in.getNovemberAmount().subtract(out.getNovemberAmount()));
        difference.setDecemberAmount(in.getDecemberAmount().subtract(out.getDecemberAmount()));
        difference.setCurrentAmount(in.getCurrentAmount().subtract(out.getCurrentAmount()));

        return difference;
    }

    public FutureBudget updateBudgetLineItem(FutureBudget futureBudget, Integer profileId) {

        FutureBudgetOrg futureBudgetOrg = getLineItemOrg(profileId, futureBudget.getDirection(),
                futureBudget.getOrgName());

        futureBudgetOrgRepository.save(futureBudgetOrg);

        for (int month = 1; month <= 13; month++) {
            saveLineItem(futureBudgetOrg.getOrgId(), futureBudget, month, profileId);
        }

        futureBudget.setOrgId(futureBudgetOrg.getOrgId());

        return futureBudget;
    }

    private void validDirection(String direction) {
        if (!("I".equals(direction) || "O".equals(direction))) {
            throw new IllegalStateException("Invalid Direction");
        }
    }

    private FutureBudgetOrg getLineItemOrg(Integer profileId, String direction, String orgName) {

        validDirection(direction);

        List<FutureBudgetOrg> futureBudgetOrg = futureBudgetOrgRepository.findAllOrgByProfileId(profileId);
        FutureBudgetOrg futureBudgetOrgRetVal = new FutureBudgetOrg();
        futureBudgetOrgRetVal.setOrgName(orgName);
        futureBudgetOrgRetVal.setDirection(direction);
        futureBudgetOrgRetVal.setProfileId(profileId);

        for (FutureBudgetOrg org : futureBudgetOrg) {
            if (org.getOrgName().equals(orgName) && org.getDirection().equals(direction)) {
                futureBudgetOrgRetVal = org;
            }
        }

        return futureBudgetOrgRetVal;
    }

    private void saveLineItem(Integer orgId, FutureBudget futureBudget, Integer month,  Integer profileId) {
        FutureBudgetLineItem futureBudgetLineItem = new FutureBudgetLineItem();

        futureBudgetLineItem.setOrgId(orgId);
        futureBudgetLineItem.setFrequencyPerMonth(futureBudget.getFrequencyPerMonth());
        futureBudgetLineItem.setMonth(month);

        switch (month) {        
        case 1:
            futureBudgetLineItem.setAmount(futureBudget.getJanuaryAmount());
            break;
        case 2:
            futureBudgetLineItem.setAmount(futureBudget.getFebruaryAmount());
            break;
        case 3:
            futureBudgetLineItem.setAmount(futureBudget.getMarchAmount());
            break;
        case 4:
            futureBudgetLineItem.setAmount(futureBudget.getAprilAmount());
            break;
        case 5:
            futureBudgetLineItem.setAmount(futureBudget.getMayAmount());
            break;
        case 6:
            futureBudgetLineItem.setAmount(futureBudget.getJuneAmount());
            break;
        case 7:
            futureBudgetLineItem.setAmount(futureBudget.getJulyAmount());
            break;
        case 8:
            futureBudgetLineItem.setAmount(futureBudget.getAugustAmount());
            break;
        case 9:
            futureBudgetLineItem.setAmount(futureBudget.getSeptemberAmount());
            break;
        case 10:
            futureBudgetLineItem.setAmount(futureBudget.getOctoberAmount());
            break;
        case 11:
            futureBudgetLineItem.setAmount(futureBudget.getNovemberAmount());
            break;
        case 12:
            futureBudgetLineItem.setAmount(futureBudget.getDecemberAmount());
            break;
        default:
            futureBudgetLineItem.setMonth(futureBudgetRepository.getCurrentMonthValue(profileId));
            futureBudgetLineItem.setAmount(futureBudget.getCurrentAmount());
            break;
        }

        futureBudgetLineItemRepository.save(futureBudgetLineItem);
    }

    public void removeBudgetLineItem(FutureBudgetOrg futureBudgetOrg, Integer profileId) throws Exception {

        FutureBudgetOrg futureBudgetOrgWithId = getLineItemOrg(profileId, futureBudgetOrg.getDirection(),
                futureBudgetOrg.getOrgName());

        try {
            futureBudgetLineItemRepository
                    .deleteAll(futureBudgetLineItemRepository.findAllByOrgId(futureBudgetOrgWithId.getOrgId()));
        } catch (Exception e) {
            System.err.println(e.toString());
        }

        try {
            futureBudgetOrgRepository
                .delete(futureBudgetOrgRepository.getOne(futureBudgetOrgWithId.getOrgId()));
        } catch (Exception e) {
            System.err.println(e.toString());
            throw new Exception("Did not delete org/check that it exists");
        }
    }

	public List<FutureBudgetLineItem> completeMonth(Integer profileId, boolean overrideValues) throws InvalidAmount {
        Integer currentMonth = futureBudgetRepository.getCurrentMonthValue(profileId);
        if((!overrideValues) && (futureBudgetRepository.getCurrentMonthTotalSum(profileId, currentMonth)>0) )
        {
             throw new InvalidAmount("Current month sum amount not empty");
        }
    
        Integer nextMonth = getNextMonth(currentMonth);
        Integer futureCurrentMonth = currentMonth % 12 + 13;

        List<FutureBudgetLineItem> currentMonthFullData = futureBudgetLineItemRepository.getCurrentMonthFullDataPerProfile(profileId, currentMonth);
        List<FutureBudgetLineItem> retVal = new ArrayList<FutureBudgetLineItem>();

        for (FutureBudgetLineItem futureBudgetLineItem : currentMonthFullData) {
            
            FutureBudgetLineItem futureBudgetLineItemNextMonth = futureBudgetLineItemRepository.findByOrgIdAndMonth(futureBudgetLineItem.getOrgId(), nextMonth);
            FutureBudgetLineItem futureBudgetLineItemNew = resetCurrentDataPerOrg(futureBudgetLineItem, futureBudgetLineItemNextMonth.getAmount(), futureCurrentMonth );
            
            // save then delete as it is part of PK
            futureBudgetLineItemRepository.save(futureBudgetLineItemNew);
            futureBudgetLineItemRepository.delete(futureBudgetLineItem);
            retVal.add(futureBudgetLineItemNew);
        }

        return retVal;
    }
    
    private FutureBudgetLineItem resetCurrentDataPerOrg(FutureBudgetLineItem futureBudgetLineItem, BigDecimal amount, Integer newMonth){
        return new FutureBudgetLineItem(futureBudgetLineItem.getOrgId(), newMonth, amount, futureBudgetLineItem.getFrequencyPerMonth());
    }

    private Integer getNextMonth(Integer currentMonth){
        Integer nextMonth = currentMonth % 12+1;
        if(nextMonth == 0) {
            nextMonth = 12;
        }    
        return nextMonth;
    }

}