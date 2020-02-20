package edu.yuriikoval1997.configs;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.server.ServerFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class HelloWorldConfig extends Configuration {

    @JsonProperty
    @NotEmpty
    private String template;

    @JsonProperty
    private String defaultName = "Stranger";

    @Valid
    @NotNull
    private DataSourceFactory dataSourceFactory = new DataSourceFactory();

    @JsonProperty("postgresql")
    public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
        dataSourceFactory.setPassword(System.getenv("DATASOURCE_PASSWORD"));
        dataSourceFactory.setUser(System.getenv("DATASOURCE_PASSWORD"));
        this.dataSourceFactory = dataSourceFactory;
    }
}
