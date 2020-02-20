package edu.yuriikoval1997.aspects;

import com.codahale.metrics.MetricRegistry;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
@Aspect
public class MetricsAspect {
    private final MetricRegistry metricRegistry;

    @Autowired
    public MetricsAspect(MetricRegistry metricRegistry) {
        this.metricRegistry = metricRegistry;
    }

    public void measure(JoinPoint joinPoint) {}
}
