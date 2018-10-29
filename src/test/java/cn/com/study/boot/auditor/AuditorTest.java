package cn.com.study.boot.auditor;

import cn.com.study.boot.ReadingListApplicationTests;
import cn.com.study.boot.employee.jpa.dao.EmployeeDAO;
import cn.com.study.boot.employee.jpa.po.EmployeePO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AuditorTest extends ReadingListApplicationTests {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Test
    public void testCreateEmp(){
        EmployeePO employeePO = new EmployeePO();
        employeePO.setEmpId("011105");
        employeePO.setName("uzi");
        employeePO.setEmail("011105@htsc.com");
        employeePO = employeeDAO.save(employeePO);
        System.out.println(employeePO);
        employeePO = employeeDAO.findOne("011105");
        employeePO.setName("李定国");
        /*由于增加DynamicUpdate注解，通过打印sql*/
        employeePO = employeeDAO.save(employeePO);
        /*sql为 update b_employee set update_date=?, name=? where emp_id=?*/
        System.out.println(employeePO);
    }
}
