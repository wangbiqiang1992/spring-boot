package cn.com.study.boot.employee.auditor;

import org.springframework.data.domain.AuditorAware;

public class EmpAuditorAware implements AuditorAware<String> {
    @Override
    public String getCurrentAuditor() {
        //需要和shiro或者是spring security结合
        return "System User";
    }
}
