package com.example.demo.controller;

import com.example.demo.entity.Auth;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Registration;
import com.example.demo.service.AuthService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

//@Service
@AllArgsConstructor
@RestController
public class AuthController {
    private final AuthService authService;
    private final CustomerService customerService;
    private RegistrationService registrationService;
    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody Auth auth){
        try {
            return ResponseEntity.ok(authService.login(auth));
        } catch (Exception e) {
            return ResponseEntity.status ( HttpStatus.UNAUTHORIZED ).build ();
        }
    }

    @PostMapping(path = "/api/admin/login")
    public ResponseEntity<?> adminLogin(@RequestBody Auth auth){
        try {
            return ResponseEntity.ok(authService.adminLogin(auth));
        } catch (Exception e) {
            return ResponseEntity.status ( HttpStatus.UNAUTHORIZED ).build ();
        }
    }

    @PostMapping(path = "/registration")
    public ResponseEntity<?> login(@RequestBody Registration customer){
        try {
            return ResponseEntity.ok(registrationService.addNewCustomer(customer));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


}
