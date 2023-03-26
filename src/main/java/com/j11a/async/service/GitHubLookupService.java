package com.j11a.async.service;

import com.j11a.async.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class GitHubLookupService {

    AsyncFetchData asyncFetchData;

    public GitHubLookupService(AsyncFetchData asyncFetchData) {
        this.asyncFetchData = asyncFetchData;
    }

    public List<User> getUsers(final String[] users) throws InterruptedException, ExecutionException {

        List<User> retVal = new ArrayList<>();
        final CompletableFuture<User>[] completableFutures = new CompletableFuture[users.length];

        for (int i = 0; i < users.length; i++) {
            completableFutures[i] = asyncFetchData.fetchData(users[i]);
        }

        CompletableFuture.allOf(completableFutures).join();

        for (CompletableFuture<User> completableFuture : completableFutures) {
            retVal.add(completableFuture.get());
        }

        return retVal;
    }
}
