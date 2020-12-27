package com.dmt.budgetApp.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.dmt.budgetApp.exceptions.InvalidAmount;
import com.dmt.budgetApp.exceptions.InvalidData;
import com.dmt.budgetApp.model.FutureBudget;
import com.dmt.budgetApp.model.FutureBudgetLineItem;
import com.dmt.budgetApp.model.FutureBudgetOrg;
import com.dmt.budgetApp.model.RawData;
import com.dmt.budgetApp.repository.FutureBudgetLineItemRepository;
import com.dmt.budgetApp.repository.FutureBudgetOrgRepository;
import com.dmt.budgetApp.repository.FutureBudgetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
        log.info("SumPerMonth method called");
        List<FutureBudget> retVal = new ArrayList<FutureBudget>();
        FutureBudget outgoing = futureBudgetRepository.sumOutgoing(id);
        FutureBudget incoming = futureBudgetRepository.sumIncoming(id);
        retVal.add(outgoing);
        retVal.add(incoming);
        retVal.add(incomingMinusOutgoing(incoming, outgoing));

        return retVal;
    }

    private FutureBudget incomingMinusOutgoing(FutureBudget in, FutureBudget out) {
        
        log.info("incomingMinusOutgoing method called");
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

        log.info("updateBudgetLineItem method called");
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
            log.error("validDirection method error");
            throw new IllegalStateException("Invalid Direction");
        }
    }

    private FutureBudgetOrg getLineItemOrg(Integer profileId, String direction, String orgName) {

        log.info("getLineItemOrg method called, profileid = {}. direction = {}, orgName = {}", profileId, direction, orgName);
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

        log.info("saveLineItem method called, profileid = {}. month = {}, orgId = {}", profileId, month, orgId);
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
            Integer currentMonth = futureBudgetRepository.getCurrentMonthValue(profileId);
            if(currentMonth <= 12){
                // no current set up - default to Jan current
                currentMonth = 13;
            }
            futureBudgetLineItem.setMonth(currentMonth);
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
            log.error("removeBudgetLineItem method error = {}", e);
            throw new Exception("removeBudgetLineItem");
        }

        try {
            futureBudgetOrgRepository
                .delete(futureBudgetOrgRepository.getOne(futureBudgetOrgWithId.getOrgId()));
        } catch (Exception e) {
            log.error("removeBudgetLineItem method error = {}", e);
            throw new Exception("Did not delete org/check that it exists");
        }
    }

	public List<FutureBudgetLineItem> completeMonth(Integer profileId, boolean overrideValues) throws InvalidAmount {
        final Integer currentMonth = futureBudgetRepository.getCurrentMonthValue(profileId);
        if((!overrideValues) && (futureBudgetRepository.getCurrentMonthTotalSum(profileId, currentMonth)>0) )
        {
            log.error("completeMonth method error = Current month sum amount not empty! ");
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

    public RawData getCurrentMonth(Integer profileId){
        RawData retVal  = new RawData();
        retVal.setData(futureBudgetRepository.getCurrentMonthValue(profileId).toString());
        return retVal;
    }

	public RawData currentMonth(Integer profileId, Integer month) throws InvalidData{
        log.info("currentMonth method Started (ProfileId = {}, Month = {}) ", profileId, month);

        if(month < 13 || month > 24){
            log.error("currentMonth method error = Month is not valid! ");
            throw new InvalidData("Month is not valid");
        }
        if(futureBudgetRepository.getCurrentMonthValue(profileId)>12){
            log.error("currentMonth method error = user already has current month! ");
            throw new InvalidData("Month is not valid, user already has current month");
        }
        List<FutureBudgetOrg> orgs =  futureBudgetOrgRepository.findAllOrgByProfileId(profileId);
        for (FutureBudgetOrg futureBudgetOrg : orgs) {
            FutureBudgetLineItem futureBudgetLineItem = new FutureBudgetLineItem();
            futureBudgetLineItem.setFrequencyPerMonth(1);
            futureBudgetLineItem.setOrgId(futureBudgetOrg.getOrgId());
            futureBudgetLineItem.setMonth(month);
            futureBudgetLineItemRepository.save(futureBudgetLineItem);
        }
		return new RawData(month);
	}

	public FutureBudgetLineItem updateBudgetLineItem(FutureBudgetLineItem futureBudgetLineItem, Integer profileId)
            throws InvalidData {
        Boolean validOrgProfile = false;
        List<FutureBudgetOrg> passedInProfilesOrgs = futureBudgetOrgRepository.findAllOrgByProfileId(profileId);
        for(FutureBudgetOrg passedInProfilesOrg: passedInProfilesOrgs) {
            if(futureBudgetLineItem.getOrgId().equals(passedInProfilesOrg.getOrgId())){
                validOrgProfile= true;
            }  
        }
        if(!validOrgProfile){
            log.error("updateBudgetLineItem - invalid Profile/org info ");
            throw new InvalidData("invalid Profile/org info");
        }

        log.info("updateBudgetLineItem - update info = {} ", futureBudgetLineItem.toString());
        return futureBudgetLineItemRepository.save(futureBudgetLineItem);
    }
    
}