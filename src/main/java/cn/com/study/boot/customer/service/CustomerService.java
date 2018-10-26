package cn.com.study.boot.customer.service;

import cn.com.study.boot.customer.dto.QueryCustParam;
import cn.com.study.boot.customer.jpa.po.CustomerPO;

import java.util.List;

public interface CustomerService {

    List<CustomerPO> queryAllCustomer(CustomerPO customerPO);


    List<CustomerPO> readAllCustomer(CustomerPO customerPO);


    List<CustomerPO> findAllCustomer(QueryCustParam custParam);

    List<CustomerPO> searchAllCustomer(QueryCustParam custParam);
}
