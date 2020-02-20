package edu.yuriikoval1997.health;

import com.codahale.metrics.health.HealthCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CheckDatabaseConnection extends HealthCheck {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CheckDatabaseConnection(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    protected Result check() {
        try {
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
        } catch (Exception e) {
            return Result.unhealthy("Connection has dropped: ", e.getMessage());
        }
        return Result.healthy();
    }
}
