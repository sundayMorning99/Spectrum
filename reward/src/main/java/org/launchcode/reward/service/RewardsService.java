package org.launchcode.reward.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.launchcode.reward.dto.RewardsDTO;
import org.launchcode.reward.model.Customer;
import org.launchcode.reward.model.Transaction;
import org.launchcode.reward.repository.CustomerRepository;
import org.launchcode.reward.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class RewardsService {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private TransactionRepository transactionRepository;
    
    public int calculatePoints(BigDecimal amount) {
        if (amount == null) {
            return 0;
        }
        
        int points = 0;
        double amt = amount.doubleValue();
        
        if (amt > 100) {
            points += (int) ((amt - 100) * 2);
            points += 50; 
        } else if (amt > 50) {
            points += (int) (amt - 50);
        }
        return points;
    }
    
    public List<RewardsDTO> getAllCustomerRewards() {
        List<Customer> customers = customerRepository.findAll();
        List<RewardsDTO> rewardsList = new ArrayList<>();
        
        for (Customer customer : customers) {
            RewardsDTO rewards = calculateCustomerRewards(customer.getId());
            rewardsList.add(rewards);
        }
        return rewardsList;
    }
    
    public RewardsDTO calculateCustomerRewards(Long customerId) {
        Customer customer = customerRepository.findById(customerId).get();
        
        List<Transaction> transactions = transactionRepository.findByCustomerId(customerId);
        
        Map<String, Integer> monthlyPoints = new HashMap<>();
        int totalPoints = 0;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
        
        for (Transaction transaction : transactions) {
            int points = calculatePoints(transaction.getAmount());
            totalPoints += points;
            
            String monthKey = transaction.getTransactionDate().format(formatter);
            monthlyPoints.merge(monthKey, points, Integer::sum);
        }
        return new RewardsDTO(customerId, customer.getName(), monthlyPoints, totalPoints);
    }
}   