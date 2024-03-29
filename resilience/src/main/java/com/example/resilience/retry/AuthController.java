package com.example.resilience.retry;

import com.example.resilience.util.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Credentials credentials) throws Exception {
        authService.authenticate(credentials);
        return ResponseEntity.ok("Login successful");
    }
}

