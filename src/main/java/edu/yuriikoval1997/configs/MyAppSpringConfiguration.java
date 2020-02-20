package edu.yuriikoval1997.configs;

import io.dropwizard.db.DataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.concurrent.atomic.AtomicLong;

@EnableAspectJAutoProxy
@Configuration
public class MyAppSpringConfiguration {

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public AtomicLong counter() {
        return new AtomicLong();
    }

    @Bean
    public DataSource dataSource(DataSourceFactory dataSourceFactory) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dataSourceFactory.getDriverClass());
        dataSource.setUrl(dataSourceFactory.getUrl());
        dataSource.setUsername(dataSourceFactory.getUser());
        dataSource.setPassword(dataSourceFactory.getPassword());
        return dataSource;
    }
}
