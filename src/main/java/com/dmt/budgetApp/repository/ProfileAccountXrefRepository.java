package com.dmt.budgetApp.repository;

import com.dmt.budgetApp.model.ProfileAccountPK;
import com.dmt.budgetApp.model.ProfileAccountXrefDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileAccountXrefRepository extends JpaRepository<ProfileAccountXrefDB, ProfileAccountPK> {
}
