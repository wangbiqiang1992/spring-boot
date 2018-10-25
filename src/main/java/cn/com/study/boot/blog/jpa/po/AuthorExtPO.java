package cn.com.study.boot.blog.jpa.po;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "A_AUTHOR_EXT")
@Data
public class AuthorExtPO {

    @Id
    @GeneratedValue
    private Integer ext_id;
    private String salary;
    private String level;

}
