package com.github.valentinkarnaukhov.education.producer.aspect;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Slf4j
public class DbRequestsAnalyzer {

    private int executionCount = 0;

    public void start() {
        log.info("Execution has been started");
    }

    public void finish() {
        log.info("Execution has been finished");
        log.info(String.valueOf(executionCount));
        refresh();
    }

    public void increment() {
        executionCount += 1;
    }

    public void refresh() {
        this.executionCount = 0;
    }

}
