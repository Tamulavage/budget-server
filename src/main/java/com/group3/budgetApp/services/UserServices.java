package com.group3.budgetApp.services;

import com.group3.budgetApp.model.User;
import com.group3.budgetApp.repository.UserRepository;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Optional;

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

    public UserServices() {
    }

    public User createUser(String firstName, String lastName){
        User user = new User(firstName, lastName);

        tx.begin();
        em.persist(user);
        tx.commit();

        return user;
    }

    public User updateFirstName(User user, String newName){
        User userToUpdate = em.merge(user);
        if(userToUpdate != null){
            tx.begin();
            userToUpdate.setFirstName(newName);
            tx.commit();
        }
        return user;
    }

    public User updateLastName(User user, String newName){
        User userToUpdate = em.merge(user);
        if(userToUpdate != null){
            tx.begin();
            userToUpdate.setLastName(newName);
            tx.commit();
        }
        return user;
    }

    public User updateFirstName(Integer id, String newName){
        User user = findById(id);
        if(user != null){
            tx.begin();
            user.setFirstName(newName);
            tx.commit();
        }
        return user;
    }

    public User updateLastName(Integer id, String newName){
        User user = findById(id);
        if(user != null){
            tx.begin();
            user.setLastName(newName);
            tx.commit();
        }
        return user;
    }

    public User findById(Integer id){
        return em.find(User.class, id);
    }

    public User removeUser(Integer id){
        User user = em.find(User.class, id);
        return removeHelper(user);
    }

    public User removeUser(User user){
        User userToRemove = em.merge(user);
        return removeHelper(userToRemove);
    }

    private User removeHelper(User user) {
        if (user != null) {
            tx.begin();
            em.remove(user);
            tx.commit();
        }
        return user;
    }
}
