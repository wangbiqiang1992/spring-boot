package cn.com.study.boot.customer.jpa.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "B_CUSTOMER")
public class CustomerPO {

    @Id
    private String id;

    private String name;

    private String aliasName;

    private String level;

    private String type;

    private String accountId;

    private String idNum;

    private String idName;

}
