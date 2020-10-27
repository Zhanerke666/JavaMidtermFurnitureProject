package com.example.demo.controller;

import com.example.demo.entity.Auth;
import com.example.demo.service.AuthService;
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
    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody Auth auth){
        try {
            return ResponseEntity.ok(authService.login(auth));
        } catch (Exception e) {
            return ResponseEntity.status ( HttpStatus.UNAUTHORIZED ).build ();
        }
    }


}
