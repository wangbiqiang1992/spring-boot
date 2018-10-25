package cn.com.study.boot.blog.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class AuthorDTO implements Serializable {
    private static final long serialVersionUID = -4348079944413373101L;
    private String name;
    private String type;
    private String city;
    private String salary;
    private String level;
}
