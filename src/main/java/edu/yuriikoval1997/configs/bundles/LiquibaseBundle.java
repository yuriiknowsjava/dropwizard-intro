package edu.yuriikoval1997.configs.bundles;

import edu.yuriikoval1997.configs.HelloWorldConfig;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;

public class LiquibaseBundle extends MigrationsBundle<HelloWorldConfig> {

        @Override
        public PooledDataSourceFactory getDataSourceFactory(HelloWorldConfig configuration) {
            return configuration.getDataSourceFactory();
        }

        @Override
        public String name() {
            return "postgresql";
        }
    }