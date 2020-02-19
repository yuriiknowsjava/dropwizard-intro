package edu.yuriikoval1997;

import com.codahale.metrics.health.HealthCheck;
import edu.yuriikoval1997.configs.HelloWorldConfig;
import edu.yuriikoval1997.configs.bundles.LiquibaseBundle;
import edu.yuriikoval1997.spring.SpringContextLoaderListener;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import javax.ws.rs.Path;

@Slf4j
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
        parent.getBeanFactory().registerSingleton("configuration", configuration);
        parent.registerShutdownHook();
        parent.start();

        ctx.setParent(parent);
        ctx.register(MyAppSpringConfiguration.class);
        ctx.refresh();
        ctx.registerShutdownHook();
        ctx.start();

        ctx.getBeansOfType(HealthCheck.class)
                .forEach((k, v) -> environment
                        .healthChecks()
                        .register(k, v));

        ctx.getBeansWithAnnotation(Path.class)
                .values()
                .forEach(environment.jersey()::register);

        environment.servlets().addServletListeners(new SpringContextLoaderListener(ctx));
    }
}
