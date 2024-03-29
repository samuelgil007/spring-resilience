package com.example.resilience.circuitbreaker;

import com.example.resilience.util.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerCircuitBreaker {

    @Autowired
    private UserServiceCircuitBreaker userServiceCircuitBreaker;

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userServiceCircuitBreaker.getUser(id));
    }
}
