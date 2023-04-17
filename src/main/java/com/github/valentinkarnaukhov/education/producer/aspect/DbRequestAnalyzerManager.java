package com.github.valentinkarnaukhov.education.producer.aspect;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DbRequestAnalyzerManager {

    private final Map<Long, DbRequestsAnalyzer> dbRequestsAnalyzers = new HashMap<>();

    public void start() {
        Thread currentThread = Thread.currentThread();
        var requestAnalyzer = dbRequestsAnalyzers.computeIfAbsent(currentThread.getId(), key -> new DbRequestsAnalyzer());
        requestAnalyzer.start();
    }

    public void finish() {
        Thread currentThread = Thread.currentThread();
        dbRequestsAnalyzers.get(currentThread.getId()).finish();
    }

    public void incrementRequestCounterForCurrentThread() {
        Thread currentThread = Thread.currentThread();
        dbRequestsAnalyzers.get(currentThread.getId()).increment();
    }

}
