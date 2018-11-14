package cn.com.study.boot.blog.jpa.po;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 双向一对一主键关联
 * @author Administrator
 */
@Entity
@Data
@Table(name = "A_AUTHOR")
public class AuthorPO {
    @Id
    @GeneratedValue(generator = "pkGenerator")
    @GenericGenerator(name = "pkGenerator", strategy = "native")
    @Column(name = "id",length = 10)
    private Integer id;

    @Column(name = "name",nullable = false)
    private String name;

    @OneToOne(mappedBy = "author",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private AuthorDetailPO detail;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "id",referencedColumnName = "ext_id")
    private AuthorExtPO authorExt;
}
