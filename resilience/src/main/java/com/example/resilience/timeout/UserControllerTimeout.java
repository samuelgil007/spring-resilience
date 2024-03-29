package com.example.resilience.timeout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletionStage;

@RestController
public class UserControllerTimeout {

    @Autowired
    private UserServiceTimeout userServiceTimeout;

    @GetMapping("/userTimeout")
    public CompletionStage<ResponseEntity<String>> getUser() {
        return userServiceTimeout.getUser()
                .thenApply(user -> ResponseEntity.ok("User updated successfully"))
                .exceptionally(ex -> {
                    return ResponseEntity.status(500).body("Timeout occurred: " + ex.getMessage());
                });
    }
}

