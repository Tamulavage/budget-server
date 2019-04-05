package com.group3.budgetApp.services;

import com.group3.budgetApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@Service
public class UserServices {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("budgetApp");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();
    private UserRepository repo;

    @Autowired
    public UserServices(UserRepository repo) {
        this.repo = repo;
    }


}
