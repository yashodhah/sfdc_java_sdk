package com.example.sfdcsyncpoc.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicationShutdownManager {
    @Autowired
    private ApplicationContext appContext;

    public void exit() {
        SpringApplication.exit(appContext, () -> 0);
    }
}
