package com.example.bookstoreapi.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Counter;
import org.springframework.stereotype.Component;

@Component
public class CustomMetrics {

    private final Counter bookCounter;

    public CustomMetrics(MeterRegistry meterRegistry) {
        // Create a counter metric to track the number of books created
        bookCounter = meterRegistry.counter("book_creation_count");
    }

    public void incrementBookCreationCount() {
        bookCounter.increment();
    }
}
