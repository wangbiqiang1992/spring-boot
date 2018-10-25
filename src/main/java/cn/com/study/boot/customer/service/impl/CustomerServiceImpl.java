package cn.com.study.boot.customer.service.impl;

import cn.com.study.boot.customer.jpa.dao.CustomerDAO;
import cn.com.study.boot.customer.jpa.po.CustomerPO;
import cn.com.study.boot.customer.service.CustomerService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerDAO customerDAO;

    /**
     * example查询的例子
     * @param customerPO
     * @return
     */
    @Override
    public List<CustomerPO> queryAllCustomer(CustomerPO customerPO) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("aliasName",GenericPropertyMatchers.contains())
                .withIgnorePaths("id","accountId","idNum","idName");
        Example<CustomerPO> example = Example.of(customerPO,matcher);
        return customerDAO.findAll(example);
    }
}
