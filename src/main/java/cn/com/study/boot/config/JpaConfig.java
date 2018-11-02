package cn.com.study.boot.config;

import cn.com.study.boot.common.jpa.dao.BaseRepositoryImpl;
import com.mysql.fabric.jdbc.FabricMySQLDataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.concurrent.Executor;

@Configuration
@EnableJpaRepositories(basePackages = "cn.com.study.**.dao",repositoryBaseClass = BaseRepositoryImpl.class)
@EntityScan(basePackages = "cn.com.study.**.po")
@EnableTransactionManagement
public class JpaConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    @ConditionalOnMissingClass("com.alibaba.druid.pool.DruidDataSource")
    public DataSource dataSource(){
        return new FabricMySQLDataSource();
    }

    @Bean
    public Executor getExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(25);
        executor.initialize();
        return executor;
    }

}
