package com.example.demo.service;

import com.example.demo.entity.Auth;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Role;
import com.example.demo.repository.AuthRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;
    @Transactional
    public Auth login(Auth auth) throws Exception {
        Auth authDB = authRepository.findByLoginAndPassword(auth.getLogin(),auth.getPassword());
        if(authDB == null){
            throw new Exception();
        }
        String token = UUID.randomUUID ().toString ();
        authDB.setToken(token);
        return authRepository.save(authDB);
    }
    public Customer getCustomerByToken(String token) throws Exception {
        Auth authDB = authRepository.findByToken(token);
        return customerRepository.findById(authDB.getCustomerId()).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @Transactional
    public Auth adminLogin(Auth auth) throws Exception {
        System.out.println("we are at adminLogin service");
        System.out.println(auth);
        Auth authDB = authRepository.findByLoginAndPassword(auth.getLogin(),auth.getPassword());
        System.out.println(authDB);
        if(authDB == null){
            throw new Exception();
        }
        System.out.println("no first exception");
        System.out.println(roleRepository.findRoleByUserId(authDB.getCustomerId()));
        String role = roleRepository.findRoleByUserId(authDB.getCustomerId());
        System.out.println(role);
        if(role.equals("admin")){
            String token = UUID.randomUUID ().toString ();
            authDB.setToken(token);
            return authRepository.save(authDB);
        }else {
            throw new Exception();
        }
    }
}
