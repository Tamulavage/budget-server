package com.group3.budgetApp.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.group3.budgetApp.model.FutureBudget;
import com.group3.budgetApp.model.FutureBudgetLineItem;
import com.group3.budgetApp.model.FutureBudgetOrg;
import com.group3.budgetApp.repository.FutureBudgetLineItemRepository;
import com.group3.budgetApp.repository.FutureBudgetOrgRepository;
import com.group3.budgetApp.repository.FutureBudgetRepository;

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

        return difference;
    }

    public FutureBudget createNewBudgetLineItem(FutureBudget futureBudget, Integer profileId) {
        FutureBudgetOrg futureBudgetOrg = new FutureBudgetOrg();
        futureBudgetOrg.setOrgName(futureBudget.getOrgName());
        futureBudgetOrg.setDirection(futureBudget.getDirection());
        futureBudgetOrg.setProfileId(profileId);

        futureBudgetOrgRepository.save(futureBudgetOrg);
        
        for(int month=1; month <=12; month++){
            saveLineItem(futureBudgetOrg.getOrgId(), futureBudget, month);
        }

        futureBudget.setOrgId(futureBudgetOrg.getOrgId());

        return futureBudget;
    }

    private void saveLineItem (Integer orgId, FutureBudget futureBudget, Integer month) {
        FutureBudgetLineItem futureBudgetLineItem = new FutureBudgetLineItem();

        futureBudgetLineItem.setOrgId(orgId);
        futureBudgetLineItem.setFrequencyPerMonth(futureBudget.getFrequencyPerMonth());
        futureBudgetLineItem.setMonth(month);

        switch(month){
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
                futureBudgetLineItem.setAmount(new BigDecimal(0.0));
                break;
        }

        futureBudgetLineItemRepository.save(futureBudgetLineItem);
    }

}