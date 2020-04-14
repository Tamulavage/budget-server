package com.dmt.budgetApp.repository;

import java.util.List;

import com.dmt.budgetApp.model.Transaction;
import com.dmt.budgetApp.model.TransactionWithAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    @Query(value = "SELECT temp.* " + " FROM ( " + "     SELECT  " + "         a.nick_name AS from_Account_Name, "
            + "         b.nick_name AS to_Account_Name, " + "         t.* " + "     FROM transaction t "
            + "       LEFT JOIN (SELECT * FROM account a,  profile_account_xref x WHERE x.account_id = a.id AND x.profile_id = ?1 ) a ON a.id = from_account_id "
            + "       LEFT JOIN (SELECT * FROM account a,  profile_account_xref x WHERE x.account_id = a.id AND x.profile_id =  ?1 ) b ON b.id = to_account_id ) "
            + "     temp " + " WHERE " + " NOT( temp.from_Account_Name IS  NULL AND temp.to_Account_Name IS NULL) "
            + " ORDER BY transaction_dt DESC, transaction_id DESC; ", nativeQuery = true)
    List<TransactionWithAccount> findAllWithAccountNameByUserId(Integer userId);

    @Query(value = "SELECT t.* "
        // hack to allo select from single table after entiry joins
        + ", 1 as clazz_ , null as accounts, null as from_account_name, null as to_account_name "
        + " FROM transaction t  WHERE transaction_id = ?1", nativeQuery = true)
    Transaction findAllByTranId(Integer userId);

}
