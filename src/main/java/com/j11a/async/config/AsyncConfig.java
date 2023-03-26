package com.j11a.async.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5); // minimum number of workers to keep alive without timing out
        executor.setMaxPoolSize(10); // maximum number of threads that can ever be created -- maxPoolSize depends on queueCapacity in that ThreadPoolTaskExecutor will only create a new thread if the number of items in its queue exceeds queueCapacity.
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("GithubLookup-");
        executor.initialize();
        return executor;
    }
}
