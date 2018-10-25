package cn.com.study.boot.customer.service;

import cn.com.study.boot.customer.jpa.po.CustomerPO;

import java.util.List;

public interface CustomerService {

    List<CustomerPO> queryAllCustomer(CustomerPO customerPO);
}
