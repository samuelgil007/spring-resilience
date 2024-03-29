package com.example.resilience.fallback;

import com.example.resilience.util.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserControllerFallback {

    @Autowired
    private UserServiceFallBack userServiceFallBack;

    @GetMapping("/user/search")
    public ResponseEntity<User> searchUsers() {
        return ResponseEntity.ok(userServiceFallBack.searchUser());
    }
}
