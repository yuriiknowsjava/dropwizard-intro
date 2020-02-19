package edu.yuriikoval1997;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.atomic.AtomicLong;

@Configuration
@ComponentScan
public class MyAppSpringConfiguration {

    @Bean
    public String template() {
        return "Hello, %s!";
    }

    @Bean
    public String defaultName() {
        return "stranger";
    }

    @Bean
    public AtomicLong counter() {
        return new AtomicLong();
    }
}
