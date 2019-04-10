package com.group3.budgetApp.repository;

import com.group3.budgetApp.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUserName(String username);

    //@Query("select p from Profile p where upper(p.firstName) like concat('%', upper(?1), '%') or upper(p.lastName) like concat('%', upper(?2), '%')")
    User findByFirstNameAndLastName(String first, String last);

    List<User> findAllByLastName(String last);

}
