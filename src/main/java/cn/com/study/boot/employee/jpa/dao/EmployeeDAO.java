package cn.com.study.boot.employee.jpa.dao;

import cn.com.study.boot.employee.jpa.po.EmployeePO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDAO extends JpaRepository<EmployeePO,String>,JpaSpecificationExecutor<EmployeePO> {
}
