package com.example.demo.service;

import com.example.demo.entity.Auth;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Registration;
import com.example.demo.repository.AuthRepository;
import com.example.demo.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class RegistrationService {
    protected AuthRepository authRepository;
    protected CustomerRepository customerRepository;
    @Transactional
    public ResponseEntity<?> addNewCustomer(Registration customer){
        Customer newUser = new Customer();
        newUser.setName(customer.getName());
        newUser.setAddress(customer.getAddress());
        newUser.setPhone(customer.getPhone());
        try{
            newUser = customerRepository.save(newUser);
            System.out.println(newUser.getId());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        Auth newAuth = new Auth();
        newAuth.setLogin(customer.getLogin());
        newAuth.setPassword(customer.getPassword());

        try {
            newAuth.setCustomerId(newUser.getId());
            newAuth.setToken(UUID.randomUUID ().toString ());
            newAuth = authRepository.save(newAuth);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok().build();
    }

}
