package com.dmt.budgetApp.repository;

import com.dmt.budgetApp.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Integer> {

    Profile findByUserName(String username);
    Profile findByFirstNameAndLastName(String first, String last);
    List<Profile> findAllByLastName(String last);



}
