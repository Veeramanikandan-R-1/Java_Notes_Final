package com.springapp.demo.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final Logger logger =
            LoggerFactory.getLogger(ScheduledTasks.class);

    // Executes every 5 seconds
    @Scheduled(fixedRate = 5000)
    public void reportStatus() {
        logger.info("Fixed rate task: {}", LocalDateTime.now());
    }

    // Executes 5 seconds after the previous execution finishes
    @Scheduled(fixedDelay = 5000)
    public void syncData() {
        logger.info("Fixed delay task: {}", LocalDateTime.now());
    }

    // Executes every day at 9:00 AM
    @Scheduled(
        cron = "0 0 9 * * *",
        zone = "Asia/Kolkata"
    )
    public void dailyReport() {
        logger.info("Generating daily report");
    }
}
