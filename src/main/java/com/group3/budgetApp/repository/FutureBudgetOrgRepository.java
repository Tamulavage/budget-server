package com.group3.budgetApp.repository;

import java.util.List;

import com.group3.budgetApp.model.FutureBudgetOrg;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FutureBudgetOrgRepository extends JpaRepository<FutureBudgetOrg, Integer> {
    List<FutureBudgetOrg> findAllOrgByProfileId(Integer profileId);
    
}