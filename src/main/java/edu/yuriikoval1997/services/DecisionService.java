package edu.yuriikoval1997.services;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DecisionService {

    private final MetricRegistry metricRegistry;

    @Autowired
    public DecisionService(MetricRegistry metricRegistry) {
        this.metricRegistry = metricRegistry;
    }

    public String decide() {
        if (Math.random() > 0.33) {
            return success();
        }
        return failure();
    }

    private String success() {
        Timer.Context timer = metricRegistry.timer("successTimer").time();
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {
        }
        timer.stop();
        return "Перемога!";
    }

    private String failure() {
        metricRegistry.meter("failureMetric").mark();
        metricRegistry.counter("mmm5").inc();
        return "Зрада!";
    }
}
