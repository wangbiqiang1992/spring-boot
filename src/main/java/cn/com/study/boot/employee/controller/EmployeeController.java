package cn.com.study.boot.employee.controller;

import cn.com.study.boot.employee.jpa.dao.EmployeeDAO;
import cn.com.study.boot.employee.jpa.po.EmployeePO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Resource(name = "redisTemplate")
    private RedisTemplate<String,String> template;

    @Resource(name = "redisTemplate")
    private ValueOperations<String,Object> vOps;

    @Resource(name = "redisTemplate")
    private HashOperations<String,String,Object> hashOps;

    @RequestMapping("/{id}")
    public EmployeePO getEmployeeInfo(@PathVariable("id") EmployeePO employeePO){
        return employeePO;
    }

    @RequestMapping("/page")
    public Page<EmployeePO> findAllByPage(@PageableDefault(size = 5) Pageable pageable){
        return employeeDAO.findAll(pageable);
    }

    @RequestMapping("/page2")
    public List<EmployeePO> findAllByPage2(String name,@PageableDefault(size = 5) Pageable pageable){
        EmployeePO employeePO = new EmployeePO();
        employeePO.setName(name);
        ExampleMatcher matcher = ExampleMatcher.matching();
        Example<EmployeePO> example = Example.of(employeePO,matcher);
        return employeeDAO.findAll(example,pageable).getContent();
    }
}
