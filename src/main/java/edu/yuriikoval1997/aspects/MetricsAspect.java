package edu.yuriikoval1997.aspects;

import com.codahale.metrics.MetricRegistry;
import edu.yuriikoval1997.annotations.SetTimer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MetricsAspect {
    private final MetricRegistry metricRegistry;

    @Autowired
    public MetricsAspect(MetricRegistry metricRegistry) {
        this.metricRegistry = metricRegistry;
    }

    @Pointcut("@annotation(setTimer)")
    public void pointcut(SetTimer setTimer) {
    }

    @Before("within(edu.yuriikoval1997.services.*)")
    public void start() {
        System.out.println("start");
    }

    @After("within(edu.yuriikoval1997.services.*)")
    public void end() {
        System.out.println("end");
    }
}
