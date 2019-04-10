package com.group3.budgetApp.repository;

import com.group3.budgetApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByFirstName(String first);
    List<User> findAllByFirstName(String first);
    User findByLastName(String last);
    List<User> findAllByLastName(String last);

}
