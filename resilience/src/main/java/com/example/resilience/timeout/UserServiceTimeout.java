package com.example.resilience.timeout;

import com.example.resilience.util.User;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.hibernate.context.TenantIdentifierMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeoutException;

@Service
public class UserServiceTimeout {

        private static final String ENDPOINT = "http://localhost:3000/timeout";

        @Autowired
        private RestTemplate restTemplate;

        @TimeLimiter(name = "getUserTimeout", fallbackMethod = "fallbackGetUser")
        public CompletionStage<User> getUser() {
            return CompletableFuture.supplyAsync(() -> {
                return restTemplate.getForEntity(ENDPOINT, User.class).getBody();
            });
        }

        public CompletionStage<User> fallbackGetUser(Exception e) {
            System.out.println(e.getMessage());
            return CompletableFuture.failedFuture(new TimeoutException());
        }

}

