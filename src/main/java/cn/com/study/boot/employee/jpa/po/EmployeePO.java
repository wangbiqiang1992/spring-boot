package cn.com.study.boot.employee.jpa.po;

import cn.com.study.boot.common.jpa.po.AbstractAuditorPO;
import cn.com.study.boot.employee.auditor.ActionLogsListener;
import cn.com.study.boot.employee.enums.Status;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;

@Entity
@Table(name = "b_employee")
@EntityListeners({ActionLogsListener.class})
@DynamicInsert
@DynamicUpdate /*这个注解不是说只更新非null字段，而是更新变化的字段*/
@Data
@ToString
public class EmployeePO extends AbstractAuditorPO {

    @Id
    @Column(name = "emp_id", nullable = false)
    private String empId;

    private String name;

    private String email;

    @Enumerated
    private Status status;

    @CreatedBy
    private String createUserId;

    @LastModifiedBy
    private String updateUserId;

    @PrePersist
    public void status() {
        if (status == null) {
            status = Status.ON_MISSION;
        }
    }
}
