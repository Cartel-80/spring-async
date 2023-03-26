package com.j11a.async.controller;

import com.j11a.async.model.User;
import com.j11a.async.service.GitHubLookupService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class Controller {

    private final GitHubLookupService gitHubLookupService;

    public Controller(final GitHubLookupService gitHubLookupService) {
        this.gitHubLookupService = gitHubLookupService;
    }

    @GetMapping
    public List<User> getUserFromGithub(@RequestParam final String[] u) throws ExecutionException, InterruptedException {
        return gitHubLookupService.getUsers(u);
    }
}
