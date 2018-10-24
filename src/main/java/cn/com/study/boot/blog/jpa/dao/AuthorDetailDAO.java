package cn.com.study.boot.blog.jpa.dao;

import cn.com.study.boot.blog.jpa.po.AuthorDetailPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorDetailDAO extends JpaRepository<AuthorDetailPO,Integer>,JpaSpecificationExecutor<AuthorDetailPO> {
}
