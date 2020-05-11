package com.gitfocus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Tech Mahindra
 * Initial class to boot GitFocus Application
 */
@SpringBootApplication
public class GitFocusApplication {

    private static final Logger logger = LoggerFactory.getLogger(GitFocusApplication.class);

    public static void main(String[] args) {
        logger.info("Starting GitFocus-Service Application..");
        SpringApplication.run(GitFocusApplication.class, args);
    }

}