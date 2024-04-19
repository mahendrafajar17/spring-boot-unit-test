package com.jatismobile.unittest.configurations;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "unittestEntityManager", transactionManagerRef = "unitTestTransactionManager", basePackages = "com.jatismobile.unittest.repositories.unittest")
public class UnitTestDBConfig {
    @Primary
    @Bean(name = "unittestDatasource")
    @ConfigurationProperties("unittest.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "unittestEntityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder,
            @Qualifier("unittestDatasource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.jatismobile.unittest.models.unittest")
                .persistenceUnit("unittestPersistenceUnit")
                .build();
    }

    @Primary
    @Bean(name = "unitTestTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("unittestEntityManager") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
