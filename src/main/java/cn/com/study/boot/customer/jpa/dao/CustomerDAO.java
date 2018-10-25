package cn.com.study.boot.customer.jpa.dao;

import cn.com.study.boot.customer.jpa.po.CustomerPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDAO extends JpaRepository<CustomerPO,String>,JpaSpecificationExecutor<CustomerPO> {
}
