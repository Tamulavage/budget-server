package com.group3.budgetApp.services;

import org.springframework.stereotype.Service;

@Service
public class TransactionServices {
    private javax.persistence.EntityTransaction tx;
    private javax.persistence.EntityManager em;
    private javax.persistence.EntityManagerFactory emf;
    private com.group3.budgetApp.repository.TransactionRepository repo;
}
