package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;


    public ResponseEntity<?> addNewCustomer(Customer customer){
        Customer newUser = new Customer();
        newUser.setName(customer.getName());
        newUser.setAddress(customer.getAddress());
        newUser.setPhone(customer.getPhone());
        try{
            customerRepository.save(newUser);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }
}
