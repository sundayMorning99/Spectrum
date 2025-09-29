package org.launchcode.reward.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy="customer", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Transaction> transactions;

    public Customer() {}
    public Customer(String name){this.name =name;}

    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}

    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public List<Transaction> getTransactions(){return transactions;}
    public void setTransactions(List<Transaction> transactions){this.transactions = transactions;}
}

    

