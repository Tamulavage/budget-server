package com.group3.budgetApp.repository;

import java.util.List;

import com.group3.budgetApp.model.FutureBudget;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FutureBudgetRepository extends JpaRepository<FutureBudget, Integer> {
    @Query(value = "select org_id, org_name, freq_per_month , direction, max(jan) as january_amount ,max(feb) as february_amount, max(march) as march_amount, " 
                        +" max(apr) as april_amount, max(may) as may_amount, max(june) as june_amount, "
                        +" max(july) as july_amount, max(aug) as august_amount, max(sept) as september_amount, " 
                        +" max(october) as october_amount, max(nov) as november_amount, max(december) as december_amount " 
                +"from ("
                    +"select fa.org_id org_id, org_name,  freq_per_month , direction, "
	                    +" CASE WHEN month=1 then amount end as jan, "
	                    +" CASE WHEN month=2 then amount end as feb, "
                        +" CASE WHEN month=3 then amount end as march, "
                        +" CASE WHEN month=4 then amount end as apr, "
                        +" CASE WHEN month=5 then amount end as may, "
                        +" CASE WHEN month=6 then amount end as june, "
                        +" CASE WHEN month=7 then amount end as july, "
                        +" CASE WHEN month=8 then amount end as aug, "
                        +" CASE WHEN month=9 then amount end as sept, "
                        +" CASE WHEN month=10 then amount end as october, "
                        +" CASE WHEN month=11 then amount end as nov, "
                        +" CASE WHEN month=12 then amount end as december "  
                    +" from budget.future_accounting fa, "
                         +" budget.future_accounting_org fao "
                    +" where fao.org_id = fa.org_id"
                    +" and profile_id=?1 "
                +") temp group by  org_id, org_name, freq_per_month , direction", nativeQuery = true)
    List<FutureBudget> findAllByProfileId(Integer profileId);    
    
}