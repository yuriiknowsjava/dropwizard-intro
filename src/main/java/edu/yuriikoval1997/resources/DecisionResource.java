package edu.yuriikoval1997.resources;

import edu.yuriikoval1997.services.DecisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
@Component
@Path("/decision")
public class DecisionResource {

    private final DecisionService decisionService;

    @Autowired
    public DecisionResource(DecisionService decisionService) {
        this.decisionService = decisionService;
    }

    @GET
    public Object makeDecision() {
        return new Object() {
            public String message = decisionService.decide();
        };
    }
}
