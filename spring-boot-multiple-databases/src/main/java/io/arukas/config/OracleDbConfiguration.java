package io.arukas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "oracleDbEntityManager",
        transactionManagerRef = "oracleDbTransactionManager",
        basePackages = "io.arukas.repository.oracle"//设置dao（repo）所在位置
)
public class OracleDbConfiguration {

    @Autowired
    @Qualifier("oracleDbDataSource")
    private DataSource oracleDbDataSource;

    /**
     * Entity manager definition.
     *
     * @param builder an EntityManagerFactoryBuilder.
     * @return LocalContainerEntityManagerFactoryBean.
     */
    @Bean(name = "oracleDbEntityManager")
    public LocalContainerEntityManagerFactoryBean oracleDb1EntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(oracleDbDataSource)
                .properties(hibernateProperties())
                .packages("io.arukas.entity.oracle")//设置实体类所在位置
                .persistenceUnit("oracleDbPersistenceUnit")
                .build();
    }

    /**
     * @param entityManagerFactory
     * @return
     */
    @Bean(name = "oracleDbTransactionManager")
    public PlatformTransactionManager oracleDbTransactionManager(
            @Qualifier("oracleDbEntityManager") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    /**
     * 读取属性配置
     *
     * @return
     */
    private Map<String, Object> hibernateProperties() {
        Resource resource = new ClassPathResource("hibernate-oracle-db.properties");
        try {
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            return properties.entrySet().stream()
                    .collect(Collectors.toMap(
                            e -> e.getKey().toString(),
                            e -> e.getValue())
                    );
        } catch (IOException e) {
            return new HashMap<String, Object>();
        }
    }

}
