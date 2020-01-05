package com.dmt.budgetApp.repository;

import java.util.List;

import com.dmt.budgetApp.model.FutureBudget;
import com.dmt.budgetApp.model.FutureBudgetLineItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FutureBudgetRepository extends JpaRepository<FutureBudget, Integer> {
        @Query(value = "SELECT org_id, org_name, freq_per_month , direction, MAX(cur) as current_amount, "
                        + " MAX(jan) as january_amount, MAX(feb) as february_amount, MAX(march) as march_amount, "
                        + " MAX(apr) as april_amount, MAX(may) as may_amount, MAX(june) as june_amount, "
                        + " MAX(july) as july_amount, MAX(aug) as august_amount, MAX(sept) as september_amount, "
                        + " MAX(october) as october_amount, MAX(nov) as november_amount, MAX(december) as december_amount "
                        + "FROM ("
                        + "      SELECT fa.org_id org_id, org_name,  freq_per_month , direction, "
                        + "              CASE WHEN month=1 then amount end as jan, " 
                        + "              CASE WHEN month=2 then amount end as feb, "
                        + "              CASE WHEN month=3 then amount end as march, "
                        + "              CASE WHEN month=4 then amount end as apr, " 
                        + "              CASE WHEN month=5 then amount end as may, "
                        + "              CASE WHEN month=6 then amount end as june, "
                        + "              CASE WHEN month=7 then amount end as july, " 
                        + "              CASE WHEN month=8 then amount end as aug, "
                        + "              CASE WHEN month=9 then amount end as sept, "
                        + "              CASE WHEN month=10 then amount end as october, "
                        + "              CASE WHEN month=11 then amount end as nov, "
                        + "              CASE WHEN month=12 then amount end as december, "
                        + "              CASE WHEN month>12  then amount end as cur "
                        + "      FROM future_accounting fa, "
                        + "           future_accounting_org fao " 
                        + "      WHERE fao.org_id = fa.org_id"
                        + "       AND profile_id=?1 "
                        + "     ) temp GROUP BY org_id, org_name, freq_per_month , direction", nativeQuery = true)
        List<FutureBudget> findAllByProfileId(Integer profileId);

        @Query(value = "SELECT org_id, org_name, freq_per_month , direction, MAX(cur) as current_amount, "
                        + " MAX(jan) as january_amount ,MAX(feb) as february_amount, MAX(march) as march_amount, "
                        + " MAX(apr) as april_amount, MAX(may) as may_amount, MAX(june) as june_amount, "
                        + " MAX(july) as july_amount, MAX(aug) as august_amount, MAX(sept) as september_amount, "
                        + " MAX(october) as october_amount, MAX(nov) as november_amount, MAX(december) as december_amount "
                        + " FROM ( " 
                        + "     SELECT fa.org_id org_id, org_name,  freq_per_month , direction, "
                        + "              CASE WHEN month=1 THEN amount * freq_per_month end as jan, "
                        + "              CASE WHEN month=2 THEN amount * freq_per_month end as feb, "
                        + "              CASE WHEN month=3 THEN amount * freq_per_month end as march, "
                        + "              CASE WHEN month=4 THEN amount * freq_per_month end as apr, "
                        + "              CASE WHEN month=5 THEN amount * freq_per_month end as may, "
                        + "              CASE WHEN month=6 THEN amount * freq_per_month end as june, "
                        + "              CASE WHEN month=7 THEN amount * freq_per_month end as july, "
                        + "              CASE WHEN month=8 THEN amount * freq_per_month end as aug, "
                        + "              CASE WHEN month=9 THEN amount * freq_per_month end as sept, "
                        + "              CASE WHEN month=10 THEN amount * freq_per_month end as october, "
                        + "              CASE WHEN month=11 THEN amount * freq_per_month end as nov, "
                        + "              CASE WHEN month=12 THEN amount * freq_per_month end as december, "
                        + "              CASE WHEN month>12 THEN amount * freq_per_month end as cur "
                        + "     FROM future_accounting fa, "
                        + "          future_accounting_org fao "
                        + "     WHERE fao.org_id = fa.org_id" 
                        + "      AND fao.direction = 'O' " 
                        + "      AND profile_id=?1 "
                        + "     ) temp GROUP BY org_id, org_name, freq_per_month , direction", nativeQuery = true)
        List<FutureBudget> findAllOutputByProfileId(Integer profileId);

        @Query(value = "SELECT org_id, org_name, freq_per_month , direction, MAX(cur) as current_amount, "
                        + " MAX(jan) as january_amount ,MAX(feb) as february_amount, MAX(march) as march_amount, "
                        + " MAX(apr) as april_amount, MAX(may) as may_amount, MAX(june) as june_amount, "
                        + " MAX(july) as july_amount, MAX(aug) as august_amount, MAX(sept) as september_amount, "
                        + " MAX(october) as october_amount, MAX(nov) as november_amount, MAX(december) as december_amount "
                        + "FROM ( "
                        + "     SELECT fa.org_id org_id, org_name,  freq_per_month , direction, "
                        + "              CASE WHEN month=1 then amount * freq_per_month end as jan, "
                        + "              CASE WHEN month=2 then amount * freq_per_month end as feb, "
                        + "              CASE WHEN month=3 then amount * freq_per_month end as march, "
                        + "              CASE WHEN month=4 then amount * freq_per_month end as apr, "
                        + "              CASE WHEN month=5 then amount * freq_per_month end as may, "
                        + "              CASE WHEN month=6 then amount * freq_per_month end as june, "
                        + "              CASE WHEN month=7 then amount * freq_per_month end as july, "
                        + "              CASE WHEN month=8 then amount * freq_per_month end as aug, "
                        + "              CASE WHEN month=9 then amount  * freq_per_month end as sept, "
                        + "              CASE WHEN month=10 then amount * freq_per_month end as october, "
                        + "              CASE WHEN month=11 then amount * freq_per_month end as nov, "
                        + "              CASE WHEN month=12 then amount * freq_per_month end as december, "
                        + "              CASE WHEN month>12 THEN amount * freq_per_month end as cur "
                        + "     FROM future_accounting fa, " 
                        + "          future_accounting_org fao "
                        + "     WHERE fao.org_id = fa.org_id"
                        + "      AND fao.direction = 'I' " 
                        + "      AND profile_id=?1 "
                        + "     ) temp GROUP BY org_id, org_name, freq_per_month , direction", nativeQuery = true)
        List<FutureBudget> findAllInputByProfileId(Integer profileId);

        @Query(value = "SELECT '1' as org_id, NULL AS org_name, NULL AS freq_per_month, direction,  "
                        + " SUM(cur) as current_amount, "
                        + " SUM(jan) as january_amount , "
                        + " SUM(feb) as february_amount, SUM(march) as march_amount, "
                        + " SUM(apr) as april_amount, SUM(may) as may_amount, SUM(june) as june_amount, "
                        + " SUM(july) as july_amount, SUM(aug) as august_amount, SUM(sept) as september_amount, "
                        + " SUM(october) as october_amount, SUM(nov) as november_amount, "
                        + " SUM(december) as december_amount "
                        + " FROM ( "
                        + "      SELECT fa.org_id org_id, org_name,  freq_per_month , direction, "
                        + "             CASE WHEN month=1 then amount* freq_per_month end as jan, "
                        + "             CASE WHEN month=2 then amount* freq_per_month end as feb, "
                        + "             CASE WHEN month=3 then amount* freq_per_month end as march, "
                        + "             CASE WHEN month=4 then amount* freq_per_month end as apr, "
                        + "             CASE WHEN month=5 then amount* freq_per_month end as may, "
                        + "             CASE WHEN month=6 then amount* freq_per_month end as june, "
                        + "             CASE WHEN month=7 then amount* freq_per_month end as july, "
                        + "             CASE WHEN month=8 then amount* freq_per_month end as aug, "
                        + "             CASE WHEN month=9 then amount* freq_per_month end as sept, "
                        + "             CASE WHEN month=10 then amount* freq_per_month end as october, "
                        + "             CASE WHEN month=11 then amount* freq_per_month end as nov, "
                        + "             CASE WHEN month=12 then amount* freq_per_month end as december, "
                        + "             CASE WHEN month>12 THEN amount * freq_per_month end as cur "
                        + "     FROM future_accounting fa, "
                        + "          future_accounting_org fao "
                        + "     WHERE fao.org_id = fa.org_id " 
                        + "      AND fao.direction = 'O' "
                        + "      AND profile_id=?1 "
                        + " ) temp group by direction;", nativeQuery = true)
        FutureBudget sumOutgoing(Integer profileId);

        @Query(value = "SELECT '2' as org_id, null as org_name, null as freq_per_month, direction, "
                        + " SUM(cur) as current_amount, "
                        + " SUM(jan) as january_amount , "
                        + " SUM(feb) as february_amount, SUM(march) as march_amount, "
                        + " SUM(apr) as april_amount, SUM(may) as may_amount, SUM(june) as june_amount, "
                        + " SUM(july) as july_amount, SUM(aug) as august_amount, SUM(sept) as september_amount, "
                        + " SUM(october) as october_amount, SUM(nov) as november_amount, "
                        + " SUM(december) as december_amount "
                        + " FROM ( "
                        + "     SELECT fa.org_id org_id, org_name,  freq_per_month , direction, "
                        + "             CASE WHEN month=1 then amount* freq_per_month end as jan, "
                        + "             CASE WHEN month=2 then amount* freq_per_month end as feb, "
                        + "             CASE WHEN month=3 then amount* freq_per_month end as march, "
                        + "             CASE WHEN month=4 then amount* freq_per_month end as apr, "
                        + "             CASE WHEN month=5 then amount* freq_per_month end as may, "
                        + "             CASE WHEN month=6 then amount* freq_per_month end as june, "
                        + "             CASE WHEN month=7 then amount* freq_per_month end as july, "
                        + "             CASE WHEN month=8 then amount* freq_per_month end as aug, "
                        + "             CASE WHEN month=9 then amount* freq_per_month end as sept, "
                        + "             CASE WHEN month=10 then amount* freq_per_month end as october, "
                        + "             CASE WHEN month=11 then amount* freq_per_month end as nov, "
                        + "             CASE WHEN month=12 then amount* freq_per_month end as december, "
                        + "             CASE WHEN month>12 THEN amount * freq_per_month end as cur "
                        + "     FROM future_accounting fa, "
                        + "          future_accounting_org fao "
                        + "     WHERE fao.org_id = fa.org_id " 
                        + "      AND fao.direction = 'I' "
                        + "      AND profile_id=?1 "
                        + " ) temp GROUP BY direction", nativeQuery = true)
        FutureBudget sumIncoming(Integer profileId);

        @Query(value = "SELECT MAX(fa.month)"
                + " FROM future_accounting_org fao, "
                + "      future_accounting fa "
                + " WHERE fao.org_id = fa.org_id "
                + "  AND fao.profile_id =?1 ", nativeQuery = true)
       Integer getCurrentMonthValue(Integer profileId);   

       @Query(value = "SELECT SUM(fa.amount) "
               + " FROM future_accounting_org fao, "
               + "      future_accounting fa "
               + " WHERE fao.org_id = fa.org_id "
               + " AND fao.profile_id = ?1 " 
               + " AND fa.month = ?2 ", nativeQuery = true)
        Integer getCurrentMonthTotalSum(Integer profileId, Integer month);    
}