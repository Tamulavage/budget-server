package com.group3.budgetApp.repository;

import com.group3.budgetApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
