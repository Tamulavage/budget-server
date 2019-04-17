package com.group3.budgetApp.repository;

import com.group3.budgetApp.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Integer> {

    Profile findByUserName(String username);

    //@Query("select p from Profile p where upper(p.firstName) like concat('%', upper(?1), '%') or upper(p.lastName) like concat('%', upper(?2), '%')")
    Profile findByFirstNameAndLastName(String first, String last);

    List<Profile> findAllByLastName(String last);

}
