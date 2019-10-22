package com.group3.budgetApp.services;

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
}