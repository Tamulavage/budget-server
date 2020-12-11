package com.dmt.budgetApp.repository;

import java.util.List;

import com.dmt.budgetApp.model.FutureBudgetLineItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FutureBudgetLineItemRepository extends JpaRepository<FutureBudgetLineItem, Integer> {
	Iterable<? extends FutureBudgetLineItem> findAllByOrgId(Integer orgId);
	FutureBudgetLineItem findByOrgIdAndMonth(Integer orgId, Integer month);

	@Query(value = "SELECT fa.org_id, fa.month AS month, fa.amount AS amount, fa.freq_per_month "        
	+ " FROM future_accounting_org fao, "
	+ "      future_accounting fa "
	+ " WHERE fao.org_id = fa.org_id "
	+ " AND fao.profile_id = ?1 " 
	+ " AND fa.month = ?2", nativeQuery = true)
	List<FutureBudgetLineItem> getCurrentMonthFullDataPerProfile(Integer profileId, Integer month); 

	@Modifying
	@Query(value = "DELETE FROM future_accounting  WHERE org_id= ?1", nativeQuery = true)
	void deleteByOrgID(Integer orgId);
}