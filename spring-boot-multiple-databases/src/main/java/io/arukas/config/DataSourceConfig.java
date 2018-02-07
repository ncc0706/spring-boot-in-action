package io.arukas.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    /**
     * mysql datasource definition.
     *
     * @return datasource.
     */
    @Primary
    @Bean("mysqlDataSource")
    @Qualifier("mysqlDataSource")
    @ConfigurationProperties(prefix = "mysql.db.datasource")
    public DataSource mysqlDataSource() {
        return DataSourceBuilder
                .create()
                .build();
    }

    /**
     * oracle datasource definition.
     *
     * @return datasource.
     */
    @Bean("oracleDbDataSource")
    @Qualifier("oracleDbDataSource")
    @ConfigurationProperties(prefix = "oracle.db.datasource")
    public DataSource oracleDbDataSource() {
        return DataSourceBuilder
                .create()
                .build();
    }

    /**
     * postgres datasource definition.
     *
     * @return datasource.
     */
    @Bean("postgresDataSource")
    @Qualifier("postgresDataSource")
    @ConfigurationProperties(prefix = "postgres.db.datasource")
    public DataSource postgresDataSource() {
        return DataSourceBuilder
                .create()
                .build();
    }


}
