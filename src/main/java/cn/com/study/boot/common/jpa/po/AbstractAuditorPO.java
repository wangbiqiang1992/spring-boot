package cn.com.study.boot.common.jpa.po;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public abstract class AbstractAuditorPO {

    @CreatedDate
    @Temporal(value = TIMESTAMP)
    private Date createDate;

    @LastModifiedDate
    @Temporal(value = TIMESTAMP)
    private Date updateDate;
}
