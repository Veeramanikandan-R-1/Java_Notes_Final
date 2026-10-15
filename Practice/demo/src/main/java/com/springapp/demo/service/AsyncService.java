package com.springapp.demo.service;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    private static final Logger logger =
            LoggerFactory.getLogger(AsyncService.class);

    @Async("taskExecutor")
    public CompletableFuture<String> processTask() {

        logger.info("Task started on thread: {}",
                Thread.currentThread().getName());

        try {
            Thread.sleep(3000); // Simulate a slow operation
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

            return CompletableFuture.failedFuture(e);
        }

        logger.info("Task completed");

        return CompletableFuture.completedFuture(
                "Async task completed successfully");
    }
}
