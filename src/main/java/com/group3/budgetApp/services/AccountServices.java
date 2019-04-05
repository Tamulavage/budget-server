package com.group3.budgetApp.services;

import com.group3.budgetApp.model.Account;

import javax.persistence.Persistence;

public class AccountServices {
    private javax.persistence.EntityManagerFactory emf = Persistence.createEntityManagerFactory("Account");
    private com.group3.budgetApp.repository.AccountRepository repo;
    private javax.persistence.EntityManager em = emf.createEntityManager();
    private javax.persistence.EntityTransaction tx = em.getTransaction();


    public Account createAccount(String name, Double balance, Integer type_id, Integer user_id, String institution_name){
        Account account = new Account();
        account.setBalance(balance);
        account.setName(name);
        account.setType_id(type_id);
        account.setUser_id(user_id);
        account.setInstitution_name(institution_name);

        tx.begin();
        em.persist(account);
        tx.commit();

        return account;
    }


}

