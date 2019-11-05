package com.group3.budgetApp.repository;

import com.group3.budgetApp.model.FutureBudgetLineItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FutureBudgetLineItemRepository extends JpaRepository<FutureBudgetLineItem, Integer> {
}