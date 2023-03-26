package com.j11a.async.service;

import com.j11a.async.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class AsyncFetchData {

    private final RestTemplate restTemplate;

    public AsyncFetchData(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    @Async
    public CompletableFuture<User> fetchData(final String user) throws InterruptedException {
        log.info("Looking up: " + user);
        if(user.equalsIgnoreCase("exception")){ //simulate an exception
            throw new RuntimeException();
        }
        final String url = String.format("https://api.github.com/users/%s", user);
        User resp = restTemplate.getForObject(url, User.class);

        // Artificial delay of 1s for demonstration purposes
        int time = new Random().nextInt(10000);
        log.info("Delay: " + time + ". For User: " + user);
        Thread.sleep(time);
        CompletableFuture<User> userCompletableFuture = CompletableFuture.completedFuture(resp);
        log.info("Returning : " + user);
        return userCompletableFuture;
    }
}
