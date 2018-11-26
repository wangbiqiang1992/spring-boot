package cn.com.study.boot;

import cn.com.study.boot.employee.auditor.EmpAuditorAware;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author Administrator
 */
@EnableAspectJAutoProxy
@SpringBootApplication
@EnableAsync
@EnableJpaAuditing
@EnableCaching
public class ReadingListApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadingListApplication.class,args);
    }

    @Bean
    public AuditorAware<String> auditorAware(){
        return new EmpAuditorAware();
    }
}
