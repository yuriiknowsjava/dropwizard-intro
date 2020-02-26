package edu.yuriikoval1997.resources;

import com.codahale.metrics.annotation.*;
import edu.yuriikoval1997.pojos.Saying;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import static java.util.function.Predicate.not;

@Component
@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    @Autowired
    public HelloWorldResource(String template, String defaultName, AtomicLong counter) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = counter;
    }

    @Metered(name = "sayHelloMeter")
    @GET
    @Timed(name = "sayHelloTimer")
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        String value = name
                .filter(not(String::isBlank))
                .orElse(defaultName);
        return new Saying(counter.incrementAndGet(), String.format(template, value));
    }
}
