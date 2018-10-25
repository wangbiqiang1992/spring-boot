package cn.com.study.boot.blog.jpa.dao;

import cn.com.study.boot.blog.jpa.po.AuthorExtPO;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorExtDAO extends BaseJpaRepository<AuthorExtPO,Integer> {
}
