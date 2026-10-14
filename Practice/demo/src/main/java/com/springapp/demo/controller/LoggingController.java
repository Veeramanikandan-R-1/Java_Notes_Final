package com.springapp.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggingController {

    public static final Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @GetMapping("/logs")
    public String getLogs(){
        logger.trace("Trace log");
        logger.debug("Debug log");
        logger.info("Logs endpoint called");
        logger.warn("This is a warning");
        logger.error("This is an error");

        return "Check application logs";
    }
    
}
