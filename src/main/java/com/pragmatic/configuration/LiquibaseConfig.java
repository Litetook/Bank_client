package com.pragmatic.configuration;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class LiquibaseConfig {
    @Bean
    @ConfigurationProperties("liquibase.main")
    public LiquibaseProperties mainLiquibaseProps() {
        return new LiquibaseProperties();
    }

    @Bean
    public SpringLiquibase mainLiquibase(DataSource mainDataSource,
                                         LiquibaseProperties mainLiquibaseProps) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setShouldRun(mainLiquibaseProps.isEnabled());
        liquibase.setDataSource(mainDataSource);
        liquibase.setChangeLog(mainLiquibaseProps.getChangeLog());
        liquibase.setContexts(mainLiquibaseProps.getContexts());

        return liquibase;
    }

}
