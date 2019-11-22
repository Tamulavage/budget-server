package com.dmt.budgetApp.repository;

import com.dmt.budgetApp.model.FutureBudgetLineItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FutureBudgetLineItemRepository extends JpaRepository<FutureBudgetLineItem, Integer> {
	Iterable<? extends FutureBudgetLineItem> findAllByOrgId(Integer orgId);
}