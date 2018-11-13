package cn.com.study.boot.common.jpa.dao;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRepository <T,ID extends Serializable> extends JpaRepository<T,ID> {
    /**
     * 计算总数的方法
     * @param spec
     * @return
     */
    Long countAll(Specification<T> spec);
}
