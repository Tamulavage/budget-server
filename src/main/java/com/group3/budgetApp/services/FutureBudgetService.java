package com.group3.budgetApp.services;

import java.util.ArrayList;
import java.util.List;

import com.group3.budgetApp.model.FutureBudget;
import com.group3.budgetApp.model.FutureBudgetOrg;
import com.group3.budgetApp.repository.FutureBudgetOrgRepository;
import com.group3.budgetApp.repository.FutureBudgetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FutureBudgetService {

    private FutureBudgetOrgRepository futureBudgetOrgRepository;
    private FutureBudgetRepository futureBudgetRepository;

    @Autowired
    public FutureBudgetService(FutureBudgetOrgRepository futureBudgetOrgRepository,
            FutureBudgetRepository futureBudgetRepository) {
        this.futureBudgetOrgRepository = futureBudgetOrgRepository;
        this.futureBudgetRepository = futureBudgetRepository;
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
        difference.setJanuaryAmount(in.getJanuaryAmount().subtract(out.getJanuaryAmount()) );
        difference.setFebruaryAmount(in.getFebruaryAmount().subtract(out.getFebruaryAmount()) );
        difference.setMarchAmount(in.getMarchAmount().subtract(out.getMarchAmount()) );
        difference.setAprilAmount(in.getAprilAmount().subtract(out.getAprilAmount()) );
        difference.setMayAmount(in.getMayAmount().subtract(out.getMayAmount()) );
        difference.setJuneAmount(in.getJuneAmount().subtract(out.getJuneAmount()) );
        difference.setJulyAmount(in.getJulyAmount().subtract(out.getJulyAmount()) );
        difference.setAugustAmount(in.getAugustAmount().subtract(out.getAugustAmount()) );
        difference.setSeptemberAmount(in.getSeptemberAmount().subtract(out.getSeptemberAmount()) );
        difference.setOctoberAmount(in.getOctoberAmount().subtract(out.getOctoberAmount()) );
        difference.setNovemberAmount(in.getNovemberAmount().subtract(out.getNovemberAmount()) );
        difference.setDecemberAmount(in.getDecemberAmount().subtract(out.getDecemberAmount()) );                        

        return difference;
    }
}