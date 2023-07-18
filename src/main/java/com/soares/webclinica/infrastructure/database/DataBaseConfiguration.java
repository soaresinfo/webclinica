package com.soares.webclinica.infrastructure.database;

import lombok.RequiredArgsConstructor;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.HashMap;

@RequiredArgsConstructor
/*
@Configuration
@EnableJpaRepositories(basePackages = "com.soares.webclinica.repository",
                        entityManagerFactoryRef = "entityManager",
                        transactionManagerRef = "transactionManager")
@EntityScan("com.soares.webclinica.repository.model")*/
public class DataBaseConfiguration {

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    private String jdbcUsername;

    @Value("${spring.datasource.password}")
    private String jdbcPassword;

    @Value("${spring.datasource.driverClassName}")
    private String jdbcDriverClassName;

    private final Environment env;

    //@Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(jdbcDriverClassName);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(jdbcUsername);
        dataSource.setPassword(jdbcPassword);
        return dataSource;
    }

    //@Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }

    //@Bean
    public LocalContainerEntityManagerFactoryBean entityManager(){
        System.out.println("Iniciando entityManager...");
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());

        em.setPackagesToScan(new String[] {"com.soares.webclinica.repository.model"});
        em.setPersistenceUnitName("webclinica-pu");

        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(adapter);

        HashMap<String, Object> properties = new HashMap<>();
        properties.put(AvailableSettings.DIALECT, env.getProperty("spring.jpa.properties.hibernate.dialect"));
        properties.put(AvailableSettings.SHOW_SQL, env.getProperty("spring.jpa.properties.hibernate.show-sql"));

        em.setJpaPropertyMap(properties);
        em.afterPropertiesSet();
        System.out.println("Iniciado entityManager.");
        return em;
    }

    //@Bean
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManager().getObject());
        return transactionManager;
    }
}
