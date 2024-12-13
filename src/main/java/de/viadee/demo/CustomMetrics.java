package de.viadee.demo;

import java.time.Instant;

import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;

@Component
public class CustomMetrics {

    private final MeterRegistry meterRegistry;
    private volatile double lastSigtermReceived;
    private double customStartTime;

    public CustomMetrics(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        Gauge.builder("sigterm_received", this, CustomMetrics::getSigtermReceived)
                .description("timestamp of when the sigterm signal was received.")
                .register(meterRegistry);
        Gauge.builder("custom_start_time", this, CustomMetrics::getCustomStartTime).description("custom starttime metric").register(meterRegistry);
    }

    public void updateSigtermReceived() {
        this.lastSigtermReceived = Instant.now().getEpochSecond();
    }

    @PostConstruct
    public void updateCustomStartTime() {
        this.customStartTime = Instant.now().getEpochSecond();
    }

    private double getSigtermReceived() {
        return this.lastSigtermReceived;
    }

    private double getCustomStartTime() {
        return this.customStartTime;
    }
}