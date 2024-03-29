package com.example.resilience.circuitbreaker;

import com.example.resilience.util.User;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceCircuitBreaker {

    private static final String AUTH_ENDPOINT = "http://localhost:3000/users";

    @Autowired
    private RestTemplate restTemplate;

    @CircuitBreaker(name = "user", fallbackMethod = "fallbackGetUser")
    public User getUser(Long id) {
        return restTemplate.getForEntity(AUTH_ENDPOINT+"/"+id, User.class).getBody();
    }

    public User fallbackGetUser(Long id, Throwable t) {
        return new User(id, "Fallback User", "fallback@example.com");
    }
}

