package cn.com.study.boot.common.jpa.po;

import cn.com.study.boot.employee.auditor.ActionLogsListener;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class AbstractAuditorPO {

    @CreatedDate
    private Date createDate;
    @LastModifiedDate
    private Date updateDate;
}
