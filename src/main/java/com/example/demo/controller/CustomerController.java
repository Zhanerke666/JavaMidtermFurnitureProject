package com.example.demo.controller;

import com.example.demo.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CustomerController {

    private final AuthService authService;

    @GetMapping("/customers/me")
    public ResponseEntity<?> me(@RequestHeader("Auth") String token) throws Exception {
        return ResponseEntity.ok ( authService.getCustomerByToken(token) );
    }
}
