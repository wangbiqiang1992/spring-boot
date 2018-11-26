package cn.com.study.boot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.mysql.fabric.jdbc.FabricMySQLDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

/**
 * 一个数据库的情况，如果有两个数据库，则复制当前类，修改名称即可
 *
 * @author Administrator
 */
@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "primaryEntityManagerFactory",
        transactionManagerRef = "primaryTransactionManager",
        basePackages = "cn.com.study.**.dao"
)
@EnableTransactionManagement
/*@EnableConfigurationProperties注解是用来开启对@ConfigurationProperties注解配置Bean的支持。
也就是@EnableConfigurationProperties注解告诉Spring Boot 使能支持@ConfigurationProperties*/
@EnableConfigurationProperties(JpaProperties.class)
public class PrimaryJpaConfig {

    @Autowired
    private DataSource primaryDS;
    @Autowired
    private JpaProperties jpaProperties;


    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    @ConditionalOnMissingClass("com.alibaba.druid.pool.DruidDataSource")
    public DataSource dataSource(){
        return new FabricMySQLDataSource();
    }

    @Bean("primaryDS")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource primaryDataSource(){
        return new DruidDataSource();
    }

    @Primary
    @Bean(name = "primaryEntityManager")
    public EntityManager primaryEntityManager(EntityManagerFactoryBuilder builder){
        return primaryEntityManagerFactory(builder).getObject().createEntityManager();
    }

    @Primary
    @Bean(name = "primaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(EntityManagerFactoryBuilder builder){
        return builder.dataSource(primaryDS)
                .properties(jpaProperties.getHibernateProperties(primaryDS))
                .packages("cn.com.study.**.po")
                .persistenceUnit("primaryPersistenceUnit")
                .build();
    }

    @Bean("primaryTransactionManager")
    public PlatformTransactionManager primaryTransactionManager(EntityManagerFactoryBuilder builder){
        return new JpaTransactionManager(primaryEntityManagerFactory(builder).getObject());
    }

}
