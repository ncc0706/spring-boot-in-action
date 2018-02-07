package io.arukas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "postgresEntityManager",
        transactionManagerRef = "postgresTransactionManager",
        basePackages = "io.arukas.repository.postgres" //设置dao（repo）所在位置
)
public class PostgresConfiguration {

    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    @Qualifier("postgresDataSource")
    private DataSource postgresDataSource;

    /**
     * Entity manager definition.
     *
     * @param builder an EntityManagerFactoryBuilder.
     * @return LocalContainerEntityManagerFactoryBean.
     */
    @Bean(name = "postgresEntityManager")
    public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(postgresDataSource)
                .properties(getVendorProperties(postgresDataSource))
                .packages("io.arukas.entity.postgres")//设置实体类所在位置
                .persistenceUnit("postgresPersistenceUnit")
                .build();
    }

    /**
     * @param entityManagerFactory
     * @return
     */
    @Primary
    @Bean(name = "postgresTransactionManager")
    public PlatformTransactionManager postgresTransactionManager(
            @Qualifier("postgresEntityManager") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    /**
     * 读取属性配置
     *
     * @param dataSource
     * @return
     */
    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }

}
