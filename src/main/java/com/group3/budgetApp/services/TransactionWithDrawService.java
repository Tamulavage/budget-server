package com.group3.budgetApp.services;

import com.group3.budgetApp.model.TransactionWithdraw;
import com.group3.budgetApp.repository.TransactionWithdrawRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TransactionWithDrawService {



//    private EntityManagerFactory emf =Persistence.createEntityManagerFactory("budgetApp");
//    private EntityManager em =emf.createEntityManager();
//    private EntityTransaction tx =em.getTransaction();
//    @Autowired
    private TransactionWithdrawRepo repo;

//    public TransactionWithDrawService(){
//          this.emf =Persistence.createEntityManagerFactory("budgetApp");
//          this.em = emf.createEntityManager();
//          this.tx =em.getTransaction();
//
//    }
//
//    public TransactionWithDrawService(EntityManager em){
//        this.em = em;
//        this.tx = em.getTransaction();
//    }

    @Autowired
    public TransactionWithDrawService(TransactionWithdrawRepo repo){
        this.repo = repo;
    }

    public TransactionWithdraw createWithdrawTransaction(TransactionWithdraw transaction){
        Double amount = transaction.getAmount();
        if(amount < 0 ){
            throw new IllegalArgumentException("Amount must be greater then zero");
        }
        return repo.save(transaction);
    }


    public void deleteTransaction(Integer id){
        TransactionWithdraw transaction = repo.getOne(id);
        if( transaction != null){
            repo.delete(transaction);
        }
    }

    public TransactionWithdraw findTransactionById(Integer id){
        return repo.getOne(id);
    }
}
