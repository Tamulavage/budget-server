package com.group3.budgetApp.repository;

import com.group3.budgetApp.model.ProfileAccountPK;
import com.group3.budgetApp.model.ProfileAccountXrefDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileAccountXrefRepository extends JpaRepository<ProfileAccountXrefDB, ProfileAccountPK> {
}
