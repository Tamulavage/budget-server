package com.dmt.budgetApp.repository;

import com.dmt.budgetApp.model.ProfileAccountPK;
import com.dmt.budgetApp.model.ProfileAccountXrefDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileAccountXrefRepository extends JpaRepository<ProfileAccountXrefDB, ProfileAccountPK> {
    @Modifying
    @Query(value = "DELETE FROM profile_account_xref  WHERE profile_id = ?1", nativeQuery = true)
    void deleteByUserId(Integer userId);
}
