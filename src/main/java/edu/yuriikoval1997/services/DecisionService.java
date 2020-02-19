package edu.yuriikoval1997.services;

import com.codahale.metrics.annotation.Timed;
import org.springframework.stereotype.Service;

@Service
public class DecisionService {

    public String decide() {
        if (Math.random() > 0.5) {
            return success();
        }
        return failure();
    }

    @Timed(name = "successTimer")
    private String success() {
        return "Перемога!";
    }

    @Timed(name = "failureTimer")
    private String failure() {
        return "Зрада!";
    }
}
