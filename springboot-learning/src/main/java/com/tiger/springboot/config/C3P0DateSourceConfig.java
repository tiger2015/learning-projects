package com.tiger.springboot.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class C3P0DateSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "c3p0")
    public DataSource dataSource() {
        return DataSourceBuilder.create().type(ComboPooledDataSource.class).build();
    }


}
