package com.group3.budgetApp.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {
    @Id
    private Integer id;
    private String name;
    private Double balance;
    private Integer type_id;
    private Long user_id;
    private String institution_name;
}
