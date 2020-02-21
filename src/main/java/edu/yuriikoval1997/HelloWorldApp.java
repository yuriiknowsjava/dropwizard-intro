package edu.yuriikoval1997;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheck;
import edu.yuriikoval1997.configs.HelloWorldConfig;
import edu.yuriikoval1997.configs.bundles.LiquibaseBundle;
import edu.yuriikoval1997.spring.SpringContextLoaderListener;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.ws.rs.Path;

@Slf4j
@Configuration
@ComponentScan
public class HelloWorldApp extends Application<HelloWorldConfig> {

    public static void main(String[] args) throws Exception {
        new HelloWorldApp().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfig> bootstrap) {
        bootstrap.addBundle(new LiquibaseBundle());
    }

    @Override
    public void run(HelloWorldConfig configuration, Environment environment) {
        log.info("Application has been started!");

        AnnotationConfigWebApplicationContext parent = new AnnotationConfigWebApplicationContext();
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();

        parent.refresh();
        parent.getBeanFactory().registerSingleton("dataSourceFactory", configuration.getDataSourceFactory());
        parent.getBeanFactory().registerSingleton("template", configuration.getTemplate());
        parent.getBeanFactory().registerSingleton("defaultName", configuration.getDefaultName());
        parent.registerShutdownHook();
        parent.start();

        ctx.setParent(parent);
        ctx.register(HelloWorldApp.class); // class that is annotated with @ComponentScan
        ctx.refresh();
        ctx.registerShutdownHook();
        ctx.start();

        // Registering health check beans into dropwizard environment
        ctx.getBeansOfType(HealthCheck.class)
                .forEach(environment.healthChecks()::register);

        // Registering resource beans into dropwizard environment
        ctx.getBeansWithAnnotation(Path.class)
                .values()
                .forEach(environment.jersey()::register);

        // Registering metric beans into dropwizard env
        ctx.getBeansOfType(MetricRegistry.class)
                .forEach(environment.metrics()::register);

        environment.servlets().addServletListeners(new SpringContextLoaderListener(ctx));

        log.info("The context is up and running");
    }
}
