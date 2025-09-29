package org.launchcode.reward.controller;

import org.launchcode.reward.dto.RewardsDTO;
import org.launchcode.reward.model.Customer;
import org.launchcode.reward.model.Transaction;
import org.launchcode.reward.repository.CustomerRepository;
import org.launchcode.reward.repository.TransactionRepository;
import org.launchcode.reward.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class RewardsController {

    @Autowired
    private RewardsService rewardsService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/rewards")
    public List<RewardsDTO> getAllRewards(){
        return rewardsService.getAllCustomerRewards();
    }

    @GetMapping("/rewards/{customerId}")
    public RewardsDTO getCustomerRewards(@PathVariable Long customerId){
        return rewardsService.calculateCustomerRewards(customerId);
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer){
        return customerRepository.save(customer);
    }

    @PostMapping("/transactions")
    public Transaction addTransaction(@RequestBody Transaction transaction){
        return transactionRepository.save(transaction);
    }
    
    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }
}