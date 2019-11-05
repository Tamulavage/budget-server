package com.group3.budgetApp.repository;

import java.util.List;

import com.group3.budgetApp.model.Transaction;
import com.group3.budgetApp.model.TransactionWithAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    // @Query(value = "SELECT distinct t.* FROM Transaction t , Account a , profile_account_xref x where x.account_id = a.id and x.profile_id = ?1 and (t.from_account_id = a.id  OR t.to_account_id = a.id) ORDER BY transaction_dt DESC, transaction_id DESC", nativeQuery = true)
    // List<Transaction> findAllByUserId(Integer userId);

     @Query(value = "SELECT temp.* "
                    + " FROM ( "
                    + "     SELECT  "
                    + "         a.nick_name AS from_Account_Name, "
                    + "         b.nick_name AS to_Account_Name, "
                    + "         t.* "
                    + "     FROM budget.transaction t "
                    + "       LEFT JOIN (SELECT * FROM account a,  profile_account_xref x WHERE x.account_id = a.id AND x.profile_id = ?1 ) a ON a.id = from_account_id "
                    + "       LEFT JOIN (SELECT * FROM account a,  profile_account_xref x WHERE x.account_id = a.id AND x.profile_id =  ?1 ) b ON b.id = to_account_id ) "
                    + "     temp "
                    + " WHERE "
                    + " NOT( temp.from_Account_Name IS  NULL AND temp.to_Account_Name IS NULL) "
                    + " ORDER BY transaction_dt DESC, transaction_id DESC; ", nativeQuery = true)
    List<TransactionWithAccount> findAllWithAccountNameByUserId(Integer userId);
}
