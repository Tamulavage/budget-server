package com.group3.budgetApp.services;

import com.group3.budgetApp.repository.TransactionRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class TransactionWithDrawService {

    private EntityTransaction tx;
    private EntityManager em;
    private EntityManagerFactory emf;
    private TransactionRepository repo;
}
