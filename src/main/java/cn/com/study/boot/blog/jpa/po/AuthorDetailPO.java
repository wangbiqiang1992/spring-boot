package cn.com.study.boot.blog.jpa.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Data
@Table(name = "A_AUTHOR_DETAIL")
public class AuthorDetailPO {

    @Id
    @GeneratedValue(generator = "pkGenerator")
    @GenericGenerator(name = "pkGenerator",
            strategy = "foreign",
            parameters = @Parameter(name = "property", value = "author"))
    @Column(name = "id",nullable = false,unique = true)
    private Integer id;
    /*类型*/
    private String type;

    private String city;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @PrimaryKeyJoinColumn
    // 加上这个注解，可以避免出现stackOverFlow
    @JsonIgnore
    private AuthorPO author;

}
