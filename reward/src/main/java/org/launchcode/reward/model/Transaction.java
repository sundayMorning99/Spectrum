package org.launchcode.reward.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="transaction")
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, precision= 10, scale=2)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime transactionDate;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable=false)
    private Customer customer;

    public Transaction() {}

    public Transaction(BigDecimal amount, LocalDateTime transactionDate, Customer customer){
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.customer = customer;
    }

    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}

    public BigDecimal getAmount(){return amount;}
    public void setAmount(BigDecimal amount){this.amount = amount;}

    public LocalDateTime getTransactionDate(){return transactionDate;}
    public void setTransactionDate(LocalDateTime transactionDate){this.transactionDate = transactionDate;}

    public Customer getCustomer(){return customer;}
    public void setCustomer(Customer customer){this.customer = customer;}
}
