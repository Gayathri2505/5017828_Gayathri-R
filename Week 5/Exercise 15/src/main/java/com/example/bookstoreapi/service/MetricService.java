package com.example.bookstoreapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Counter;

@Service
public class MetricService {

    private final Counter bookCreationCounter;

    @Autowired
    public MetricService(MeterRegistry meterRegistry) {
        this.bookCreationCounter = meterRegistry.counter("books_created");
    }

    public void incrementBookCreationCounter() {
        bookCreationCounter.increment();
    }
}
