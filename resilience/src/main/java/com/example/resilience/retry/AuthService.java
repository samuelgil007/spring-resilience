package com.example.resilience.retry;

import com.example.resilience.util.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class AuthService {

    private static final String ENDPOINT = "http://localhost:3000/authenticate";

    @Autowired
    private RestTemplate restTemplate;

    @Retryable(maxAttempts = 5, backoff = @Backoff(delay = 100), retryFor = RuntimeException.class)
    public void authenticate(Credentials credentials) {
        System.out.println("Executed");
        restTemplate.postForEntity(ENDPOINT, credentials, String.class);
    }

}