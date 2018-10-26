package cn.com.study.boot.customer.service.impl;

import cn.com.study.boot.common.jpa.specification.BaseSpecification;
import cn.com.study.boot.customer.dto.QueryCustParam;
import cn.com.study.boot.customer.jpa.dao.CustomerDAO;
import cn.com.study.boot.customer.jpa.po.CustomerPO;
import cn.com.study.boot.customer.service.CustomerService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static cn.com.study.boot.common.jpa.specification.BaseSpecification.*;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerDAO customerDAO;

    /**
     * example查询的例子
     *
     * @param customerPO
     * @return
     */
    @Override
    public List<CustomerPO> queryAllCustomer(CustomerPO customerPO) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("aliasName", GenericPropertyMatchers.contains())
                .withIgnorePaths("id", "accountId", "idNum", "idName");
        Example<CustomerPO> example = Example.of(customerPO, matcher);
        return customerDAO.findAll(example);
    }

    @Override
    public List<CustomerPO> readAllCustomer(CustomerPO customerPO) {
        Specification specification = (root, query, cb) -> cb.equal(root.get("name"), customerPO.getName());
        Specification specification2 = (root, query, cb) -> cb.like(root.get("aliasName"), customerPO.getAliasName());
        return customerDAO.findAll(Specifications.where(specification).and(specification2));
    }

    @Override
    public List<CustomerPO> findAllCustomer(QueryCustParam custParam) {
        Specification specification = (root, query, cb) -> cb.equal(root.get("name"), custParam.getCustomerName());
        Specification specification2 = (root, query, cb) -> cb.like(root.get("aliasName"), "%" + custParam.getAlisaName() + "%");
        return customerDAO.findAll(Specifications.where(specification).and(specification2));
    }

    @Override
    public List<CustomerPO> searchAllCustomer(QueryCustParam custParam) {
        Specification<CustomerPO> specification = (root, query, cb) -> cb.and(cb.equal(root.get("name"), custParam.getCustomerName()), cb.like(root.get("aliasName"), "%" + custParam.getAlisaName() + "%"));
        return customerDAO.findAll(specification);
    }

    @Override
    public List<CustomerPO> seekAllCustomer(CustomerPO customerPO) {
        BaseSpecification<CustomerPO> specification = new BaseSpecification<CustomerPO>().and(
                Cnd.eq("name",customerPO.getName()),
                Cnd.like("aliasName",customerPO.getAliasName()))
                .or(Cnd.eq("level",customerPO.getLevel()))
                .asc("id");
        return customerDAO.findAll(specification);
    }

}
