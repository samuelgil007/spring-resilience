package com.example.resilience.fallback;

import com.example.resilience.util.User;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceFallBack {

    private static final String ENDPOINT = "http://localhost:3000/users";

    @Autowired
    private RestTemplate restTemplate;

    @CircuitBreaker(name = "searchFallback", fallbackMethod = "fallbackSearchUser")
    public User searchUser() {
        System.out.println(restTemplate.getForEntity(ENDPOINT, User.class).getBody());
        return restTemplate.getForEntity(ENDPOINT, User.class).getBody();
    }

    public User fallbackSearchUser(Throwable t) {
        System.out.println(t.getMessage());
        return new User(1L, "Fallback User", "fallback@example.com");
    }
}