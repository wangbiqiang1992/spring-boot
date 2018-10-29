package cn.com.study.boot.employee.auditor;

import cn.com.study.boot.employee.enums.Status;
import cn.com.study.boot.employee.jpa.po.EmployeePO;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.PostPersist;

@Slf4j
public class ActionLogsListener {

    @PostPersist
    public void postLog(Object entity) {
        try {
            Class cls = entity.getClass();
            if(cls.newInstance() instanceof EmployeePO){
                EmployeePO employeePO = (EmployeePO) entity;
                Status status = employeePO.getStatus();
                if (status == null || status.equals(Status.ON_MISSION)) {
                    log.info(employeePO.getName() + "上班啦");
                } else if(status.equals(Status.DI_MISSION)) {
                    log.info(employeePO.getName() + "离职了");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
