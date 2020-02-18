package edu.yuriikoval1997;

import edu.yuriikoval1997.configs.HelloWorldConfig;
import edu.yuriikoval1997.configs.bundles.LiquibaseBundle;
import edu.yuriikoval1997.health.TemplateHealthCheck;
import edu.yuriikoval1997.resources.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.concurrent.atomic.AtomicLong;

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
    public void run(HelloWorldConfig helloWorldConfig, Environment environment) {
        System.out.println("Application has been started!");
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(helloWorldConfig.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        HelloWorldResource helloWorldResource = new HelloWorldResource(
                helloWorldConfig.getTemplate(),
                helloWorldConfig.getDefaultName(),
                new AtomicLong()
        );
        environment.jersey().register(helloWorldResource);
    }
}
